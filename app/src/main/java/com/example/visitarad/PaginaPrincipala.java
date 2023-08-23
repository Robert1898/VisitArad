package com.example.visitarad;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.visitarad.evenimente.Evenimente;
import com.example.visitarad.hoteluri.Hoteluri;
import com.example.visitarad.magazine.CentreComerciale;
import com.example.visitarad.obiective.ObiectiveTuristice;
import com.example.visitarad.restaurante.Restaurante;
import com.example.visitarad.sanatate.Sanatate;
import com.google.firebase.auth.FirebaseAuth;

public class PaginaPrincipala extends AppCompatActivity {
    CardView obTuristice,cardViewRestaurante,carViewHoteluri,getCarViewSanatate,cardViewEvenimente,cardViewMagazine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_principala);
        obTuristice = findViewById(R.id.idObTuristice);
        cardViewRestaurante = findViewById(R.id.cardViewRestaurante);
        carViewHoteluri = findViewById(R.id.cardViewHoteluri);
        getCarViewSanatate = findViewById(R.id.cardViewSanatate);
        cardViewEvenimente = findViewById(R.id.cardViewEvenimete);
        cardViewMagazine = findViewById(R.id.cardViewMagazine);
        obTuristice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ObiectiveTuristice.class);
                startActivity(intent);
            }
        });

        cardViewRestaurante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Restaurante.class);
                startActivity(intent);
            }
        });

        carViewHoteluri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Hoteluri.class);
                startActivity(intent);
            }
        });


        getCarViewSanatate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Sanatate.class);
                startActivity(intent);
            }
        });

        cardViewEvenimente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Evenimente.class);
                startActivity(intent);
            }
        });

        cardViewMagazine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CentreComerciale.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Log.d("DEBUG", "onBackPressed Called");
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Iesire")
                .setMessage("Esti sigur ca vrei sa iesi?")
                .setPositiveButton("Da", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();

                    }
                })
                .setNegativeButton("Nu", null)
                .show();
    }





    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.meniu_detalii, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()) {
            case R.id.deconectare:
                new AlertDialog.Builder(this)
                        .setTitle("Deconectare")
                        .setMessage("Ești sigur că vrei să te deconectezi?")
                        .setPositiveButton("Da", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseAuth.getInstance().signOut();
                                Intent intent = new Intent(PaginaPrincipala.this, MainActivityLogin.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("Nu", null)
                        .show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}