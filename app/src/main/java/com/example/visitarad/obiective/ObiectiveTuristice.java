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
        /*switch (position){
            case 0:
                Intent intent = new Intent(this, ObiectivePalatulAdministrativ.class);
                startActivity(intent);
                break;

            case 1:
                Intent intent1 = new Intent(this, ObiectiveTeatru.class);
                startActivity(intent1);
                break;

            case 2:
                Intent intent2 = new Intent(this, ObiectiveLunca.class);
                startActivity(intent2);
                break;

            case 3:
                Intent intent3 = new Intent(this, ObiectiveMacea.class);
                startActivity(intent3);
                break;


            case 4:
                Intent intent4 = new Intent(this, ObiectiveCenad.class);
                startActivity(intent4);
                break;

            case 5:
                Intent intent5 = new Intent(this, ObiectiveMoneasa.class);
                startActivity(intent5);
                break;

            case 6:
                Intent intent6 = new Intent(this, ObiectiveAntonPadova.class);
                startActivity(intent6);
                break;

            case 7:
                Intent intent7 = new Intent(this, ObiectiveStadion.class);
                startActivity(intent7);
                break;
        }
*/
    }
}