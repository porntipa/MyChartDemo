package th.or.nectec.mychartdemo;

import java.util.List;

/**
 * Created by codemobiles on 10/10/17.
 */

class ChartLineResult {

    /**
     * data : [{"date":"27/06/2016","prices":20500},{"date":"28/06/2016","prices":20450},{"date":"30/06/2016","prices":20350},{"date":"31/06/2016","prices":20400},{"date":"01/06/2016","prices":20450},{"date":"02/06/2016","prices":20400},{"date":"03/06/2016","prices":20350},{"date":"04/06/2016","prices":20650},{"date":"06/06/2016","prices":20650},{"date":"07/06/2016","prices":20650},{"date":"08/06/2016","prices":20750},{"date":"09/06/2016","prices":20900},{"date":"10/06/2016","prices":21050}]
     * title : ย้อนหลัง 30 วัน ประจำวันที่ 10/06/59
     * type : 96.5
     */

    private String title;
    private double type;
    private List<DataBean> data;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getType() {
        return type;
    }

    public void setType(double type) {
        this.type = type;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * date : 27/06/2016
         * prices : 20500
         */

        private String date;
        private int prices;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getPrices() {
            return prices;
        }

        public void setPrices(int prices) {
            this.prices = prices;
        }
    }
}
