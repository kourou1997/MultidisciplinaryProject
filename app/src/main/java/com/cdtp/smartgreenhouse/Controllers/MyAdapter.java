package com.cdtp.smartgreenhouse.Controllers;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.cdtp.smartgreenhouse.R;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

//Listeyi oluşturacak class
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


    String seralar[], sera_descriptions[];
    int images[];
    double temperatures[];
    Context context;

    //Constructor yazıyoruz
    public MyAdapter(Context ct, String seralar[], String sera_descriptions[], int images[], double temperatures[]){
        context = ct;
        this.seralar = seralar;
        this.sera_descriptions = sera_descriptions;
        this.images = images;
        this.temperatures = temperatures;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.seraTextView.setText(seralar[position]);
        holder.seraDescriptionTextView.setText(sera_descriptions[position]);
        holder.imageView.setImageResource(images[position]);
        holder.tempTextView.setText(""+temperatures[position] + "°C");

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("sera", seralar[position]);
                intent.putExtra("sera_detail", sera_descriptions[position]);
                intent.putExtra("image", images[position]);
                intent.putExtra("temperature", temperatures[position]);
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return seralar.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView seraTextView, seraDescriptionTextView, tempTextView;
        ImageView imageView;
        ConstraintLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            seraTextView = itemView.findViewById(R.id.seraTextView);
            seraDescriptionTextView = itemView.findViewById(R.id.seraDescriptionTextView);
            imageView = itemView.findViewById(R.id.imageView);
            tempTextView = itemView.findViewById(R.id.tempTextView);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
