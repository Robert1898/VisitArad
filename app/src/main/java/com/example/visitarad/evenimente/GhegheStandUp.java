package com.example.visitarad.evenimente;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.visitarad.CommonMethods;
import com.example.visitarad.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class GhegheStandUp extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eveniment_activity_gheghe_stand_up);

        TextView txtEven = findViewById(R.id.txtGherghe);
        Button btnBilet = findViewById(R.id.btnGherghe);

        CommonMethods setTitle = new CommonMethods();
        setTitle.setTitleActionBar(GhegheStandUp.this, "Stand-up Comedy cu Gabriel Gherghe, Edi Vacariu, Paul Szabo și Bogdan Nonic");

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("Evenimente").document("Gherghe");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapGherghe);
        if (mapFragment != null){
            mapFragment.getMapAsync(GhegheStandUp.this);
        }
        else {
            Log.e("Gherghe", "Map fragmet is null");
        }

        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                    String text = documentSnapshot.getString("despre").replace("\\n","\n");
                    txtEven.setText(text);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("Frozen", "Eroare Firestore");
            }
        });

        btnBilet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonMethods.onClickUrl(GhegheStandUp.this, "https://www.iabilet.ro/bilete-arad-stand-up-comedy-cu-gabriel-gherghe-edi-vacariu-paul-szabo-si-bogdan-nonic-radem-glumim-86236/");
            }
        });

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        CommonMethods marker = new CommonMethods();

        ScrollView scrollView = findViewById(R.id.scrollViewGherghe);

        LatLng pozitie = new LatLng(46.17152440693017, 21.317375965150443);
        marker.getGoogleMapsMarker(googleMap, pozitie, "Club Flex", 18);


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