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

public class RecyclerAdapterObiectiveTuristice extends RecyclerView.Adapter<RecyclerAdapterObiectiveTuristice.ObiectiveViewHolder>{

    private ArrayList<String> denumireObiectiv;
    private ArrayList<Integer> imagineObiectiv;

    private Context context;

    private OnClickInterface iterfataObiective;

    public RecyclerAdapterObiectiveTuristice(ArrayList<String> denumireObiectiv, ArrayList<Integer> imagineObiectiv, Context context, OnClickInterface interfataObiective) {
        this.denumireObiectiv = denumireObiectiv;
        this.imagineObiectiv = imagineObiectiv;
        this.context = context;
        this.iterfataObiective = interfataObiective;
    }

    @NonNull
    @Override
    public ObiectiveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //in aceasta metoda definim card design-ul pe care l-am facut
        //1- cream un obiect din clasa View si transferam design-ul cardview-ului in el folosind LayoutInflater
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_obiective_turistice, parent, false); // aici facem link cu designul pe care l-am creat
        //va transforma fișierul XML al layout-ului activity_obiective_turistice într-un obiect View pe care Android îl poate desena pe ecran.
        return new ObiectiveViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ObiectiveViewHolder holder, int position) {
        //in aceasta metoda afisam datele pe ecran in recycler view
        holder.textObiective.setText(denumireObiectiv.get(position));
        holder.imagineObiective.setImageResource(imagineObiectiv.get(position));


    }

    @Override
    public int getItemCount() {
        //aici specificam cat sa fie afisat in recycler view
        return imagineObiectiv.size();
    }

    //aceasta clasa va reprezenta design-ul cardului si avem toate referintele la
    //componentele vizuale

    public class ObiectiveViewHolder extends RecyclerView.ViewHolder {

        //aceasta clasa reprezinta designul cardului
        TextView textObiective;
        ImageView imagineObiective;
        CardView cardView;

        public ObiectiveViewHolder(@NonNull View itemView) {
            //match-uim componentele cu id-ul lor
            //cu item view accesam item-ul cardului pe care l-am facut
            super(itemView);
            textObiective = itemView.findViewById(R.id.textViewObiective);
            imagineObiective = itemView.findViewById(R.id.imageViewObiective);
            cardView = itemView.findViewById(R.id.cardViewObiective);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(iterfataObiective != null){
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            iterfataObiective.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
