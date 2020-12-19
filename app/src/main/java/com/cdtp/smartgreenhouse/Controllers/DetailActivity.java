package com.cdtp.smartgreenhouse.Controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cdtp.smartgreenhouse.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DetailActivity extends AppCompatActivity implements OnChartGestureListener, OnChartValueSelectedListener {

    List<Double> valueList = Arrays.asList(15.2, 15.7, 15.9, 18.9, 19.5, 20.2, 21.3, 23.7);

    private LineChart chart;
    private TextView title, description, temperature;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        title = findViewById(R.id.detailSeraTextView);
        description = findViewById(R.id.detailSeraPropertiesTextView);
        temperature = findViewById(R.id.detailsTemperatureTextView);
        Button setTempButton = findViewById(R.id.tempSetButton);

        seekBar = findViewById(R.id.tempSeekBar);

        chart = findViewById(R.id.chart1);


        chart.setOnChartValueSelectedListener(this);

        chart.setDrawGridBackground(false);
        chart.getDescription().setEnabled(false);
        chart.setDrawBorders(false);

        chart.getAxisLeft().setEnabled(false);
        chart.getAxisRight().setDrawAxisLine(false);
        chart.getAxisRight().setDrawGridLines(false);
        chart.getXAxis().setDrawAxisLine(false);
        chart.getXAxis().setDrawGridLines(false);

        // enable touch gestures
        chart.setTouchEnabled(true);

        // enable scaling and dragging
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        chart.setPinchZoom(false);

        getSetData();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                temperature.setText(""+i+".0°C");
                //TODO: Change temperature globally when the button is pressed.
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



    }

    private final int[] colors = new int[] {
            ColorTemplate.VORDIPLOM_COLORS[0],
            ColorTemplate.VORDIPLOM_COLORS[1],
            ColorTemplate.VORDIPLOM_COLORS[2]
    };

    private void getSetData(){
        //intentin dolu olup olmadığını kontrol ediyoruz
        if(getIntent().hasExtra("image") && getIntent().hasExtra("sera") && getIntent().hasExtra("sera_detail")){
            title.setText(getIntent().getStringExtra("sera"));
            description.setText(getIntent().getStringExtra("sera_detail"));
            //image.setImageResource(getIntent().getIntExtra("image", 1));
            temperature.setText(""+ getIntent().getDoubleExtra("temperature", 25.0) + "°C");
            seekBar.setProgress((int)getIntent().getDoubleExtra("temperature", 25.0));

            chart.resetTracking();

            Random random = new Random();

            //datasetler
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();

            //ilerleme datası
            ArrayList<Entry> values = new ArrayList<>();

            for (int i = 0; i < valueList.size(); i++) {
                double val = valueList.get(i);
                values.add(new Entry(i, (float) val));
            }

            LineDataSet d = new LineDataSet(values, "Sıcaklık");
            d.setLineWidth(2.5f);
            d.setCircleRadius(4f);

            int color = colors[(int) (random.nextInt(1000) % colors.length)];
            d.setColor(color);
            d.setCircleColor(color);
            dataSets.add(d);

            //TODO: x eksenine paralel giden, ayarlanmış sıcaklığı gösteren bir dataset de istiyoruz
            ArrayList<Entry> wantedTempValues = new ArrayList<>();

            for (int i = 0; i < valueList.size(); i++) {
                wantedTempValues.add(new Entry(i, (float) Float.parseFloat(temperature.getText().toString().substring(0, 4))));
            }

            LineDataSet d2 = new LineDataSet(wantedTempValues, "Hedef");
            d2.setLineWidth(2.5f);
            d2.setCircleRadius(4f);

            int color2 = colors[(int) (random.nextInt(1000) % colors.length)];
            d2.setColor(color2);
            d2.setCircleColor(color2);
            dataSets.add(d2);


            LineData data = new LineData(dataSets);
            chart.setData(data);
            chart.invalidate();

            //Boşsa error veriyoruz
        } else {
            Toast.makeText(this, "No data", Toast.LENGTH_LONG);
        }
    }

    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartLongPressed(MotionEvent me) {

    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {

    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {

    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {

    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {

    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}