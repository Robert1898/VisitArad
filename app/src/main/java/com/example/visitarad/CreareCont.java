package com.example.visitarad;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class CreareCont extends AppCompatActivity {


//    private DatabaseReference mDataBase;
    private Button btnCreareCont;
    private EditText editTextEmail;
    private EditText editTextPass;

    //Firebase Authentication
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser currentUser;

    //Firebase Connection
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection("Users");

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if(currentUser != null){

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creare_cont);

        btnCreareCont = findViewById(R.id.butonCreareCont);
        editTextEmail = findViewById(R.id.txtEmailCreareCont);
        editTextPass = findViewById(R.id.txtPassCreareCont);



        firebaseAuth = FirebaseAuth.getInstance();
        btnCreareCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPass.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(CreareCont.this, "Introduceti adresa de email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(CreareCont.this, "Introduceti parola", Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    //FirebaseUser user = firebaseAuth.getCurrentUser();
                                    Toast.makeText(CreareCont.this, "Succes", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), PaginaPrincipala.class);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(CreareCont.this, "Eroare",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });


            }
        });
       /* authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                currentUser = firebaseAuth.getCurrentUser();
                if (currentUser != null)
                {
                    //user already logged in

                } else{

                    //user not logged in
                }
            }
        };

        btnCreareCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(editTextEmail.getText().toString()) && !TextUtils.isEmpty(editTextPass.getText().toString())) {

                    String email = editTextEmail.getText().toString().trim();
                    String pass = editTextPass.getText().toString().trim();

                    //se creaza o noua metoda
                    createUserEmailAccount(email,pass);

                }
                else {
                    Toast.makeText(CreareCont.this, "Va rog sa introduceti toate datele", Toast.LENGTH_SHORT).show();
                }
            }
        });
        }

    private void createUserEmailAccount(String email, String pass) {
        if (!TextUtils.isEmpty(editTextEmail.getText().toString()) && !TextUtils.isEmpty(editTextPass.getText().toString())) {

            firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {



                }
            });

        }*/
    }
}
