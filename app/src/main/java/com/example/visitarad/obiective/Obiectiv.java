package com.example.visitarad.obiective;

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

import java.util.ArrayList;

public class Obiectiv extends AppCompatActivity implements OnMapReadyCallback {

    private CommonMethods commonMethods = new CommonMethods();
    private ModelObiective obiective;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obiectiv);

        ViewPager2 pager2 = findViewById(R.id.imgObiectiv);
        Button btnContact  = findViewById(R.id.butonTelefonObiectiv);
        Button btnFacebook  = findViewById(R.id.butonFacebookObiectiv);
        Button btnInsta  = findViewById(R.id.butonInstaObiectiv);
        Button btnWeb  = findViewById(R.id.butonSiteWebObiectiv);
        TextView despreObiectiv = findViewById(R.id.descriereObiectiv);
        Button mail = findViewById(R.id.butonMailObiectiv);
        TextView orarObiectiv = findViewById(R.id.orarObiectiv);

        String obiectivID = getIntent().getStringExtra("obiectivID");

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef  = db.collection("obiectiveTuristice").document(obiectivID);

        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if (documentSnapshot.exists()){
                        String id = documentSnapshot.getId();
                        String numeOb = documentSnapshot.getString("nume");
                        String despreOb = documentSnapshot.contains("despre") ? documentSnapshot.getString("despre").replace("\\n", "\n") : "";
                        String orar = documentSnapshot.contains("orar") ? documentSnapshot.getString("orar").replace("\\n", "\n") : "";
                        String contact = documentSnapshot.getString("contact");
                        String facebook = documentSnapshot.getString("facebook");
                        ArrayList<String> imagini = (ArrayList<String>) documentSnapshot.get("imagini");
                        String insta = documentSnapshot.getString("instagram");
                        //String imagineCard = documentSnapshot.getString("imagineCard");
                        String getMail = documentSnapshot.getString("mail");
                        String site = documentSnapshot.getString("site");
                        Double latitudine = documentSnapshot.getDouble("latitudine");
                        Double longitudine = documentSnapshot.getDouble("longitudine");

                        obiective = new ModelObiective(id,numeOb,despreOb,latitudine,longitudine,facebook,insta,site,getMail,contact,orar);

                        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapobiectiv);
                        mapFragment.getMapAsync(Obiectiv.this);

                        commonMethods.setTitleActionBar(Obiectiv.this, obiective.getName());
                        despreObiectiv.setText(obiective.getDescriere());
                        orarObiectiv.setText(obiective.getOrar());

                        ImageAdapter imageAdapter = new ImageAdapter(imagini, Obiectiv.this);
                        pager2.setAdapter(imageAdapter);

                        btnContact.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                CommonMethods.onClickContact(Obiectiv.this, obiective.getContact());
                            }
                        });
                        btnInsta.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                CommonMethods.onClickUrl(Obiectiv.this, obiective.getInsta());
                            }
                        });
                        btnWeb.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                CommonMethods.onClickUrl(Obiectiv.this, obiective.getSite());
                            }
                        });
                        btnFacebook.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                CommonMethods.onClickUrl(Obiectiv.this, obiective.getFacebook());
                            }
                        });

                        mail.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                CommonMethods.onClickMail(Obiectiv.this, obiective.getMail());
                            }
                        });

                        if (obiective.getContact() == null || obiective.getContact().isEmpty()) {
                            btnContact.setVisibility(View.GONE);
                        }

                        if (obiective.getFacebook() == null || obiective.getFacebook().isEmpty()) {
                            btnFacebook.setVisibility(View.GONE);
                        }

                        if (obiective.getInsta() == null || obiective.getInsta().isEmpty()) {
                            btnInsta.setVisibility(View.GONE);
                        }

                        if (obiective.getSite() == null || obiective.getSite().isEmpty()) {
                            btnWeb.setVisibility(View.GONE);
                        }

                        if (obiective.getMail() == null || obiective.getMail().isEmpty()) {
                            mail.setVisibility(View.GONE);
                        }

                    }
            }
        });





    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        ScrollView scrollView = findViewById(R.id.scrollViewObiectiv);
        LatLng pozitie = new LatLng(obiective.getLatitude(),obiective.getLongitude());
        commonMethods.getGoogleMapsMarker(googleMap, pozitie, obiective.getName(),16);

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