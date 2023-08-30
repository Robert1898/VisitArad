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

public class RecyclerAdapterView extends RecyclerView.Adapter<RecyclerAdapterView.CategoriViewHolder>{

    private ArrayList<String> denumire;
    private ArrayList<Integer> imagine;

    private Context context;
    OnClickInterface onClickInterface;

    public RecyclerAdapterView(ArrayList<String> denumire, ArrayList<Integer> imagine, Context context, OnClickInterface onClickInterface) {
        this.denumire = denumire;
        this.imagine = imagine;
        this.context = context;
        this.onClickInterface = onClickInterface;
    }

    @NonNull
    @Override
    public CategoriViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_restaurante,parent,false);
        return new CategoriViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriViewHolder holder, int position) {

        holder.text.setText(denumire.get(position));
        holder.image.setImageResource(imagine.get(position));
    }

    @Override
    public int getItemCount() {
        return imagine.size();
    }


    public class CategoriViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView text;
        private CardView cardView;

        public CategoriViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.textViewRestaurante);
            image = itemView.findViewById(R.id.imageViewRestaurante);
            cardView = itemView.findViewById(R.id.cardViewRestaurante);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos  = getAdapterPosition();
                    onClickInterface.onItemClick(pos);
                }
            });
        }
    }
}