package com.e_carter.userroles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    EditText fullName,email,password,phone;
    Button registerBtn,goToLogin;
    boolean valid = true;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

//        setup
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        fullName = findViewById(R.id.registerName);
        email = findViewById(R.id.registerEmail);
        password = findViewById(R.id.registerPassword);
        phone = findViewById(R.id.registerPhone);
        registerBtn = findViewById(R.id.registerBtn);
        goToLogin = findViewById(R.id.gotoLogin);

//        btn regist

        registerBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkField(fullName);
                checkField(email);
                checkField(password);
                checkField(phone);

                if (valid){
//            start the user regristration
                    fAuth.createUserWithEmailAndPassword( email.getText().toString(),password.getText().toString())
                            .addOnSuccessListener( new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    FirebaseUser user = fAuth.getCurrentUser();
                                    Toast.makeText( Register.this,"Akun berhasil dibuat",Toast.LENGTH_SHORT ).show();
                                    DocumentReference df = fStore.collection( "Users" ).document(user.getUid());
                                    Map<String,Object> userInfo = new HashMap<>();
                                    userInfo.put( "FullName",fullName.getText().toString() );
                                    userInfo.put( "UserEmail",email.getText().toString() );
                                    userInfo.put( "PhoneNumber",phone.getText().toString() );
//                            spesifik jika admin
                                    userInfo.put( "isUser","1" );
                                    df.set( userInfo );

                                    startActivity(new Intent(getApplicationContext(),UserActivity.class) );
                                    finish();

                                }
                            } ).addOnFailureListener( new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText( Register.this,"Gagal membuat akun",Toast.LENGTH_SHORT ).show();
                        }
                    } );
                }
            }
        } );
//        btn login
        goToLogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(getApplicationContext(),Login.class) );
            }
        } );

    }

    public boolean checkField(EditText textField){
        if(textField.getText().toString().isEmpty()){
            textField.setError("Error");
            valid = false;
        }else {
            valid = true;
        }

        return valid;
    }
}