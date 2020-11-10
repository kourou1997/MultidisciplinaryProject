package com.cdtp.smartgreenhouse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//Listeyi oluşturacak class
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    String seralar[], sera_descriptions[];
    int images[];
    Context context;

    //Constructor yazıyoruz
    public MyAdapter(Context ct, String seralar[], String sera_descriptions[], int images[]){
        context = ct;
        this.seralar = seralar;
        this.sera_descriptions = sera_descriptions;
        this.images = images;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.seraTextView.setText(seralar[position]);
        holder.seraDescriptionTextView.setText(sera_descriptions[position]);
        holder.imageView.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return seralar.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView seraTextView, seraDescriptionTextView;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            seraTextView = itemView.findViewById(R.id.seraTextView);
            seraDescriptionTextView = itemView.findViewById(R.id.seraDescriptionTextView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
