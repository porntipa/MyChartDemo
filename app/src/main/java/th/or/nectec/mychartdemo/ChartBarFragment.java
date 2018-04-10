package th.or.nectec.mychartdemo;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChartBarFragment extends Fragment {

    private BarChart mChart;
    private ChartBarResult mPriceResult;
    private BarDataSet set1;
    private ArrayList<String> xVals;
    private BarDataSet set2;

    public ChartBarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_chart_bar, container, false);
        drawChart();
        return v;
    }


    public void drawChart(){


        //mChart.setDescription("Gold Price History");
        mChart.setDescription("");
        mChart.setDescriptionPosition(250,20);
        mChart.setExtraOffsets(5, 10, 5, 5);
        mChart.setDragDecelerationFrictionCoef(0.95f);
        mChart.setHighlightPerTapEnabled(true);
        //mChart.animateX(1500, Easing.EasingOption.Linear);


        // hide label in each orietation
        mChart.getAxisLeft().setDrawLabels(true);
        mChart.getAxisRight().setDrawLabels(false);
        mChart.getXAxis().setDrawLabels(true);
        mChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        mChart.getXAxis().setTextColor(Color.parseColor("#57000000"));

        mChart.getLegend().setEnabled(true); // label under chart

        // hide left, right vertical lines
        YAxis leftAxis = mChart.getAxisLeft();
        YAxis rightAxis = mChart.getAxisRight();

        leftAxis.setGridColor(Color.parseColor("#339d9272"));
        rightAxis.setGridColor(Color.parseColor("#339d9272"));

        mChart.getAxisLeft().setTextColor(Color.parseColor("#57000000"));
        mChart.getAxisRight().setTextColor(Color.parseColor("#57000000"));


        leftAxis.setDrawAxisLine(false);
        rightAxis.setDrawAxisLine(false);


        // hide horizontal lines
        XAxis xAxis = mChart.getXAxis();
        xAxis.setGridColor(Color.parseColor("#9d9272"));
        xAxis.setDrawGridLines(false);


        setData();
        mChart.animateXY(3000, 3000);


    }

    private void setData() {

        xVals = new ArrayList<String>();
        Gson gson = new Gson();
        mPriceResult = gson.fromJson(getString(R.string.dummy_bar_data), ChartBarResult.class);

        for (int i = 0; i < mPriceResult.getPrice1().size()-2; i++) {
            String date = mPriceResult.getPrice1().get(i).getDate();
            xVals.add(date);
        }

        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(getDataSet(mPriceResult.getPrice1(), ColorTemplate.LIBERTY_COLORS) ); // add the datasets
        dataSets.add(getDataSet(mPriceResult.getPrice2(), ColorTemplate.COLORFUL_COLORS) ); // add the datasets

        // create a data object with the datasets
        BarData data = new BarData(xVals, dataSets);
        data.setValueFormatter(new MyValueFormatter());


        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        // redraw
        mChart.invalidate();
    }


    public class MyValueFormatter implements ValueFormatter {


        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
            // write your logic here
            switch (dataSetIndex){
                case 0:
                    return "Gold";
                case 1:
                    return "Silver";
            }
            return "";
        }


    }


    public BarDataSet getDataSet(List<ChartBarResult.PriceBean> data, int[] colors){
        ArrayList<BarEntry> yVals = new ArrayList<BarEntry>();

        for (int i = 0; i < data.size()-2; i++) {

            String price = data.get(i).getPrice();
            yVals.add(new BarEntry(Integer.valueOf(price), i));
        }

        BarDataSet dataSet = new BarDataSet(yVals, "");
        dataSet.setValueTypeface(Typeface.DEFAULT);
        dataSet.setValueTextColor(Color.parseColor("#000000"));
        dataSet.setColors(colors);
        //dataSet.setColor(Color.BLUE);
        dataSet.setValueTextSize(10f);
        dataSet.setBarSpacePercent(20f);
        dataSet.setBarShadowColor(Color.BLACK);

        return dataSet;

    }


}
