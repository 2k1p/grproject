package com.example.ibyg.Manager;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.ibyg.BasicActivity;
import com.example.ibyg.Login.MemberInitActivity;
import com.example.ibyg.Login.SignUpActivity;
import com.example.ibyg.MapsActivity;
import com.example.ibyg.Notice.GalleryActivity;
import com.example.ibyg.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

//카페명, 주소, 전화번호, 영업시간, 와이파이, 콘센트, 좌석수, 최소가격
public class CafeManager extends BasicActivity implements View.OnClickListener {
    private static final String TAG = "CafeManager";
    private FirebaseFirestore db;
    private ImageView profileImageVIew;
    private String profilePath;
    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cafemanager);

        setToolbarTitle("카페 등록");  //액션바 제목설정

        LinearLayout layout = findViewById(R.id.ahpshow);
        layout.setVisibility(View.VISIBLE); //의사결정시스템 정보수집 숨긴거 보이게
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        if(user == null){
            startActivity(new Intent(this, SignUpActivity.class));
        }else{

            db = FirebaseFirestore.getInstance();
            DocumentReference docRef = db.collection("users").document(user.getUid());
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if(document != null){

                            if (document.exists()) {
                                Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                            } else {
                                Log.d(TAG, "No such document");
                                startActivity(new Intent(getApplication(), MemberInitActivity.class));
                            }

                        }
                    } else {
                        Log.d(TAG, "get failed with ", task.getException());
                    }
                }
            });

        }

        db = FirebaseFirestore.getInstance();



        findViewById(R.id.button6).setOnClickListener(this);
        findViewById(R.id.button_save).setOnClickListener(this);
        findViewById(R.id.cafeimage).setOnClickListener(this);
        profileImageVIew = findViewById(R.id.profileImageView);
        profileImageVIew.setOnClickListener(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0: {
                if (resultCode == Activity.RESULT_OK) {
                    profilePath = data.getStringExtra("profilePath");
                    Bitmap bmp = BitmapFactory.decodeFile(profilePath);
                    profileImageVIew.setImageBitmap(bmp);
                }
                break;
            }
        }
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_save:  //등록버튼
                saveProduct();
                break;
            case R.id.cafeimage:    //카페사진 업로드하기
                if (ContextCompat.checkSelfPermission(CafeManager.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(CafeManager.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            1);
                    if (ActivityCompat.shouldShowRequestPermissionRationale(CafeManager.this,
                            Manifest.permission.READ_EXTERNAL_STORAGE)) {

                    } else {
                        startToast("권한을 허용해 주세요");
                    }
                }else{
                    myStartActivity(GalleryActivity.class);
                }
                break;
            case R.id.button6:
                myStartActivity(MapsActivity.class);
                break;
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0  && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    myStartActivity(GalleryActivity.class);
                } else {
                    startToast("권한을 허용해 주세요");
                }
            }
        }
    }


    private void saveProduct(){
        final String name = ((EditText)findViewById(R.id.CafeEdit)).getText().toString().trim();
        final String address = ((EditText)findViewById(R.id.CafeEdit2)).getText().toString().trim();
        final String phone = ((EditText)findViewById(R.id.CafeEdit3)).getText().toString().trim();
        final String time = ((EditText)findViewById(R.id.CafeEdit4)).getText().toString().trim();
        final String wifi = ((EditText)findViewById(R.id.CafeEdit5)).getText().toString().trim();
        final String seat = ((EditText)findViewById(R.id.CafeEdit6)).getText().toString().trim();
        final String consent = ((EditText)findViewById(R.id.CafeEdit7)).getText().toString().trim();
        final String price = ((EditText)findViewById(R.id.CafeEdit8)).getText().toString().trim();

        final String coffee1 = ((EditText)findViewById(R.id.CafeEdit9)).getText().toString().trim();
        final String price1 = ((EditText)findViewById(R.id.CafeEdit13)).getText().toString().trim();
        final String coffee2 = ((EditText)findViewById(R.id.CafeEdit10)).getText().toString().trim();
        final String price2 = ((EditText)findViewById(R.id.CafeEdit14)).getText().toString().trim();
        final String coffee3 = ((EditText)findViewById(R.id.CafeEdit11)).getText().toString().trim();
        final String price3 = ((EditText)findViewById(R.id.CafeEdit15)).getText().toString().trim();
        final String coffee4 = ((EditText)findViewById(R.id.CafeEdit12)).getText().toString().trim();
        final String price4 = ((EditText)findViewById(R.id.CafeEdit16)).getText().toString().trim();

        if(name.length() > 0 && address.length() > 0 && phone.length() > 0 && time.length() > 0
        ){
            user = FirebaseAuth.getInstance().getCurrentUser();
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReference();
            final StorageReference mountainImagesRef = storageRef.child("owner_cafe/" + user.getUid() + "/cafeImage.jpg");

            if(profilePath == null){
                OwnerInfo ownerInfo = new OwnerInfo(name, address, phone, time, wifi, seat, consent, price, coffee1, coffee2, coffee3, coffee4, price1, price2, price3, price4, null);
                uploader(ownerInfo);
            }else{
                try {
                    InputStream stream = new FileInputStream(new File(profilePath));
                    UploadTask uploadTask = mountainImagesRef.putStream(stream);
                    uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                            if (!task.isSuccessful()) {
                                throw task.getException();
                            }
                            return mountainImagesRef.getDownloadUrl();
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if (task.isSuccessful()) {
                                Uri downloadUri = task.getResult();

                                OwnerInfo ownerInfo = new OwnerInfo(name, address, phone, time, wifi, seat, consent, price, coffee1, coffee2, coffee3, coffee4, price1, price2, price3, price4, downloadUri.toString());
                                uploader(ownerInfo);

                            } else {
                                startToast("회원정보를 보내는데 실패하였습니다.");
                            }
                        }
                    });
                } catch (FileNotFoundException e) {
                    Log.e("로그", "에러: " + e.toString());
                }
            }
        } else {
            startToast("회원정보를 입력해주세요.");
        }

    }

    private void uploader(OwnerInfo ownerInfo){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("owner_cafe").document(user.getUid()).set(ownerInfo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        startToast("카페정보 등록을 성공하였습니다.");
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        startToast("카페정보 등록에 실패하였습니다.");
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }

    public void myStartActivity(Class c) {
        Intent intent = new Intent(this, c);
        startActivityForResult(intent, 0);
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
    }


}
