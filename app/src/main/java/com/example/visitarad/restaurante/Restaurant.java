package com.example.visitarad.restaurante;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.visitarad.CommonMethods;
import com.example.visitarad.ImageAdapter;
import com.example.visitarad.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.idling.CountingIdlingResource;
import java.util.ArrayList;

public class Restaurant extends AppCompatActivity implements OnMapReadyCallback {

    private CommonMethods commonMethods = new CommonMethods();
    private ModelRestaurant restaurant;

    public CountingIdlingResource countingIdlingResource =
            new CountingIdlingResource("DATA_LOADER");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        ViewPager2 pager2 = findViewById(R.id.img);
        Button btnContact  = findViewById(R.id.butonTelefon);
        Button btnFacebook  = findViewById(R.id.butonFacebook);
        Button btnInsta  = findViewById(R.id.butonInsta);
        Button btnWeb  = findViewById(R.id.butonSiteWeb);
        TextView despreRestaurant = findViewById(R.id.descriere);
        Button mail = findViewById(R.id.butonMail);
        TextView orarRest = findViewById(R.id.orar);


        String restaurantID = getIntent().getStringExtra("restaurantID");

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("Restaurante").document(restaurantID);

        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                        String id = documentSnapshot.getId();
                        String numeRest = documentSnapshot.getString("nume");
                        String despreRest = documentSnapshot.getString("despre").replace("\\n", "\n");
                        String orar = documentSnapshot.getString("orar").replace("\\n", "\n");
                        String contact = documentSnapshot.getString("contact");
                        String facebook = documentSnapshot.getString("facebook");
                        ArrayList<String> imagini = (ArrayList<String>) documentSnapshot.get("imagini");
                        String insta = documentSnapshot.getString("instagram");
                        //String imagineCard = documentSnapshot.getString("imagineCard");
                        String getMail = documentSnapshot.getString("mail");
                        String site = documentSnapshot.getString("site");
                        Double latitudine = documentSnapshot.getDouble("latitudine");
                        Double longitudine = documentSnapshot.getDouble("longitudine");

                        restaurant = new ModelRestaurant(id,numeRest, despreRest, latitudine, longitudine, facebook, insta, site, getMail, contact,orar, imagini);

                    SupportMapFragment mapFragment  = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                    mapFragment.getMapAsync(Restaurant.this);

                    commonMethods.setTitleActionBar(Restaurant.this, restaurant.getName());

                        despreRestaurant.setText(restaurant.getDescriere());
                        orarRest.setText(restaurant.getOrar());

                      ImageAdapter imageAdapter = new ImageAdapter(imagini, Restaurant.this);
                      pager2.setAdapter(imageAdapter);

                        btnContact.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                CommonMethods.onClickContact(Restaurant.this, restaurant.getContact());
                            }
                        });
                        btnInsta.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                CommonMethods.onClickUrl(Restaurant.this, restaurant.getInsta());
                            }
                        });
                        btnWeb.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                CommonMethods.onClickUrl(Restaurant.this, restaurant.getSite());
                            }
                        });
                        btnFacebook.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                CommonMethods.onClickUrl(Restaurant.this, restaurant.getFacebook());
                            }
                        });

                        mail.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                CommonMethods.onClickMail(Restaurant.this, restaurant.getMail());
                            }
                        });

                    if (restaurant.getContact() == null || restaurant.getContact().isEmpty()) {
                        btnContact.setVisibility(View.GONE);
                    }

                    if (restaurant.getFacebook() == null || restaurant.getFacebook().isEmpty()) {
                        btnFacebook.setVisibility(View.GONE);
                    }

                    if (restaurant.getInsta() == null || restaurant.getInsta().isEmpty()) {
                        btnInsta.setVisibility(View.GONE);
                    }

                    if (restaurant.getSite() == null || restaurant.getSite().isEmpty()) {
                        btnWeb.setVisibility(View.GONE);
                    }

                    if (restaurant.getMail() == null || restaurant.getMail().isEmpty()) {
                        mail.setVisibility(View.GONE);
                    }

                }
                countingIdlingResource.decrement();
            }
        });
        countingIdlingResource.increment();


    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        ScrollView scrollView = findViewById(R.id.scrollView);
        LatLng pozitie = new LatLng(restaurant.getLatitude(),restaurant.getLongitude());
        commonMethods.getGoogleMapsMarker(googleMap, pozitie, restaurant.getName(),16);

        googleMap.setOnCameraMoveStartedListener(new GoogleMap.OnCameraMoveStartedListener() {
            @Override
            public void onCameraMoveStarted(int i) {
                CommonMethods.blockScrollView(scrollView);
            }
        });

        googleMap.setOnCameraMoveCanceledListener(new GoogleMap.OnCameraMoveCanceledListener() {
            @Override
            public void onCameraMoveCanceled() {
                CommonMethods.enableScrollView(scrollView);
            }
        });


    }
}