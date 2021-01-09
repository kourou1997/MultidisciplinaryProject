package com.cdtp.smartgreenhouse.Controllers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;


import com.cdtp.smartgreenhouse.Model.Sera;
import com.cdtp.smartgreenhouse.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Data server'dan alınacak, şimdilik test array'i verildi
    public static ArrayList<Sera> sera = new ArrayList<Sera>();

    private RecyclerView recyclerView;
    private String seralar[], sera_descriptions[];
    private int images[];
    private double temperatures[];
    Context context;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    MyAdapter myAdapter;
    ArrayList<Double> temps = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();


        recyclerView = findViewById(R.id.recycler_view);
        FirebaseApp.initializeApp(getBaseContext());


         final Query temp = mDatabase.child("istenen_sicaklik");

         temp.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    sera.clear();
                    temps.clear();
                    for(DataSnapshot s : snapshot.getChildren()){
                        System.out.println("Hello " + s.child("temp").getValue());
                        temps.add(Double.parseDouble(s.child("temp").getValue().toString()));
                    }

                    for(int i = 1; i <= temps.size(); i++){
                        sera.add(new Sera("Sera " + i, "description", R.drawable.sera_1, temps.get(i-1)));
                    }

                    fillData();

                    myAdapter = new MyAdapter(context, seralar, sera_descriptions, images, temperatures);
                    recyclerView.setAdapter(myAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(context));

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
         });

    }


    private void fillData(){
        String[] tempSeralar = new String[sera.size()];
        String[] tempSeraDescriptions = new String[sera.size()];
        int[] tempImages = new int[sera.size()];
        double[] tempTemperatures = new double[sera.size()];

        for(int i=0; i<sera.size(); i++){
            tempSeralar[i] = sera.get(i).getTitle();
            tempSeraDescriptions[i] = sera.get(i).getDescription();
            tempImages[i] = sera.get(i).getImage();
            tempTemperatures[i] = sera.get(i).getTemperature();
        }

        seralar = tempSeralar;
        sera_descriptions = tempSeraDescriptions;
        images = tempImages;
        temperatures = tempTemperatures;


    }
}