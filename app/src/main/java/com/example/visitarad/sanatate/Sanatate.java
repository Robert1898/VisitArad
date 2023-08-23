package com.example.visitarad.sanatate;

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

public class Sanatate extends AppCompatActivity implements OnClickInterface {

    RecyclerView recyclerViewSanatate;
    RecyclerAdapterView adapterSanatate;
    ArrayList<String> txtSanatate = new ArrayList<>();
    ArrayList<Integer> imgSanatate = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanatate);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar!= null){
            actionBar.setTitle("Sanatate");
        }

        recyclerViewSanatate = findViewById(R.id.recyclerViewSanatate);

        recyclerViewSanatate.setLayoutManager(new LinearLayoutManager(Sanatate.this));

        txtSanatate.add("Spitalul Judetean Arad");
        imgSanatate.add(R.drawable.spitalul_judetean_arad);

        txtSanatate.add("MedLife Genesys");
        imgSanatate.add(R.drawable.genesis);

        txtSanatate.add("Rubio");
        imgSanatate.add(R.drawable.rubio);

        txtSanatate.add("Policlinica AS");
        imgSanatate.add(R.drawable.poli_as);

        txtSanatate.add("Turcin");
        imgSanatate.add(R.drawable.turcin);

        adapterSanatate = new RecyclerAdapterView(txtSanatate, imgSanatate, Sanatate.this, this);
        recyclerViewSanatate.setAdapter(adapterSanatate);
    }

    @Override
    public void onItemClick(int position) {

        Intent intent = new Intent(getApplicationContext(), Spitale.class);
        intent.putExtra("SpitaleID", txtSanatate.get(position));
        startActivity(intent);
    }
}