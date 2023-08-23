package com.example.visitarad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivityLogin extends AppCompatActivity {
    EditText _txtEmail;
    EditText _txtPass;
    Button _lgnbutton, creareCont;
    TextView textFaraCont;

    //Firebase Authentication
    private FirebaseAuth mAuth;
   // private FirebaseAuth.AuthStateListener authStateListener;
   // private FirebaseUser user;



    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(getApplicationContext(), PaginaPrincipala.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        _txtEmail = findViewById(R.id.txtEmail);
        _txtPass = findViewById(R.id.txtPass);
        _lgnbutton = findViewById(R.id.lgnbutton);
        creareCont = findViewById(R.id.butonInregistrare);
        mAuth = FirebaseAuth.getInstance();
        textFaraCont = findViewById(R.id.textViewFaraCont);

        _lgnbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = _txtEmail.getText().toString().trim();
                String password = _txtPass.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(MainActivityLogin.this, "Introduceti adresa de email", Toast.LENGTH_SHORT).show();

                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(MainActivityLogin.this, "Introduceti parola", Toast.LENGTH_SHORT).show();
                    return;
                }


                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(MainActivityLogin.this, "Succes",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(MainActivityLogin.this, PaginaPrincipala.class);
                                    startActivity(intent);
                                    finish();
                                    //FirebaseUser user = mAuth.getCurrentUser();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(MainActivityLogin.this, "Eroare",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });


        creareCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCreareCont = new Intent(MainActivityLogin.this, CreareCont.class);
                startActivity(intentCreareCont);
            }
        });

        textFaraCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // String item = _spinner.getSelectedItem().toString();
                    Intent intent = new Intent(MainActivityLogin.this, PaginaPrincipala.class);
                    startActivity(intent);
            }
        });


    }
}


