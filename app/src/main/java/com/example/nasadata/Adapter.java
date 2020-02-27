package com.example.nasadata;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder>{
    private List<Data> list;
    private Context context;

    public Adapter(List<Data> list,Context context){
        this.context = context;
        this.list = list;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView quotes,author;
        public ImageView image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
           // author = itemView.findViewById(R.id.author);
           // quotes = itemView.findViewById(R.id.quotes);
            image = itemView.findViewById(R.id.image);
        }
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_content, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Data data = list.get(i);
       // myViewHolder.quotes.setText(data.getQuotes());
       // myViewHolder.author.setText(data.getAuthor());
        Glide.with(context).load(data.getImage()).into(myViewHolder.image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}