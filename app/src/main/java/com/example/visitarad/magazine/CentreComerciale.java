package com.example.visitarad.magazine;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.visitarad.OnClickInterface;
import com.example.visitarad.R;
import com.example.visitarad.RecyclerAdapterView;

import java.util.ArrayList;

public class CentreComerciale extends AppCompatActivity implements OnClickInterface {

    RecyclerView recyclerViewMagazine;
    RecyclerAdapterView adapterMagazine;
    ArrayList<String> txtMagazin = new ArrayList<>();
    ArrayList<Integer> imgMagazin = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_centre_comerciale);

        recyclerViewMagazine = findViewById(R.id.recyclerViewMagazine);
        recyclerViewMagazine.setLayoutManager(new LinearLayoutManager(CentreComerciale.this));


        txtMagazin.add("Atrium Mall");
        txtMagazin.add("Remarkt");
        txtMagazin.add("Selgros");
        txtMagazin.add("Altex");


        imgMagazin.add(R.drawable.atrium);
        imgMagazin.add(R.drawable.remarkt);
        imgMagazin.add(R.drawable.selgros);
        imgMagazin.add(R.drawable.altex);

        adapterMagazine = new RecyclerAdapterView(txtMagazin,imgMagazin,CentreComerciale.this,this);
        recyclerViewMagazine.setAdapter(adapterMagazine);

    }

    @Override
    public void onItemClick(int position) {


        String magazin = txtMagazin.get(position);
        if ("Atrium Mall".equals(magazin)){
            Intent intent0 = new Intent(CentreComerciale.this, AtriumMall.class);
            startActivity(intent0);

        }else {
            Intent intent = new Intent(CentreComerciale.this, Magazin.class);
            intent.putExtra("magazinID", txtMagazin.get(position));
            startActivity(intent);

        }
    }
}