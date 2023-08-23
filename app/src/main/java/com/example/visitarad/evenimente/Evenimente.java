package com.example.visitarad.evenimente;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.visitarad.OnClickInterface;
import com.example.visitarad.R;
import com.example.visitarad.RecyclerAdapterView;

import java.util.ArrayList;

public class Evenimente extends AppCompatActivity implements OnClickInterface {

    RecyclerView recyclerViewEvenimente;
    RecyclerAdapterView adapterEvenimente;
    ArrayList<String> txtEven = new ArrayList<>();
    ArrayList<Integer> imgEven = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evenimente);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar!= null){
            actionBar.setTitle("Evenimente");
        }

        recyclerViewEvenimente = findViewById(R.id.recyclerViewEvenimente);

        recyclerViewEvenimente.setLayoutManager(new LinearLayoutManager(Evenimente.this));



        txtEven.add("Stand-up Comedy Dan Badea");
        txtEven.add("Concert Farfarello");
        txtEven.add("Concert Trooper");
        txtEven.add("Frozen: Regatul Înghețat");
        txtEven.add("Lacrimi printre hohote de râs");
        imgEven.add(R.drawable.even_stand_up);
        imgEven.add(R.drawable.farfelo);
        imgEven.add(R.drawable.concert_trooper);
        imgEven.add(R.drawable.frozen);
        imgEven.add(R.drawable.hohote_ras);

        adapterEvenimente = new RecyclerAdapterView(txtEven,imgEven,Evenimente.this, this);
        recyclerViewEvenimente.setAdapter(adapterEvenimente);

    }

    @Override
    public void onItemClick(int position) {

            Intent intent  = new Intent(Evenimente.this, Eveniment.class);
            intent.putExtra("evenimentID", txtEven.get(position));
            startActivity(intent);



    }
}