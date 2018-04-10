package th.or.nectec.mychartdemo;

import java.util.List;

/**
 * Created by codemobiles on 10/10/17.
 */

class ChartBarResult {


    /**
     * error : false
     * price1 : [{"price":"18250","date":"15/Feb."},{"price":"18100","date":"14/Feb."},{"price":"17970","date":"13/Feb."},{"price":"18000","date":"12/Feb."},{"price":"18450","date":"11/Feb."},{"price":"18300","date":"10/Feb."},{"price":"18000","date":"9/Feb."}]
     * price2 : [{"price":"18350","date":"15/Mar."},{"price":"18400","date":"14/Mar."},{"price":"17990","date":"13/Mar."},{"price":"18200","date":"12/Mar."},{"price":"18450","date":"11/Mar."},{"price":"18500","date":"10/Mar."},{"price":"18300","date":"9/Mar."}]
     */

    private boolean error;
    private List<PriceBean> price1;
    private List<PriceBean> price2;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<PriceBean> getPrice1() {
        return price1;
    }

    public void setPrice1(List<PriceBean> price1) {
        this.price1 = price1;
    }

    public List<PriceBean> getPrice2() {
        return price2;
    }

    public void setPrice2(List<PriceBean> price2) {
        this.price2 = price2;
    }

    public static class PriceBean {
        /**
         * price : 18250
         * date : 15/Feb.
         */

        private String price;
        private String date;

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }

}
