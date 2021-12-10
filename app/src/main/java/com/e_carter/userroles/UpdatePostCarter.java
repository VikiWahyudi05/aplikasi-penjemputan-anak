package com.e_carter.userroles;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.Uploaded;
import com.Uploaded1;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class UpdatePostCarter extends AppCompatActivity {

    private static final int CAMERA_PERM_CODE = 101;
    private static final int CAMERA_REQUEST_CODE = 102;
    //    init
    ImageView imgDriver;
    Button chooseBtn,PickTime;

    TextView tvNamaDriver, tvWaktu;
    EditText namaDriver,waktu;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    FirebaseUser user;
    DatabaseReference root = FirebaseDatabase.getInstance().getReference("E_Carter");
    StorageReference reference = FirebaseStorage.getInstance().getReference();
    Uri imageUri;
    ProgressBar progressBar;
    String sessionId,namaanak,email,waktuanak,image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_update_post_carter );
//        PickTime
        PickTime = findViewById( R.id.btPickTime );
        sessionId = getIntent().getStringExtra("id");
        namaanak = getIntent().getStringExtra("namaanak");
        email = getIntent().getStringExtra("email");
        waktuanak = getIntent().getStringExtra("waktu");
        image = getIntent().getStringExtra("image");
//        setup
        namaDriver = findViewById( R.id.tvNamaDriver );
        waktu = findViewById( R.id.tvWaktu );

        imgDriver = findViewById( R.id.imgDriver );
        chooseBtn = findViewById( R.id.chooseBtn );

        tvNamaDriver = findViewById( R.id.tvNamaDriver );
        tvWaktu = findViewById( R.id.tvWaktu );
        progressBar =findViewById( R.id.progressBar );
        progressBar.setVisibility( View.INVISIBLE  );



        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        imgDriver.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction( Intent.ACTION_GET_CONTENT );
                galleryIntent.setType( "image/*" );
                startActivityForResult( galleryIntent,2 );
            }
        } );

        chooseBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageUri!=null){
                    uploadToFirebase(imageUri);
                }else {
                    Toast.makeText( UpdatePostCarter.this,"Please Select image", Toast.LENGTH_SHORT ).show();
                }
            }
        } );
        PickTime.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar=Calendar.getInstance();
                final int hour = calendar.get( Calendar.HOUR_OF_DAY );
                int minute = calendar.get( Calendar.MINUTE );
                TimePickerDialog timePickerDialog = new TimePickerDialog( UpdatePostCarter.this, R.style.Theme_AppCompat_Dialog, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfday, int minute) {
                        Calendar c = Calendar.getInstance();
                        c.set( Calendar.HOUR_OF_DAY,hourOfday);
                        c.set( Calendar.MINUTE,minute );
                        c.setTimeZone( TimeZone.getDefault() );
                        SimpleDateFormat format = new SimpleDateFormat("k:mm a");
                        String time = format.format( c.getTime() );
                        tvWaktu.setText( time );

                    }
                },hour,minute,false );
                timePickerDialog.show();
            }
        } );

    }

    private void uploadToFirebase(Uri uri) {
        FirebaseUser user = fAuth.getCurrentUser();
        final  String email = user.getEmail();
        final  String uid = user.getUid();
        final StorageReference fireRef = reference.child( uid+"."+getFileExtension(uri) );
        final String namaDriver = tvNamaDriver.getText().toString();
        final String waktu = tvWaktu.getText().toString();

        fireRef.putFile( uri ).addOnSuccessListener( new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
                fireRef.getDownloadUrl().addOnSuccessListener( new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Uploaded1 uploaded = new Uploaded1(image,namaanak,waktuanak,email,uri.toString(),namaDriver,waktu);
                        root.child( sessionId ).setValue( uploaded );

                        Toast.makeText( UpdatePostCarter.this,"Uploaded Successfully",Toast.LENGTH_SHORT ).show();

                        finish();

                    }
                } );
            }
        } ).addOnProgressListener( new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                progressBar.setVisibility( View.VISIBLE );
            }
        } ).addOnFailureListener( new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility( View.INVISIBLE );
                Toast.makeText( UpdatePostCarter.this,"Uploading Failed",Toast.LENGTH_SHORT ).show();
            }
        } );
    }

    private String getFileExtension(Uri mUri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType( cr.getType( mUri ) );


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        if (requestCode == 2 && resultCode == RESULT_OK && data!=null){
            imageUri = data.getData();
            imgDriver.setImageURI( imageUri );

        }
    }
}