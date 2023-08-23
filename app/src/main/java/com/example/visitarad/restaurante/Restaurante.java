package com.example.visitarad.restaurante;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.visitarad.CommonMethods;
import com.example.visitarad.OnClickInterface;
import com.example.visitarad.R;
import com.example.visitarad.RecyclerAdapterView;

import java.util.ArrayList;

public class Restaurante extends AppCompatActivity implements OnClickInterface {

    private RecyclerView recyclerViewRestaurante;

    private ArrayList<String> denumireRestaurant = new ArrayList<>();
    private ArrayList<Integer> imagineRestaurant = new ArrayList<>();
    RecyclerAdapterView restaurante;
    CommonMethods setTitlu = new CommonMethods();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurante);
        setTitlu.setTitleActionBar(this, "Restaurante");


        // CommonMethods.set
        recyclerViewRestaurante = findViewById(R.id.recyclerViewRestaurante);

        recyclerViewRestaurante.setLayoutManager(new LinearLayoutManager(Restaurante.this)); //aranjam elementele liniar

        denumireRestaurant.add("La Bottega Beer&Grill");
        imagineRestaurant.add(R.drawable.restaurante_bottega);

        denumireRestaurant.add("Ema Del Mar Arad");
        imagineRestaurant.add(R.drawable.restaurant_ema_del_mar);

        denumireRestaurant.add("Restaurant Krill");
        imagineRestaurant.add(R.drawable.restaurant_krill);

        denumireRestaurant.add("Euphoria Biergarten");
        imagineRestaurant.add(R.drawable.restaurant_euphoria);

        denumireRestaurant.add("Grenier Brasserie");
        imagineRestaurant.add(R.drawable.restaurant_grenier);

        denumireRestaurant.add("Restaurant Il Classico");
        imagineRestaurant.add(R.drawable.restaurant_classico);

        denumireRestaurant.add("Restaurant Ratio");
        imagineRestaurant.add(R.drawable.restaurant_ratio_beach);

        denumireRestaurant.add("Momo's Burgers");
        imagineRestaurant.add(R.drawable.restaurant_momo);

        denumireRestaurant.add("Ristorante Picasso");
        imagineRestaurant.add(R.drawable.restaurant_picasso);

        denumireRestaurant.add("Restaurant Libanez");
        imagineRestaurant.add(R.drawable.restaurant_libanez);

        restaurante  = new RecyclerAdapterView(denumireRestaurant, imagineRestaurant, Restaurante.this, this);
        recyclerViewRestaurante.setAdapter(restaurante);

    }
    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getApplicationContext(), Restaurant.class);
        intent.putExtra("restaurantID", denumireRestaurant.get(position));
        startActivity(intent);

    }
}