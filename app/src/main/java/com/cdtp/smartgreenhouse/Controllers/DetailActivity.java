package com.cdtp.smartgreenhouse.Controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cdtp.smartgreenhouse.R;

public class DetailActivity extends AppCompatActivity {

    ImageView image;
    TextView title, description, temperature;
    SeekBar seekBar;
    Button setTempButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        image = findViewById(R.id.detailImageView);
        title = findViewById(R.id.detailSeraTextView);
        description = findViewById(R.id.detailSeraPropertiesTextView);
        temperature = findViewById(R.id.detailsTemperatureTextView);
        setTempButton = findViewById(R.id.tempSetButton);

        seekBar = findViewById(R.id.tempSeekBar);

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



    private void getSetData(){
        //intentin dolu olup olmadığını kontrol ediyoruz
        if(getIntent().hasExtra("image") && getIntent().hasExtra("sera") && getIntent().hasExtra("sera_detail")){
            title.setText(getIntent().getStringExtra("sera"));
            description.setText(getIntent().getStringExtra("sera_detail"));
            image.setImageResource(getIntent().getIntExtra("image", 1));
            temperature.setText(""+ getIntent().getDoubleExtra("temperature", 25.0) + "°C");
            seekBar.setProgress((int)getIntent().getDoubleExtra("temperature", 25.0));


            //Boşsa error veriyoruz
        } else {
            Toast.makeText(this, "No data", Toast.LENGTH_LONG);
        }
    }
}