package com.example.visitarad.obiective;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.visitarad.OnClickInterface;
import com.example.visitarad.R;
import com.example.visitarad.RecyclerAdapterObiectiveTuristice;

import java.util.ArrayList;

public class ObiectiveTuristice extends AppCompatActivity implements OnClickInterface {

    private RecyclerView recyclerViewObiective;
    //creez o lista pentru fiecare element, in cazul meu pentru text si pentru imagine
    private ArrayList<String> denumireObiectiv = new ArrayList<>();
    private ArrayList<Integer> imagineObiectiv = new ArrayList<>();
    RecyclerAdapterObiectiveTuristice adapterObiectiveTuristice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obiective_turistice);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar!= null){
            actionBar.setTitle("Obiective turistice");
        }

        recyclerViewObiective = findViewById(R.id.recyclerViewObiectiveTurtistice);

        //determinam cum apar datele pe ecran
        recyclerViewObiective.setLayoutManager(new LinearLayoutManager(ObiectiveTuristice.this));

        denumireObiectiv.add("Palatul Administrativ");
        imagineObiectiv.add(R.drawable.primaria_arad_obiective);

        denumireObiectiv.add("Teatrul Clasic \"Ioan Slavici\"");
        imagineObiectiv.add(R.drawable.teatrul_arad_obiective);


        denumireObiectiv.add("Parcul Natural Lunca Mureșului");
        imagineObiectiv.add(R.drawable.obiectiv_lunca);


        denumireObiectiv.add("Grădina Botanică Macea");
        imagineObiectiv.add(R.drawable.obiective_gr_botanica_macea);


        denumireObiectiv.add("Palatul Cenad");
        imagineObiectiv.add(R.drawable.obiective_cenad);


        denumireObiectiv.add("Moneasa");
        imagineObiectiv.add(R.drawable.obiective_moneasa);

        denumireObiectiv.add("Biserica Sf. Anton de Padova");
        imagineObiectiv.add(R.drawable.obiective_padova_biserica);

        denumireObiectiv.add("Stadionul Francisc Neuman");
        imagineObiectiv.add(R.drawable.stadion_uta);


        adapterObiectiveTuristice = new RecyclerAdapterObiectiveTuristice(denumireObiectiv,imagineObiectiv, ObiectiveTuristice.this, this);
        recyclerViewObiective.setAdapter(adapterObiectiveTuristice);
    }

    @Override
    public void onItemClick(int position) {

        Intent intent = new Intent(getApplicationContext(), Obiectiv.class);
        intent.putExtra("obiectivID", denumireObiectiv.get(position));
        startActivity(intent);
    }
}