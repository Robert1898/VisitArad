package com.example.visitarad.hoteluri;

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
import com.example.visitarad.obiective.ModelObiective;
import com.example.visitarad.obiective.Obiectiv;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.FileReader;
import java.util.ArrayList;

public class Hotel extends AppCompatActivity implements OnMapReadyCallback {

    private ModelHotel hotel;
    private CommonMethods commonMethods = new CommonMethods();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);

        ViewPager2 pager2 = findViewById(R.id.imgHotel);
        Button btnContact  = findViewById(R.id.butonTelefonHotel);
        Button btnFacebook  = findViewById(R.id.butonFacebookHotel);
        Button btnInsta  = findViewById(R.id.butonInstaHotel);
        Button btnWeb  = findViewById(R.id.butonSiteWebHotel);
        TextView descriereHotel = findViewById(R.id.descriereHotel);
        Button mail = findViewById(R.id.butonMailHotel);
        TextView facilitateHotel = findViewById(R.id.facilitatiHotel);

        String hotelID = getIntent().getStringExtra("HotelID");

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("Hoteluri").document(hotelID);

        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){

                        String id = documentSnapshot.getId();
                        String numeHotel = documentSnapshot.getString("nume");
                        String despreHotel = documentSnapshot.contains("despre") ? documentSnapshot.getString("despre").replace("\\n", "\n") : "";
                        String facilitatiHotel = documentSnapshot.contains("facilitati") ? documentSnapshot.getString("facilitati").replace("\\n", "\n") : "";
                        String contact = documentSnapshot.getString("contact");
                        String facebook = documentSnapshot.getString("facebook");
                        ArrayList<String> imagini = (ArrayList<String>) documentSnapshot.get("imagini");
                        String insta = documentSnapshot.getString("instagram");
                        //String imagineCard = documentSnapshot.getString("imagineCard");
                        String getMail = documentSnapshot.getString("mail");
                        String site = documentSnapshot.getString("site");
                        Double latitudine = documentSnapshot.getDouble("latitudine");
                        Double longitudine = documentSnapshot.getDouble("longitudine");

                          hotel = new ModelHotel(id,numeHotel,despreHotel,latitudine,longitudine,facebook,insta,site,getMail,contact,facilitatiHotel);

                        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapHotel);
                        mapFragment.getMapAsync(Hotel.this);

                        commonMethods.setTitleActionBar(Hotel.this, hotel.getName());
                        descriereHotel.setText(hotel.getDescriere());
                        facilitateHotel.setText(hotel.getFacilitati());

                        ImageAdapter imageAdapter = new ImageAdapter(imagini, Hotel.this);
                        pager2.setAdapter(imageAdapter);

                        btnContact.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                CommonMethods.onClickContact(Hotel.this, hotel.getContact());
                            }
                        });
                        btnInsta.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                CommonMethods.onClickUrl(Hotel.this, hotel.getInsta());
                            }
                        });
                        btnWeb.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                CommonMethods.onClickUrl(Hotel.this, hotel.getSite());
                            }
                        });
                        btnFacebook.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                CommonMethods.onClickUrl(Hotel.this, hotel.getFacebook());
                            }
                        });

                        mail.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                CommonMethods.onClickMail(Hotel.this, hotel.getMail());
                            }
                        });

                        if (hotel.getContact() == null || hotel.getContact().isEmpty()) {
                            btnContact.setVisibility(View.GONE);
                        }

                        if (hotel.getFacebook() == null || hotel.getFacebook().isEmpty()) {
                            btnFacebook.setVisibility(View.GONE);
                        }

                        if (hotel.getInsta() == null || hotel.getInsta().isEmpty()) {
                            btnInsta.setVisibility(View.GONE);
                        }

                        if (hotel.getSite() == null || hotel.getSite().isEmpty()) {
                            btnWeb.setVisibility(View.GONE);
                        }

                        if (hotel.getMail() == null || hotel.getMail().isEmpty()) {
                            mail.setVisibility(View.GONE);
                        }

                    }
                }
        });

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        ScrollView scrollView = findViewById(R.id.scrollViewHotel);
        LatLng pozitie = new LatLng(hotel.getLatitude(),hotel.getLongitude());
        commonMethods.getGoogleMapsMarker(googleMap, pozitie, hotel.getName(),16);

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