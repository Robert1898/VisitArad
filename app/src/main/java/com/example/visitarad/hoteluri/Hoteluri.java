package com.example.visitarad.hoteluri;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.visitarad.CommonMethods;
import com.example.visitarad.OnClickInterface;
import com.example.visitarad.R;
import com.example.visitarad.RecyclerAdapterView;

import java.util.ArrayList;

public class Hoteluri extends AppCompatActivity implements OnClickInterface {

    private RecyclerView recyclerViewHotel;
    private RecyclerAdapterView hoteluriAdapter;
    private ArrayList<String> textHotel = new ArrayList<>();
    private ArrayList<Integer> imgHotel = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoteluri);

        CommonMethods commonMethods = new CommonMethods();
        commonMethods.setTitleActionBar(Hoteluri.this, "Hoteluri");

        recyclerViewHotel = findViewById(R.id.recyclerViewHoteluri);

        recyclerViewHotel.setLayoutManager(new LinearLayoutManager(Hoteluri.this));

        textHotel.add("Hotel Coandi");
        imgHotel.add(R.drawable.hotel_coandi_arad);

        textHotel.add("Continental Forum Arad");
        imgHotel.add(R.drawable.contihotel);


        textHotel.add("Hotel Maxim");
        imgHotel.add(R.drawable.hotel_maxim_arad);

        textHotel.add("ibis Styles Arad");
        imgHotel.add(R.drawable.hotel_ibis);

        textHotel.add("Hotel President");
        imgHotel.add(R.drawable.hotel_president);

        textHotel.add("Hotel Miky");
        imgHotel.add(R.drawable.hotel_miky);

        textHotel.add("Hotel Iris");
        imgHotel.add(R.drawable.hotel_iris);

        textHotel.add("Hotel Leon & Spa");
        imgHotel.add(R.drawable.hotel_leon);

        textHotel.add("Best Western Central Hotel");
        imgHotel.add(R.drawable.hotel_western);

        textHotel.add("Hotel Darosy");
        imgHotel.add(R.drawable.hotel_darosi);


        hoteluriAdapter = new RecyclerAdapterView(textHotel,imgHotel,Hoteluri.this,this);
        recyclerViewHotel.setAdapter(hoteluriAdapter);



    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getApplicationContext(), Hotel.class);
        intent.putExtra("HotelID", textHotel.get(position));
        startActivity(intent);
    }
}