package src.mainWindow;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import src.Currency;
import src.Rate;
import src.RestClient;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.text.ParseException;



public class mainWindow extends JFrame {

    private JPanel mainPanel;
    private JPanel chartPanel;// wyświetlanie wykresu

    //wyświetlanei informacji o walutach
    private JTable MinMaxCoursetable;
    private JTable actualCourseTable;
    private JTable differenceTable;
    private JTable changeMaxTable;
    private JTable changeMinTable;



    RestClient r=new RestClient();
    Currency eur=r.getPrice("eur");
    Currency usd=r.getPrice("usd");
    Currency gbp=r.getPrice("gbp");

    public mainWindow() throws IOException {

        initialize();
    }


    private void initialize(){

        JFrame frame=new JFrame();

        mainPanel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        mainPanel.setLayout(new FlowLayout());
        chartPanel.setBorder(BorderFactory.createEmptyBorder(0,0,40,0));
        frame.add(mainPanel,BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Kursy walut");
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);


        try {
            createChart();
        } catch (ParseException e) {
            e.printStackTrace();
        }


        createTable();





    }


//tworzenie tabel
    public void createTable(){




        Object rowData1[][]={
                { "Euro", eur.findActualValue() },
                { "Dolar", usd.findActualValue()},
                { "Funt", gbp.findActualValue()} };
        String columnNames1[]={"Waluta","Aktualny kurs"};

        actualCourseTable=new JTable(rowData1,columnNames1);

        actualCourseTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        actualCourseTable.setRowHeight(30);

        actualCourseTable.setPreferredScrollableViewportSize(actualCourseTable.getPreferredSize());
        actualCourseTable.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        JScrollPane tableScrollPane1 = new JScrollPane(actualCourseTable);
        tableScrollPane1.setPreferredSize(new Dimension(actualCourseTable.getPreferredSize()));
        mainPanel.add(tableScrollPane1,BorderLayout.CENTER);




        Object rowData2[][]={
                { "Euro", eur.findMinValue(),eur.findMinValueDate(),eur.findMaxValue(),eur.findMaxValueDate() },
                { "Dolar", usd.findMinValue(),usd.findMinValueDate(),usd.findMaxValue(),usd.findMaxValueDate()},
                { "Funt",gbp.findMinValue(),gbp.findMinValueDate(),gbp.findMaxValue(),gbp.findMaxValueDate()} };
        String columnNames2[]={"Waluta","Min kurs","Data","Max kurs","Data"};
        MinMaxCoursetable=new JTable(rowData2,columnNames2);
        //wysokość wwiersza
        MinMaxCoursetable.setRowHeight(30);
        MinMaxCoursetable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane tableScrollPane2 = new JScrollPane(MinMaxCoursetable);
        tableScrollPane2.setPreferredSize(new Dimension(MinMaxCoursetable.getPreferredSize()));
        mainPanel.add(tableScrollPane2,BorderLayout.EAST);


        Object rowData3[][]={
                { "Euro", eur.differenceActualMin(),eur.differenceActualMax() },
                { "Dolar",usd.differenceActualMin(),usd.differenceActualMax()},
                { "Funt",gbp.differenceActualMin(),gbp.differenceActualMax()} };
        String columnNames3[] = { "Waluta","Różnica aktualny kurs a min","Różnica aktualny kurs a max"};
        differenceTable=new JTable(rowData3,columnNames3);
        differenceTable.setRowHeight(30);
        differenceTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        differenceTable.getColumnModel().getColumn(1).setWidth(200);
        JScrollPane tableScrollPane3 = new JScrollPane(differenceTable);
        tableScrollPane3.setPreferredSize(new Dimension(differenceTable.getPreferredSize()));
        mainPanel.add(tableScrollPane3,BorderLayout.AFTER_LAST_LINE);

        Object rowData4[][]={
                { "Euro", eur.findMaxDifference(),eur.findMaxDiffDay1(),eur.findMaxDiffDay2() },
                { "Dolar",usd.findMaxDifference(),usd.findMaxDiffDay1(),usd.findMaxDiffDay2()},
                { "Funt",gbp.findMaxDifference(),gbp.findMaxDiffDay1(),gbp.findMaxDiffDay2()} };
        String columnNames4[] = { "Waluta","Max różnica","Z dnia","Na dzień"};
        changeMaxTable=new JTable(rowData4,columnNames4);
        changeMaxTable.setRowHeight(30);
        changeMaxTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane tableScrollPane4 = new JScrollPane(changeMaxTable);
        tableScrollPane4.setPreferredSize(new Dimension(changeMaxTable.getPreferredSize()));
        mainPanel.add(tableScrollPane4,BorderLayout.AFTER_LAST_LINE);

        Object rowData5[][]={
                { "Euro", eur.findMinDifference(),eur.findMinDiffDay1(),eur.findMinDiffDay2() },
                { "Dolar",usd.findMinDifference(),usd.findMinDiffDay1(),usd.findMinDiffDay2()},
                { "Funt",gbp.findMinDifference(),gbp.findMinDiffDay1(),gbp.findMinDiffDay2()} };
        String columnNames5[] = { "Waluta","Min różnica","Z dnia","Na dzień"};
        changeMinTable=new JTable(rowData5,columnNames5);
        changeMinTable.setRowHeight(30);
        changeMinTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane tableScrollPane5 = new JScrollPane(changeMinTable);
        tableScrollPane5.setPreferredSize(new Dimension(changeMinTable.getPreferredSize()));
        mainPanel.add(tableScrollPane5,BorderLayout.AFTER_LAST_LINE);



    }

    //tworzenie wykresu
    public void createChart() throws ParseException {


        JFreeChart chart = ChartFactory.createLineChart(
                "Waluty ",
                "Data",
                "Cena",
                createDataset(eur,usd,gbp),
                PlotOrientation.VERTICAL,
                true,true,false
        );


        // zmiana parametrów wyświetlania wykresu
        CategoryPlot plot=chart.getCategoryPlot();
        plot.setDomainGridlinesVisible(true);
        plot.getDomainAxis().setCategoryLabelPositions(
                CategoryLabelPositions.UP_45);

        // zmiana zakresu osi Y
        ValueAxis domain;
        domain =  plot.getRangeAxis();
        domain.setRange(3.40, 5.5);


        ChartPanel panel = new ChartPanel(chart);
        panel.setPreferredSize(new Dimension(200,200));

        chartPanel.setPreferredSize(new Dimension(750,550));
        chartPanel.removeAll();
        chartPanel.add(panel,BorderLayout.CENTER);
        chartPanel.validate();

    }

    //przygotowanie danych do wyświetlania na wykresie
    private DefaultCategoryDataset createDataset(Currency eur, Currency usd, Currency gbp) throws ParseException {

        String series1 = "Euro";
        String series2="Dolar";
        String series3="Funt";

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for(Rate rate :eur.getRates()){
        dataset.addValue(rate.getMid(), series1, rate.getEffectiveDate());
        }

        for(Rate rate :usd.getRates()){
            dataset.addValue(rate.getMid(), series2, rate.getEffectiveDate());
        }
        for(Rate rate :gbp.getRates()){
            dataset.addValue(rate.getMid(), series3, rate.getEffectiveDate());
        }
        return dataset;
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            try {
                mainWindow mainWindow = new mainWindow();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });


    }


}
