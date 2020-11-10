package com.cdtp.smartgreenhouse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    String seralar[], sera_descriptions[];
    int images[] = {R.drawable.sera_1, R.drawable.sera_2, R.drawable.sera_3};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);

        seralar = getResources().getStringArray(R.array.seralar);
        sera_descriptions = getResources().getStringArray(R.array.sera_properties);

        MyAdapter myAdapter = new MyAdapter(this, seralar, sera_descriptions, images);

        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}