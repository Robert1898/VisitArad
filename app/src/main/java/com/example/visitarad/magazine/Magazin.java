package com.example.visitarad.magazine;

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

public class Magazin extends AppCompatActivity implements OnMapReadyCallback {

    private CommonMethods commonMethods = new CommonMethods();
    private ModelMagazin magazin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magazin);

        ViewPager2 pager2 = findViewById(R.id.imgMagazin);
        Button btnContact  = findViewById(R.id.butonTelefonMagazin);
        Button btnFacebook  = findViewById(R.id.butonFacebookMagazin);
        Button btnInsta  = findViewById(R.id.butonInstaMagazin);
        Button btnWeb  = findViewById(R.id.butonSiteWebMagazin);
        TextView despreMagazin = findViewById(R.id.descriereMagazin);
        Button mail = findViewById(R.id.butonMailMagazin);
        TextView orarMagazin = findViewById(R.id.orarMagazin);

        String idMagazin = getIntent().getStringExtra("magazinID");

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("Magazine").document(idMagazin);

        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    String id = documentSnapshot.getId();
                    String nume = documentSnapshot.getString("nume");
                    String despreMag = documentSnapshot.getString("despre").replace("\\n", "\n");
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

                    magazin = new ModelMagazin(id, nume, despreMag, orar, latitudine, longitudine, facebook, insta, site, getMail, contact);

                    SupportMapFragment mapFragment  = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapMagazin);
                    mapFragment.getMapAsync(Magazin.this);

                    commonMethods.setTitleActionBar(Magazin.this, magazin.getName());

                    despreMagazin.setText(magazin.getDescriere());
                    orarMagazin.setText(magazin.getOrar());

                    ImageAdapter imageAdapter = new ImageAdapter(imagini, Magazin.this);
                    pager2.setAdapter(imageAdapter);

                    btnContact.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            CommonMethods.onClickContact(Magazin.this, magazin.getContact());
                        }
                    });
                    btnInsta.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            CommonMethods.onClickUrl(Magazin.this, magazin.getInsta());
                        }
                    });
                    btnWeb.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            CommonMethods.onClickUrl(Magazin.this, magazin.getSite());
                        }
                    });
                    btnFacebook.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            CommonMethods.onClickUrl(Magazin.this, magazin.getFacebook());
                        }
                    });

                    mail.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            CommonMethods.onClickMail(Magazin.this, magazin.getMail());
                        }
                    });

                    if (magazin.getContact() == null || magazin.getContact().isEmpty()) {
                        btnContact.setVisibility(View.GONE);
                    }

                    if (magazin.getFacebook() == null || magazin.getFacebook().isEmpty()) {
                        btnFacebook.setVisibility(View.GONE);
                    }

                    if (magazin.getInsta() == null || magazin.getInsta().isEmpty()) {
                        btnInsta.setVisibility(View.GONE);
                    }

                    if (magazin.getSite() == null || magazin.getSite().isEmpty()) {
                        btnWeb.setVisibility(View.GONE);
                    }

                    if (magazin.getMail() == null || magazin.getMail().isEmpty()) {
                        mail.setVisibility(View.GONE);
                    }

                }
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        ScrollView scrollView = findViewById(R.id.scrollViewMagazin);
        LatLng pozitie = new LatLng(magazin.getLatitude(),magazin.getLongitude());
        commonMethods.getGoogleMapsMarker(googleMap, pozitie, magazin.getName(),16);

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