package com.example.visitarad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapterView extends RecyclerView.Adapter<RecyclerAdapterView.RestauranteViewHolder>{

    private ArrayList<String> denumireRestaurant;
    private ArrayList<Integer> imagineRestaurant;

    private Context context;
    OnClickInterface onClickInterface;

    public RecyclerAdapterView(ArrayList<String> denumireRestaurant, ArrayList<Integer> imagineRestaurant, Context context, OnClickInterface onClickInterface) {
        this.denumireRestaurant = denumireRestaurant;
        this.imagineRestaurant = imagineRestaurant;
        this.context = context;
        this.onClickInterface = onClickInterface;
    }

    @NonNull
    @Override
    public RestauranteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_restaurante,parent,false);
        return new RestauranteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestauranteViewHolder holder, int position) {

        holder.textRestaurant.setText(denumireRestaurant.get(position));
        holder.imageRestaurant.setImageResource(imagineRestaurant.get(position));
    }

    @Override
    public int getItemCount() {
        return imagineRestaurant.size();
    }


    public class RestauranteViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageRestaurant;
        private TextView textRestaurant;
        private CardView cardViewRest;

        public RestauranteViewHolder(@NonNull View itemView) {
            super(itemView);
            textRestaurant = itemView.findViewById(R.id.textViewRestaurante);
            imageRestaurant = itemView.findViewById(R.id.imageViewRestaurante);
            cardViewRest = itemView.findViewById(R.id.cardViewRestaurante);

            cardViewRest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos  = getAdapterPosition();
                    onClickInterface.onItemClick(pos);
                }
            });
        }
    }
}