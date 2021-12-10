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
import android.view.View;
import android.widget.Button;

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

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    FirebaseUser user;
    private DatabaseReference myRef;

    private AdminRecyclerAdapter adminRecyclerAdapter;
    private Context mContext;

    private ArrayList<Penjemputan> penjemputansArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById( R.id.recyclerview );

        LinearLayoutManager layoutManager = new LinearLayoutManager( this );
        recyclerView.setLayoutManager( layoutManager );
        recyclerView.setHasFixedSize( true );

        myRef = FirebaseDatabase.getInstance().getReference();

//        ArrayList
        penjemputansArrayList = new ArrayList<>();

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
                    Penjemputan penjemputan =new Penjemputan();

                    penjemputan.setImageUrl( snapshot.child( "imageUrl" ).getValue().toString() );
                    penjemputan.setNamaAnak( snapshot.child( "namaDriver" ) .getValue().toString());
                    penjemputan.setWaktuJemput( snapshot.child( "waktu" ) .getValue().toString());
                    penjemputan.setEmail( snapshot.child( "email" ).getValue().toString() );
                    penjemputansArrayList.add( penjemputan );
                }
                adminRecyclerAdapter = new AdminRecyclerAdapter(getApplicationContext(),penjemputansArrayList);
                recyclerView.setAdapter( adminRecyclerAdapter );
                adminRecyclerAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        } );

    }

    private void ClearAll(){
        if (penjemputansArrayList!=null){
            penjemputansArrayList.clear();

            if (adminRecyclerAdapter !=null){
                adminRecyclerAdapter.notifyDataSetChanged();
            }


        }

        penjemputansArrayList = new ArrayList<>();
    }

//    public void logoutAdmin(View view) {
//        FirebaseAuth.getInstance().signOut();
//        startActivity( new Intent(getApplicationContext(),Login.class) );
//        finish();
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        inflate the menu
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.add_post,menu );
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