package com.example.ibyg;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.CursorLoader;

import com.example.ibyg.Manager.OwnerInfo;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

public class Imagetest extends BasicActivity {
    private static final int GALLERY_CODE = 10;
    private FirebaseAuth auth;
    private FirebaseStorage storage;
    private OwnerInfo ownerInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_test);

        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();

        ownerInfo = (OwnerInfo) getIntent().getSerializableExtra("ownerInfo");

        Button button = findViewById(R.id.imagetest);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);

                startActivityForResult(intent, GALLERY_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GALLERY_CODE) {


            StorageReference storageRef = storage.getReferenceFromUrl("gs://ibyg-project.appspot.com");
            FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
            final DocumentReference documentReference = ownerInfo == null ? firebaseFirestore.collection("owner_cafe").document() : firebaseFirestore.collection("owner_cafe").document(ownerInfo.getId());


            Uri file = Uri.fromFile(new File(getPath(data.getData())));
            StorageReference riversRef = storageRef.child("images/" + file.getLastPathSegment());
            UploadTask uploadTask = riversRef.putFile(file);

            // Register observers to listen for when the download is done or if it fails
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle unsuccessful uploads
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                    //Uri downloadUrl = taskSnapshot.getDownloadUrl();
                }
            });


        }
    }

    public String getPath(Uri uri){
        String [] proj = {MediaStore.Images.Media.DATA};
        CursorLoader cursorLoader = new CursorLoader(this,uri,proj,null,null,null);

        Cursor cursor = cursorLoader.loadInBackground();
        int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();

        return cursor.getString(index);

    }
}
