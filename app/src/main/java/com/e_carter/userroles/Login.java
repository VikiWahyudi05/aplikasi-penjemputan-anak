package com.e_carter.userroles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login extends AppCompatActivity {
    EditText email,password;
    Button loginBtn,gotoRegister;
    boolean valid = true;
//    firebase
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        setup firebase
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        email = findViewById(R.id.loginEmail);
        password = findViewById(R.id.loginPassword);
        loginBtn = findViewById(R.id.loginBtn);
        gotoRegister = findViewById(R.id.gotoRegister);

        loginBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkField(email);
                checkField(password);

                if (valid){
                    fAuth.signInWithEmailAndPassword( email.getText().toString(),password.getText().toString() )
                            .addOnSuccessListener( new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    Toast.makeText( Login.this,"Sukses Login",Toast.LENGTH_SHORT ).show();
                                    checkUserAccessLevel(authResult.getUser().getUid());

                                }
                            } ).addOnFailureListener( new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText( Login.this,"Password salah",Toast.LENGTH_SHORT ).show();
                                }
                            } );

                }
            }
        } );

        gotoRegister.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(getApplicationContext(),Register.class) );
            }
        } );


        TextView klikForgot = findViewById(R.id.gotoForgot);
        klikForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goForgot = new Intent(Login.this, Forgot.class);
                startActivity(goForgot);
                finish();
            }
        });






    }

    private void checkUserAccessLevel(String uid) {
        DocumentReference df = fStore.collection( "Users" ).document(uid);
//       extract data
        df.get().addOnSuccessListener( new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("TAG","onSuccess: "+documentSnapshot.getData());
//                identify user acces
                if (documentSnapshot.getString( "isUser" )!= null){
//                    user is admin
                    Toast.makeText( Login.this,"Selamat datang User",Toast.LENGTH_LONG ).show();
                    startActivity( new Intent(getApplicationContext(),UserActivity.class) );
                    finish();
                }
                if (documentSnapshot.getString( "isAdmin" )!=null){
                    Toast.makeText( Login.this,"Selamat datang Admin",Toast.LENGTH_LONG ).show();
                    startActivity( new Intent(getApplicationContext(),Admin.class) );
                    finish();
                }
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

    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser()!=null){
            checkUserAccessLevel(FirebaseAuth.getInstance().getCurrentUser().getUid());
        }
    }
}