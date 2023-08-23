package com.example.visitarad.evenimente;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.visitarad.CommonMethods;
import com.example.visitarad.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Eveniment extends AppCompatActivity implements OnMapReadyCallback {

    private CommonMethods commonMethods =new CommonMethods();
    private ModelEvenimente evenimente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eveniment);

        TextView despreEvent = findViewById(R.id.txtEveniment);
        Button btnBilete = findViewById(R.id.btnEveniment);
        ImageView imgEvent = findViewById(R.id.imgEveniment);

        String idEvent = getIntent().getStringExtra("evenimentID");

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("Evenimente").document(idEvent);

        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){

                        String nume = documentSnapshot.getString("nume");
                        String detalii = documentSnapshot.getString("despre").replace("\\n","\n");
                        String bilete = documentSnapshot.getString("bilete");
                        String afis = documentSnapshot.getString("imagine");
                        Double latitudine = documentSnapshot.getDouble("latitudine");
                        Double longitudine = documentSnapshot.getDouble("longitudine");
                        evenimente = new ModelEvenimente(nume,detalii, bilete, latitudine, longitudine);

                        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapEveniment);
                        mapFragment.getMapAsync(Eveniment.this);

                        commonMethods.setTitleActionBar(Eveniment.this, evenimente.getNume());

                        despreEvent.setText(detalii);


                        btnBilete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                CommonMethods.onClickUrl(Eveniment.this, evenimente.getUrlBilete());
                            }
                        });

                        Glide.with(Eveniment.this).load(afis).into(imgEvent);



                }
            }
        });

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        ScrollView scrollView = findViewById(R.id.scrollViewEveniment);
        LatLng pozitie = new LatLng(evenimente.getLatitudine(), evenimente.getLongitudine());
        commonMethods.getGoogleMapsMarker(googleMap,pozitie,evenimente.getNume(), 16 );

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