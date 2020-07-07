package src;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class RestClient {





    public Currency getPrice(String name) throws IOException {


        //Ustawianie zakresu dat z którego pobieram je
        LocalDate nowDate = LocalDate.now();
        LocalDate twoMonthsAgoDate=nowDate.minusMonths(2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY/MM/dd");
        formatter.format(nowDate);
        formatter.format(twoMonthsAgoDate);



        URL url = new URL("http://api.nbp.pl/api/exchangerates/rates/a/"+name+"/"+twoMonthsAgoDate+"/"+nowDate+"/");
        URLConnection urlCon = url.openConnection();
        InputStreamReader in = new InputStreamReader(urlCon.getInputStream());
        ObjectMapper mapper = new ObjectMapper();

        Currency er = mapper.readValue(in, Currency.class);

        in.close();
        return er;
    }



}
