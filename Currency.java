package src;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Currency {


        private String table;
        private String currency;
        private String code;
        private List<Rate> rates = null;
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        public String getTable() {
            return table;
        }

        public void setTable(String table) {
            this.table = table;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public List<Rate> getRates() {
            return rates;
        }

        public void setRates(List<Rate> rates) {
            this.rates = rates;
        }

        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }


    @Override
    public String toString() {
        return "Currency{" +
                "table='" + table + '\'' +
                ", currency='" + currency + '\'' +
                ", code='" + code + '\'' +
                ", rates=" + rates +
                ", additionalProperties=" + additionalProperties +
                '}';
    }



    public Double findActualValue(){
        Double actual =rates.get(rates.size()-1).getMid();

        actual= Double.valueOf(Math.round(actual*100));
        actual=actual/100;
        return actual;
    }
    //znajdywanie min kurs
    public Double findMinValue(){

            double min=rates.get(0).getMid();
        for(int i=0; i< rates.size(); i++){
            if(min>rates.get(i).getMid()){
                min=rates.get(i).getMid();
            }
        }

            return min;
    }

    public String findMinValueDate(){
         double min=findMinValue();
        String date=new String();
        for(int i=0; i< rates.size(); i++){
            if(min==rates.get(i).getMid()){
                date=rates.get(i).getEffectiveDate();
            }
        }

        return date;
    }

    public  Double findMaxValue(){
        double max=rates.get(0).getMid();
        for(int i=0; i< rates.size(); i++){
            if(max<rates.get(i).getMid()){
                max=rates.get(i).getMid();
            }
        }

        return max;
    }

    public String findMaxValueDate(){
        double max=findMaxValue();
        String date=new String();
        for(int i=0; i< rates.size(); i++){
            if(max==rates.get(i).getMid()){
                date=rates.get(i).getEffectiveDate();
            }
        }
        return date;
    }

    public Double differenceActualMin(){
            Double result=findActualValue()-findMinValue();

        result= Double.valueOf(Math.round(result*1000));
        result=result/1000;
            return result;

    }

    public Double differenceActualMax(){
        Double result=findActualValue()-findMaxValue();
       result= Double.valueOf(Math.round(result*1000));
        result=result/1000;
        return result;
    }

    public Double findMaxDifference(){
        double max=rates.get(0).getMid()-rates.get(1).getMid();
        for(int i=0; i< rates.size()-1; i++){
            double diff=rates.get(i).getMid()-rates.get(i+1).getMid();
            if(max<diff){
                max=diff;
            }
        }
       // max= Double.valueOf(Math.round(max*1000));
      //  max=max/1000;

        return max;
    }

    public String findMaxDiffDay1(){
            double max=findMaxDifference();
            String day1=new String();
            for(int i=0;i<rates.size()-1;i++){
                double result=rates.get(i).getMid()-rates.get(i+1).getMid();
                if(result==max){
                    day1=rates.get(i).getEffectiveDate();
                }
            }

            return day1;
    }

    public String findMaxDiffDay2(){
        double max=findMaxDifference();
        String day2=new String();
        for(int i=0;i<rates.size()-1;i++){
            double result=rates.get(i).getMid()-rates.get(i+1).getMid();
            if(result==max){
                day2=rates.get(i+1).getEffectiveDate();
            }
        }

        return day2;
    }

    public Double findMinDifference(){
        double min=rates.get(0).getMid()-rates.get(1).getMid();
        for(int i=0; i< rates.size()-1; i++){
            double diff=rates.get(i).getMid()-rates.get(i+1).getMid();
            if(min>diff ){
                min=diff;
            }
        }

        return min;
    }

    public String findMinDiffDay1(){
        double min=findMinDifference();
        String day1=new String();
        for(int i=0;i<rates.size()-1;i++){
            double result=rates.get(i).getMid()-rates.get(i+1).getMid();
            if(result==min){
                day1=rates.get(i).getEffectiveDate();
            }
        }

        return day1;
    }

    public String findMinDiffDay2(){
        double min=findMinDifference();
        String day2=new String();
        for(int i=0;i<rates.size()-1;i++){
            double result=rates.get(i).getMid()-rates.get(i+1).getMid();
            if(result==min){
                day2=rates.get(i+1).getEffectiveDate();
            }
        }

        return day2;
    }
}
