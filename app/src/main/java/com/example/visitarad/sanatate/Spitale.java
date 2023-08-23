package com.example.visitarad.sanatate;

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
import com.example.visitarad.hoteluri.Hotel;
import com.example.visitarad.hoteluri.ModelHotel;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;

public class Spitale extends AppCompatActivity implements OnMapReadyCallback {

    private CommonMethods commonMethods = new CommonMethods();
    private ModelSanatate sanatate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spitale);

        ViewPager2 pager2 = findViewById(R.id.imgSpitale);
        Button btnContact  = findViewById(R.id.butonTelefonSpitale);
        Button btnFacebook  = findViewById(R.id.butonFacebookSpitale);
        Button btnInsta  = findViewById(R.id.butonInstaSpitale);
        Button btnWeb  = findViewById(R.id.butonSiteWebSpitale);
        TextView descriereSpitale = findViewById(R.id.descriereSpitale);
        Button mail = findViewById(R.id.butonMailSpitale);
        TextView specializareSpitale = findViewById(R.id.specializariSpitale);
        TextView orarSpitale = findViewById(R.id.orarSpitale);
        TextView expandButtonSpitale = findViewById(R.id.expandButtonSpitale);

        String idSpitale = getIntent().getStringExtra("SpitaleID");

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("Sanatate").document(idSpitale);

        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){

                    String id = documentSnapshot.getId();
                    String numeSpital = documentSnapshot.getString("nume");
                    String despreSpital = documentSnapshot.contains("despre") ? documentSnapshot.getString("despre").replace("\\n", "\n") : "";
                    String specSpital = documentSnapshot.contains("specializare") ? documentSnapshot.getString("specializare").replace("\\n", "\n") : "";
                    String contact = documentSnapshot.getString("contact");
                    String facebook = documentSnapshot.getString("facebook");
                    String orar = documentSnapshot.contains("orar") ? documentSnapshot.getString("orar").replace("\\n", "\n") : "";
                    ArrayList<String> imagini = (ArrayList<String>) documentSnapshot.get("imagini");
                    String insta = documentSnapshot.getString("instagram");
                    //String imagineCard = documentSnapshot.getString("imagineCard");
                    String getMail = documentSnapshot.getString("mail");
                    String site = documentSnapshot.getString("site");
                    Double latitudine = documentSnapshot.getDouble("latitudine");
                    Double longitudine = documentSnapshot.getDouble("longitudine");

                    sanatate = new ModelSanatate(id,numeSpital,despreSpital,latitudine,longitudine,facebook,insta,site,getMail,contact,orar,specSpital);

                    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapSpitale);
                    mapFragment.getMapAsync(Spitale.this);

                    commonMethods.setTitleActionBar(Spitale.this, sanatate.getName());
                    descriereSpitale.setText(sanatate.getDescriere());
                    orarSpitale.setText(sanatate.getOrar());
                    specializareSpitale.setText(sanatate.getSpecializari());

                    ImageAdapter imageAdapter = new ImageAdapter(imagini, Spitale.this);
                    pager2.setAdapter(imageAdapter);

                    expandButtonSpitale.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            commonMethods.expandText(specializareSpitale, expandButtonSpitale);
                        }
                    });

                    btnContact.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            CommonMethods.onClickContact(Spitale.this, sanatate.getContact());
                        }
                    });
                    btnInsta.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            CommonMethods.onClickUrl(Spitale.this, sanatate.getInsta());
                        }
                    });
                    btnWeb.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            CommonMethods.onClickUrl(Spitale.this, sanatate.getSite());
                        }
                    });
                    btnFacebook.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            CommonMethods.onClickUrl(Spitale.this, sanatate.getFacebook());
                        }
                    });

                    mail.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            CommonMethods.onClickMail(Spitale.this, sanatate.getMail());
                        }
                    });

                    if (sanatate.getContact() == null || sanatate.getContact().isEmpty()) {
                        btnContact.setVisibility(View.GONE);
                    }

                    if (sanatate.getFacebook() == null || sanatate.getFacebook().isEmpty()) {
                        btnFacebook.setVisibility(View.GONE);
                    }

                    if (sanatate.getInsta() == null || sanatate.getInsta().isEmpty()) {
                        btnInsta.setVisibility(View.GONE);
                    }

                    if (sanatate.getSite() == null || sanatate.getSite().isEmpty()) {
                        btnWeb.setVisibility(View.GONE);
                    }

                    if (sanatate.getMail() == null || sanatate.getMail().isEmpty()) {
                        mail.setVisibility(View.GONE);
                    }

                }
            }
        });


    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        ScrollView scrollView = findViewById(R.id.scrollViewSpitale);
        LatLng pozitie = new LatLng(sanatate.getLatitude(),sanatate.getLongitude());
        commonMethods.getGoogleMapsMarker(googleMap, pozitie, sanatate.getName(),16);

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