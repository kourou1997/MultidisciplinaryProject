package com.cdtp.smartgreenhouse.Controllers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.json.*;

import java.util.ArrayList;
import java.util.Random;

public class DetailActivity extends AppCompatActivity implements OnChartGestureListener, OnChartValueSelectedListener {

    public static ArrayList<Double> valueList = new ArrayList<Double>();

    private LineChart chart;
    private TextView title, description, temperature;
    private SeekBar seekBar;
// ...

    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();




        @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        title = findViewById(R.id.detailSeraTextView);
        description = findViewById(R.id.detailSeraPropertiesTextView);
        temperature = findViewById(R.id.detailsTemperatureTextView);
        Button setTempButton = findViewById(R.id.tempSetButton);
        String sera_ismi = "";






        Query data = null;
        Query update = null;



        //HALİHAZIRDA OLAN DEĞERLERİ EKLEMEK İÇİN
        if(getIntent().getStringExtra("sera").equals("Sera 1")){
            sera_ismi = "sera1";
            data = mDatabase.child("sera1").orderByKey().limitToLast(10);
            data.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    valueList.clear();
                    if(snapshot.getValue() != null){
                        System.out.println(snapshot.getValue().toString());
                        getSetData();

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        } else if (getIntent().getStringExtra("sera").equals("Sera 2")) {
            sera_ismi = "sera2";
            data = mDatabase.child("sera2").orderByKey().limitToLast(10);
            data.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    valueList.clear();
                    if(snapshot.getValue() != null){
                        System.out.println(snapshot.getValue().toString());
                        getSetData();

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        } else if (getIntent().getStringExtra("sera").equals("Sera 3")) {
            sera_ismi = "sera3";
            data = mDatabase.child("sera3").orderByKey().limitToLast(10);
            data.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    valueList.clear();
                    if(snapshot.getValue() != null){
                        System.out.println(snapshot.getValue().toString());
                        getSetData();

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

            final String finalSera_ismi = sera_ismi;
            setTempButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    double wantedTemp = Double.parseDouble(temperature.getText().toString().substring(0, 4));
                    switch(finalSera_ismi){
                        case "sera1":
                        {
                            mDatabase.child("istenen_sicaklik").child("-MQbYfmvGT9Vu1bxxgwY")
                                    .child("temp").setValue(wantedTemp);
                        }
                        break;
                        case "sera2":
                        {
                            mDatabase.child("istenen_sicaklik").child("-MQb_mCgwR76f6XOa7Fx")
                                    .child("temp").setValue(wantedTemp);
                        }
                        break;
                        case "sera3":
                        {
                            mDatabase.child("istenen_sicaklik").child("-MQbeuDh7a-1khe56FRP")
                                    .child("temp").setValue(wantedTemp);
                        }
                        break;
                        default:
                            break;

                    }
                }
            });





            if(getIntent().getStringExtra("sera").equals("Sera 1")){
                update = mDatabase.child("sera1");
            } else if (getIntent().getStringExtra("sera").equals("Sera 2")) {
                update = mDatabase.child("sera2");
            } else if (getIntent().getStringExtra("sera").equals("Sera 3")) {
                update = mDatabase.child("sera3");
            }

        update.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {


                String jsonString = snapshot.getValue().toString();
                JSONObject obj = null;
                try {
                    obj = new JSONObject(jsonString);
                        if(valueList.size() > 10){
                            valueList.remove(0);
                        }
                        valueList.add(Double.parseDouble(obj.getString("sicaklik")));
                        getSetData();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



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


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                i = i + 25;
                temperature.setText(""+ i +".0°C");
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
            seekBar.setProgress((int)(getIntent().getDoubleExtra("temperature", 25.0) - 25));

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