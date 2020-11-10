package com.cdtp.smartgreenhouse.Controllers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.cdtp.smartgreenhouse.Model.Sera;
import com.cdtp.smartgreenhouse.R;

public class MainActivity extends AppCompatActivity {

    Sera sera[] = {new Sera("Sera 1", "Sera sıcaklığı düşük.", R.drawable.sera_1, 21.0),
                    new Sera("Sera 2", "Sera optimal durumda.", R.drawable.sera_2, 22.0),
                    new Sera("Sera 3", "Sera sıcaklığı yüksek.", R.drawable.sera_3, 45.0)};
    RecyclerView recyclerView;
    String seralar[], sera_descriptions[];
    int images[];
    double temperatures[];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);

        //sera bilgileri burada doluyor, bu ileride server'dan çekilecek bilginin doldurulduğu yer olacak
        fillData();

        MyAdapter myAdapter = new MyAdapter(this, seralar, sera_descriptions, images, temperatures);

        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    private void fillData(){
        String[] tempSeralar = new String[sera.length];
        String[] tempSeraDescriptions = new String[sera.length];
        int[] tempImages = new int[sera.length];
        double[] tempTemperatures = new double[sera.length];

        for(int i=0; i<sera.length; i++){
            tempSeralar[i] = sera[i].getTitle();
            tempSeraDescriptions[i] = sera[i].getDescription();
            tempImages[i] = sera[i].getImage();
            tempTemperatures[i] = sera[i].getTemperature();
        }

        seralar = tempSeralar;
        sera_descriptions = tempSeraDescriptions;
        images = tempImages;
        temperatures = tempTemperatures;


    }
}