package th.or.nectec.mychartdemo;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChartLineFragment extends Fragment {

    private LineChart mChart;
    private ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
    private ChartLineResult mPriceResult;
    private ArrayList<Entry> yVals = new ArrayList<Entry>();
    private ArrayList<String> xVals = new ArrayList<String>();


    public ChartLineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_chart_line, container, false);
        mChart = v.findViewById(R.id.chart);
        drawChart();
        return v;

    }

    public void drawChart() {


        // 1. Configure Chart Global properties (Line Color, Grid Color, Animation Label, Legend)
        // 2. Create data-entry from raw data that you feed server
        // 3. Create dataset (one dataset standing for one graph) combines both data-entry and visual properties
        // 4. Insert all dataset(s) into Chart-Data object
        // 5. Set chart-data into chart object via mChart.setData = ...
        // 5. Call invalidte to draw graphs

        mChart.setDescription("Gold Price History");
        //mChart.setDescription("Gold Price History");
        //mChart.setDescription("");
        //mChart.saveToPath(..);
        mChart.setDescriptionPosition(250,20);
        mChart.setExtraOffsets(5, 30, 5, 5);
        mChart.setDragDecelerationFrictionCoef(0.95f);
        mChart.setHighlightPerTapEnabled(true);

        //mChart.animateXY(1000,1000, Easing.EasingOption.Linear, Easing.EasingOption.Linear);
        mChart.animateX(1500, Easing.EasingOption.Linear);




        // hide label in each orietation
        mChart.getAxisLeft().setDrawLabels(true);
        mChart.getAxisLeft().setTextColor(Color.parseColor("#57000000"));
        mChart.getAxisRight().setDrawLabels(false);
        mChart.getXAxis().setDrawLabels(true);
        mChart.getXAxis().setTextColor(Color.parseColor("#57000000"));
        mChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        mChart.getLegend().setEnabled(true); // label under chart

        // hide left, right vertical lines
        YAxis leftAxis = mChart.getAxisLeft();
        YAxis rightAxis = mChart.getAxisRight();

        leftAxis.setGridColor(Color.parseColor("#339d9272"));
        rightAxis.setGridColor(Color.parseColor("#339d9272"));

        leftAxis.setDrawAxisLine(false);
        rightAxis.setDrawAxisLine(false);


        // hide horizontal lines
        XAxis xAxis = mChart.getXAxis();
        xAxis.setGridColor(Color.parseColor("#9d9272"));
        xAxis.setDrawGridLines(false);

        //mChart.getAxisLeft().setDrawGridLines(false);
        //mChart.getAxisRight().setDrawGridLines(false);




        setData1();
    }

    private void setData1() {



        Gson gson = new Gson();
        mPriceResult = gson.fromJson(getString(R.string.dummy_line_data), ChartLineResult.class);
        List<ChartLineResult.DataBean> priceArray = mPriceResult.getData();


        for (int i = 0; i < priceArray.size(); i++) {
            String price = priceArray.get(i).getPrices() + "";
            String date = priceArray.get(i).getDate();

            yVals.add(new Entry(Integer.valueOf(price), i));
            xVals.add(date);
        }

        // create a dataset and give it a type
        // mPriceResult.getTitle = Legend text
        LineDataSet dataSet = new LineDataSet(yVals, mPriceResult.getTitle());
        // set the line to be drawn like this "- - - - - -"
        //dataSet.enableDashedLine(10f, 5f, 0f);
        //dataSet.enableDashedHighlightLine(10f, 5f, 0f);
        int color = Color.parseColor("#E91E63");
        dataSet.setColor(color);
        dataSet.setLineWidth(3f);


        dataSet.setFillColor(Color.parseColor("#E91E63"));
        dataSet.setFillAlpha(10); //0-100
        dataSet.setDrawCubic(true);
        dataSet.setDrawFilled(true);


        dataSet.setCircleColor(color);
        dataSet.setValueTextColor(color);
        dataSet.setCircleRadius(6f);
        dataSet.setDrawCircleHole(true);
        dataSet.setValueTextSize(9f);

        dataSet.setValueTypeface(Typeface.DEFAULT);
        dataSet.setValueTextColor(Color.parseColor("#55000000"));

        dataSets.add(dataSet); // add the datasets

        // create a data object with the datasets
        LineData data = new LineData(xVals, dataSets);


        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);
        mChart.invalidate();
    }

}
