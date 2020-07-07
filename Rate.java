package src;

import java.util.HashMap;
import java.util.Map;

public class Rate {


        private String no;
        private String effectiveDate;
        private Double mid;
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public String getEffectiveDate() {
            return effectiveDate;
        }

        public void setEffectiveDate(String effectiveDate) {
            this.effectiveDate = effectiveDate;
        }

        public Double getMid() {
            return mid;
        }

        public void setMid(Double mid) {
            this.mid = mid;
        }

        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

    @Override
    public String toString() {
        return "Rate{" +
                "no='" + no + '\'' +
                ", effectiveDate='" + effectiveDate + '\'' +
                ", mid=" + mid +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
