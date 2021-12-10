package com.e_carter.userroles;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Forgot extends AppCompatActivity  {

    EditText email;
    Button forgotBtn;
    //    firebase
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        // setup firebase
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        email = findViewById(R.id.forgotEmail);
        forgotBtn = findViewById(R.id.forgotBtn);


        forgotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               /* fAuth.sendPasswordResetEmail(String.valueOf(email))
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText( Forgot.this,"Email berhasil dikirim",Toast.LENGTH_LONG ).show();
                                } else {
                                    Toast.makeText( Forgot.this,"Email gagal dikirim",Toast.LENGTH_LONG ).show();
                                }
                            }
                        });*/

                FirebaseAuth auth = FirebaseAuth.getInstance();
//                String emailAddress = "";
                String emailAddress = email.getText().toString();

                auth.sendPasswordResetEmail(emailAddress)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
//                                    Log.d(TAG, "Email sent.");
                                    Toast.makeText( Forgot.this,"Email berhasil dikirim",Toast.LENGTH_LONG ).show();
                                }
                            }
                        });
            }
        });


        Button klikLogin = findViewById(R.id.gotoLogin);
        klikLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goLogin = new Intent(Forgot.this, Login.class);
                startActivity(goLogin);
                finish();
            }
        });










    }

}
