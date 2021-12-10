package com.e_carter.userroles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {
    private RecyclerView recyclerView2;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    FirebaseUser user;
    private DatabaseReference myRef;

    private UserRecyclerAdapter userRecyclerAdapter;
    private Context mContext;

    private ArrayList<Pengantaran> pengantaransArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_user );
        recyclerView2 = findViewById( R.id.recyclerview2 );

        LinearLayoutManager layoutManager = new LinearLayoutManager( this );
        recyclerView2.setLayoutManager( layoutManager );
        recyclerView2.setHasFixedSize( true );

        myRef = FirebaseDatabase.getInstance().getReference();

//        ArrayList
        pengantaransArrayList = new ArrayList<>();

//        clear list
        ClearAll();

//        get data method
        GetDataFromFirebase();
    }
    private void GetDataFromFirebase() {
        Query query = myRef.child( "E_Carter" );
        query.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ClearAll();
                for (DataSnapshot snapshot:dataSnapshot.getChildren()) {
                    Pengantaran pengantaran =new Pengantaran();

                    pengantaran.setImageUrl( snapshot.child( "imageUrl" ).getValue().toString() );
                    pengantaran.setNamaAnak( snapshot.child( "namaDriver" ) .getValue().toString());
                    pengantaran.setWaktuJemput( snapshot.child( "waktu" ) .getValue().toString());

                    pengantaran.setImgAnak( snapshot.child( "imageanak" ).getValue().toString() );
                    pengantaran.setNamaAnak2( snapshot.child( "namaanak" ) .getValue().toString());
                    pengantaran.setWaktuAntar( snapshot.child( "waktuanak" ) .getValue().toString());
                    pengantaran.setEmail( snapshot.child( "email" ).getValue().toString() );
                    pengantaran.setId( snapshot.getKey() );
                    pengantaransArrayList.add( pengantaran );
                }
                userRecyclerAdapter = new UserRecyclerAdapter(getApplicationContext(),pengantaransArrayList);
                recyclerView2.setAdapter( userRecyclerAdapter );
                userRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        } );
}

    private void ClearAll(){
        if (pengantaransArrayList!=null){
            pengantaransArrayList.clear();

            if (userRecyclerAdapter !=null){
                userRecyclerAdapter.notifyDataSetChanged();
            }


        }

        pengantaransArrayList = new ArrayList<>();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        inflate the menu
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.add_post_user,menu );
        return super.onCreateOptionsMenu( menu );
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.add_post){
            startActivity( new Intent(getApplicationContext(),AddPostCarter.class) );
        }
        if (item.getItemId()==R.id.log_out){
            FirebaseAuth.getInstance().signOut();
            startActivity( new Intent(getApplicationContext(),Login.class) );
            finish();
        }
        return super.onOptionsItemSelected( item );
    }


}