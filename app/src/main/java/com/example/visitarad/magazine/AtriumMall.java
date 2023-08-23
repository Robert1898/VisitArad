package com.example.visitarad.magazine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.visitarad.CommonMethods;
import com.example.visitarad.ImageAdapter;
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

import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;

public class AtriumMall extends AppCompatActivity implements OnMapReadyCallback {

    private ModelMagazin magazin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.magazin_activity_atrium_mall);

        ArrayList<String> imgMag = new ArrayList<>();
        CommonMethods setTitle = new CommonMethods();


        CardView kfc = findViewById(R.id.cardViewKfc);
        CardView treiF = findViewById(R.id.cardView3F);
        CardView tucano = findViewById(R.id.cardViewTucano);
        CardView HM = findViewById(R.id.cardViewHM);
        CardView hervis = findViewById(R.id.cardViewHervis);
        CardView bear = findViewById(R.id.cardViewBear);
        CardView MAI = findViewById(R.id.cardViewMAI);
        CardView orange = findViewById(R.id.cardViewOrange);
        CardView BT = findViewById(R.id.cardViewBT);
        TextView despreMag = findViewById(R.id.despreAtrium);
        Button btnFacebook = findViewById(R.id.butonFacebookAtrium);
        Button btnWeb = findViewById(R.id.butonSiteWebAtrium);




        ViewPager2 pager2 = findViewById(R.id.imgAtrium);

        imgMag.add("https://scontent.ftsr1-1.fna.fbcdn.net/v/t39.30808-6/278572694_5169498209756556_9025879869535422245_n.jpg?_nc_cat=100&cb=99be929b-59f725be&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=JueF0cZpcW8AX-uksHT&_nc_ht=scontent.ftsr1-1.fna&oh=00_AfCbnqNmxOtQyqok6A675XrDIdgEh_bX_JluoxyvwSZ_FA&oe=64DA20BE");
        imgMag.add("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fspecialarad.ro%2Fwp-content%2Fuploads%2F2020%2F06%2Fatrium-mall-interior-1060x540.jpg&f=1&nofb=1&ipt=10a3c75c671a533027a66a6ea32d348c5040a3bc0a278dccadb4c8815d6f9d3a&ipo=images");
        imgMag.add("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fspecialarad.ro%2Fwp-content%2Fuploads%2F2020%2F06%2Fbenvenuti-1024x540.jpg&f=1&nofb=1&ipt=0ee6565845472d95d154e7b30743132ee6c80d7e4c1cfb4a65d7a81500d01934&ipo=images");
        imgMag.add("https://scontent.ftsr1-2.fna.fbcdn.net/v/t39.30808-6/330399148_1329484384575655_5207109631776719439_n.jpg?_nc_cat=104&cb=99be929b-59f725be&ccb=1-7&_nc_sid=730e14&_nc_ohc=1tDpaUbcAWYAX9XgzJl&_nc_ht=scontent.ftsr1-2.fna&oh=00_AfBpxW8Av9udWMduZxzS0MbDxg2Z6ckQI54VxXLE_wovYQ&oe=64D9F06E");
        imgMag.add("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fspecialarad.ro%2Fwp-content%2Fuploads%2F2021%2F05%2FCinema-City.jpg&f=1&nofb=1&ipt=15e05f8eea9df42d9e1603dd6814810ff3d47be74312a2d43c0b9a9828f0032d&ipo=images");
        imgMag.add("https://scontent.ftsr1-2.fna.fbcdn.net/v/t39.30808-6/333233968_926556342110888_2697242770605819705_n.jpg?_nc_cat=104&cb=99be929b-59f725be&ccb=1-7&_nc_sid=730e14&_nc_ohc=1eTM05ZyrKQAX_L7LmW&_nc_ht=scontent.ftsr1-2.fna&oh=00_AfDuN0A3FcVhDJESc9RXlODKsK5QalbgoLBvZyaKiQRzxQ&oe=64DA17C7");
        imgMag.add("https://scontent.ftsr1-2.fna.fbcdn.net/v/t39.30808-6/361931973_654007640089101_1530978526089315062_n.jpg?_nc_cat=103&cb=99be929b-59f725be&ccb=1-7&_nc_sid=730e14&_nc_ohc=LnV4uRNmeBoAX-ryTi7&_nc_ht=scontent.ftsr1-2.fna&oh=00_AfBTklfiRivvwtyt6e7ICD-eAjpabKSlNMahHyuKyR3YKA&oe=64D9D42A");
        imgMag.add("https://scontent.ftsr1-1.fna.fbcdn.net/v/t39.30808-6/338120892_196535876439954_5973500997346656611_n.jpg?_nc_cat=101&cb=99be929b-59f725be&ccb=1-7&_nc_sid=730e14&_nc_ohc=gPlGvZemIQ0AX-xfOIz&_nc_ht=scontent.ftsr1-1.fna&oh=00_AfCJNHKNRmTycFSB0W9xZsh5UKYPAXbm9BThT4hP73Ia9g&oe=64D94D2F");
        imgMag.add("https://scontent.ftsr1-2.fna.fbcdn.net/v/t39.30808-6/336480955_234380889052001_4151551018067095046_n.jpg?_nc_cat=107&cb=99be929b-59f725be&ccb=1-7&_nc_sid=7f8c78&_nc_ohc=R4DB6sILvs0AX8PUY0A&_nc_ht=scontent.ftsr1-2.fna&oh=00_AfBzjpGfvowf4QxcipRE92TWvW8684mhAEzYIQhXWz5eYw&oe=64DA217D");


       FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("Magazine").document("AtriumMall");

        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                if (documentSnapshot.exists()){
                    String nume = documentSnapshot.getString("nume");
                    String id = documentSnapshot.getId();
                    String facebook = documentSnapshot.getString("facebook");
                    String mail = documentSnapshot.getString("mail");
                    String insta = documentSnapshot.getString("instagram");
                    String contact = documentSnapshot.getString("contact");
                    String site = documentSnapshot.getString("site");
                    ArrayList<String> imagine =(ArrayList<String>) documentSnapshot.get("imagini");
                     String orarKfc = documentSnapshot.getString("orarKfc");
                    String orar3F = documentSnapshot.getString("orar3F");
                    String orarTucano = documentSnapshot.getString("orarTucano");
                    String orarHM = documentSnapshot.getString("orarHM");
                    String orarHervis = documentSnapshot.getString("orarHervis");
                    String orarBear = documentSnapshot.getString("orarBear");
                    String orarMAI = documentSnapshot.getString("orarMAI").replace("\\n","\n");
                    String orarOrange = documentSnapshot.getString("orarOrange");
                    String orarBT = documentSnapshot.getString("orarBT").replace("\\n","\n");
                     String atrium = documentSnapshot.getString("despre").replace("\\n","\n");
                     double latitudine = documentSnapshot.getDouble("latitudine");
                     double longitudine = documentSnapshot.getDouble("longitudine");
                     despreMag.setText(atrium);


                     magazin = new ModelMagazin(id, nume, atrium, null, latitudine, longitudine, facebook, insta, site,mail,contact );

                    setTitle.setTitleActionBar(AtriumMall.this, magazin.getName());
                    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapAtrium);
                    mapFragment.getMapAsync(AtriumMall.this);

                    ImageAdapter imageAdapter = new ImageAdapter(imagine, AtriumMall.this);
                    pager2.setAdapter(imageAdapter);

                    btnFacebook.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            CommonMethods.onClickUrl(AtriumMall.this, magazin.getFacebook());
                        }
                    });

                    btnWeb.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            CommonMethods.onClickUrl(AtriumMall.this, magazin.getSite());
                        }
                    });

                    if (magazin.getFacebook() == null || magazin.getFacebook().isEmpty()) {
                        btnFacebook.setVisibility(View.GONE);
                    }

                    if (magazin.getSite() == null || magazin.getSite().isEmpty()) {
                        btnWeb.setVisibility(View.GONE);
                    }



                    kfc.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setDialogMessage(orarKfc);
                        }
                    });

                    treiF.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setDialogMessage(orar3F);
                        }
                    });
                    tucano.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setDialogMessage(orarTucano);
                        }
                    });
                    HM.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setDialogMessage(orarHM);
                        }
                    });
                    hervis.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setDialogMessage(orarHervis);
                        }
                    });
                    bear.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setDialogMessage(orarBear);
                        }
                    });
                    MAI.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setDialogMessage(orarMAI);
                        }
                    });
                    BT.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setDialogMessage(orarBT);
                        }
                    });
                    orange.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setDialogMessage(orarOrange);
                        }
                    });


                } else Log.d("Firestore", "No such document");

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("Firestore", "Error getting document", e);
            }
        });

    }

    private void setDialogMessage(String orar){
        if (orar == null || orar.isEmpty()) {
            Toast.makeText(AtriumMall.this, "Orarul nu este disponibil momentan.", Toast.LENGTH_SHORT).show();
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(AtriumMall.this);
        builder.setTitle("Program");
        builder.setMessage(orar);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        ScrollView scrollView = findViewById(R.id.scrollViewAtrium);
        CommonMethods marker = new CommonMethods();
        LatLng pozitie = new LatLng(magazin.getLatitude(), magazin.getLongitude());
        marker.getGoogleMapsMarker(googleMap, pozitie,magazin.getName(), 17);

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