package com.techreviewsandhelp.weighttracker;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;


public class MainActivity extends AppCompatActivity {


    private LineChart mChart;
    private WebView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ID
        mView = (WebView) findViewById(R.id.webView);
        // make line chart
        mChart = new LineChart(this);
        //add to main layout
        mView.addView(mChart);

        // customize line chart
        mChart.setDescription("");
        mChart.setNoDataTextDescription("No date for the moment");

        // enable value highlighting
        mChart.setHighlightPerTapEnabled(true);

        //enable touch gestures
        mChart.setTouchEnabled(true);

        //enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setDrawGridBackground(false);

        // enable pinch and zoom
        mChart.setPinchZoom(true);

        // alternative background color
        mChart.setBackgroundColor(Color.LTGRAY);

        //data
        LineData data = new LineData();
        data.setValueTextColor(Color.WHITE);

        //data to the line chart
        mChart.setData(data);

        //legend object
        Legend l = mChart.getLegend();

        //customize legend
        l.setForm(Legend.LegendForm.LINE);
        l.setTextColor(Color.WHITE);

        XAxis xl = mChart.getXAxis();
        xl.setTextColor(Color.WHITE);
        xl.setDrawGridLines(false);
        xl.setAvoidFirstLastClipping(true);

        YAxis yl = mChart.getAxisLeft();
        yl.setTextColor(Color.WHITE);
        yl.setAxisMaxValue(120f);
        yl.setDrawGridLines(true);

        YAxis yl2 = mChart.getAxisRight();
        yl2.setEnabled(false);

    }


    private void addEntry(){
        LineData data = mChart.getData();

        if (data != null){
            LineDataSet set = data.getDataSetByIndex(0);

            if (set == null){
                // if null
                set = createSet();
                data.addDataSet(set);
            }
            // new random value
            data.addXValue("");
            data.addEntry(
                    new Entry((float) (Math.random() *75) + 60f, set
                    .getEntryCount()), 0);

            //notify chart data has been changed
            mChart.notifyDataSetChanged();

            //limit number of visible entries
            mChart.setVisibleXRange(6);

            //scroll to the last entry
            mChart.moveViewToX(data.getXValCount()-7);
        }
    }

    // method to create set
    private LineDataSet createSet(){
        LineDataSet set = new LineDataSet(null, "SPL Db");
        set.setDrawCubic(true);
        set.setCubicIntensity(0.2f);
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setColor(ColorTemplate.getHoloBlue());
        set.setLineWidth(2f);
        set.setCircleSize(4f);
        set.setFillAlpha(65);
        set.setFillColor(ColorTemplate.getHoloBlue());
        set.setHighLightColor(Color.rgb(244,117,177));
        set.setValueTextColor(Color.WHITE);
        set.setValueTextSize(10f);

        return set;
    }

    @Override
    protected void onResume() {
        super.onResume();
    //going to simulate real time data

        new Thread(new Runnable(){

            public void run(){
                addEntry(); // chart is notified of update in addentry method
            }

            });
        //pause between adds
        try {
            Thread.sleep(600);
        }catch (InterruptedException e){
            // manage error
        }
    }


    //Menu



    // Initiating Menu XML file (menu.xml)
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * Event Handling for Individual menu item selected
     * Identify single menu item by it's id
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch (item.getItemId())
        {
/**
 * Figure I might as well leave this in.
 *
            case R.id.menu_bookmark:
                // Single menu item is selected do something
                // Ex: launching new activity/screen or show alert message
                Toast.makeText(getApplicationContext(),"menu_bookmark", Toast.LENGTH_LONG).show();
                return true;

            case R.id.menu_save:
                Toast.makeText(getApplicationContext(),"menu_save", Toast.LENGTH_LONG).show();
                return true;

            case R.id.menu_search:
                Toast.makeText(getApplicationContext(),"menu_search", Toast.LENGTH_LONG).show();
                return true;

            case R.id.menu_delete:
                Toast.makeText(getApplicationContext(),"menu_delete", Toast.LENGTH_LONG).show();
                return true;
**/
            case R.id.menu_preferences:
                final Context c = this;
                startActivity(new Intent(c, Settings.class));
                finish();
                return true;

            case R.id.menu_share:
                Toast.makeText(getApplicationContext(),"menu_share", Toast.LENGTH_LONG).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}



