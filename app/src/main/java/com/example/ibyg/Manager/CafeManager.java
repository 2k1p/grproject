package com.example.ibyg.Manager;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.ibyg.BasicActivity;
import com.example.ibyg.Login.MemberInitActivity;
import com.example.ibyg.Login.SignUpActivity;
import com.example.ibyg.Notice.GalleryActivity;
import com.example.ibyg.R;
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
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import static com.example.ibyg.Notice.Util.showToast;

//카페명, 주소, 전화번호, 영업시간, 와이파이, 콘센트, 좌석수, 최소가격
public class CafeManager extends BasicActivity implements View.OnClickListener {
    private static final String TAG = "CafeManager";

    private EditText editTextName;
    private EditText editTextAddress;
    private EditText editTextPhone;
    private EditText editTexttime;
    private EditText editTextwifi;
    private EditText editTextseat;
    private EditText editTextconsent;
    private EditText editTextprice;

    private EditText editCoffee1;
    private EditText editCoffee2;
    private EditText editCoffee3;
    private EditText editCoffee4;
    private EditText editPrice1;
    private EditText editPrice2;
    private EditText editPrice3;
    private EditText editPrice4;


    private FirebaseFirestore db;
    private OwnerInfo ownerInfo;
    private StorageReference storageRef;
    private FirebaseUser user;
    private int pathCount, successCount;
    private ArrayList<String> pathList = new ArrayList<>();
    private LinearLayout parent;
    private RelativeLayout buttonsBackgroundLayout;
    private ImageView selectedImageVIew;
    private EditText selectedEditText;
    private EditText contentsEditText;
    private EditText titleEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cafemanager);

        setToolbarTitle("카페 등록");  //액션바 제목설정



        LinearLayout layout = findViewById(R.id.ahpshow);
        layout.setVisibility(View.VISIBLE); //의사결정시스템 정보수집 숨긴거 보이게
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();

        ownerInfo = (OwnerInfo) getIntent().getSerializableExtra("ownerInfo");
        postInit();

        parent = findViewById(R.id.reviewcontentsLayout);
        //loaderLayout = findViewById(R.id.loaderLyaout);
        contentsEditText = findViewById(R.id.reviewcontentsEditText);
        titleEditText = findViewById(R.id.reviewtitleEditText);

        findViewById(R.id.reviewcheck).setOnClickListener(this);
        findViewById(R.id.reviewimage).setOnClickListener(this);
        findViewById(R.id.video).setOnClickListener(this);

        contentsEditText.setOnFocusChangeListener(onFocusChangeListener);
        titleEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    selectedEditText = null;
                }
            }
        });

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

        editTextName = findViewById(R.id.CafeEdit);
        editTextAddress = findViewById(R.id.CafeEdit2);
        editTextPhone = findViewById(R.id.CafeEdit3);
        editTexttime = findViewById(R.id.CafeEdit4);
        editTextwifi = findViewById(R.id.CafeEdit5);
        editTextseat = findViewById(R.id.CafeEdit6);
        editTextconsent = findViewById(R.id.CafeEdit7);
        editTextprice = findViewById(R.id.CafeEdit8);

        editCoffee1 = findViewById(R.id.CafeEdit9);
        editPrice1 = findViewById(R.id.CafeEdit13);
        editCoffee2 = findViewById(R.id.CafeEdit10);
        editPrice2 = findViewById(R.id.CafeEdit14);
        editCoffee3 = findViewById(R.id.CafeEdit11);
        editPrice3 = findViewById(R.id.CafeEdit15);
        editCoffee4 = findViewById(R.id.CafeEdit12);
        editPrice4 = findViewById(R.id.CafeEdit16);

        findViewById(R.id.button_save).setOnClickListener(this);
    }


    private void saveProduct(){
        String name = editTextName.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String time = editTexttime.getText().toString().trim();
        String wifi = editTextwifi.getText().toString().trim();
        String seat = editTextseat.getText().toString().trim();
        String consent = editTextconsent.getText().toString().trim();
        String price = editTextprice.getText().toString().trim();

        String coffee1 = editCoffee1.getText().toString().trim();
        String coffee2 = editCoffee2.getText().toString().trim();
        String coffee3 = editCoffee3.getText().toString().trim();
        String coffee4 = editCoffee4.getText().toString().trim();
        String price1 = editPrice1.getText().toString().trim();
        String price2 = editPrice2.getText().toString().trim();
        String price3 = editPrice3.getText().toString().trim();
        String price4 = editPrice4.getText().toString().trim();

        OwnerInfo ownerInfo = new OwnerInfo(name, address, phone, time, wifi, seat, consent, price, coffee1, coffee2, coffee3, coffee4, price1, price2, price3, price4);

        if(name.length() > 0 && address.length() > 0 && phone.length() > 0 && time.length() > 0
                ){
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            if(user != null){
                db.collection("owner_cafe").document(user.getUid()).set(ownerInfo)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                startToast("카페정보 등록을 성공하였습니다.");
                                myStartActivity(CafeActivity.class);
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

        }else{
            startToast("카페정보를 입력해주세요.");
        }

    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_save:  //등록버튼
                saveProduct();
                storageUpload();
                break;
            case R.id.image:        //카페사진 업로드
                myStartActivity(GalleryActivity.class, "image", 0);
                break;
            case R.id.reviewcheck:
                storageUpload();
                break;
            case R.id.reviewimage:
                myStartActivity(GalleryActivity.class, "image", 0);
                break;
            case R.id.video:
                myStartActivity(GalleryActivity.class, "video", 0);
                break;
            case R.id.reviewbuttonsBackgroundLayout:
                if (buttonsBackgroundLayout.getVisibility() == View.VISIBLE) {
                    buttonsBackgroundLayout.setVisibility(View.GONE);
                }
                break;
            case R.id.reviewimageModify:
                myStartActivity(GalleryActivity.class, "image", 1);
                buttonsBackgroundLayout.setVisibility(View.GONE);
                break;
            case R.id.videoModify:
                myStartActivity(GalleryActivity.class, "video", 1);
                buttonsBackgroundLayout.setVisibility(View.GONE);
                break;
            case R.id.reviewdelete:
                View selectedView = (View) selectedImageVIew.getParent();

                String[] list = pathList.get(parent.indexOfChild(selectedView) - 1).split("\\?");
                String[] list2 = list[0].split("%2F");
                String name = list2[list2.length - 1];
                Log.e("로그: ","이름: "+name);

                StorageReference desertRef = storageRef.child("owner_cafe/"+ownerInfo.getId()+"/"+name);
                desertRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        showToast(CafeManager.this,"파일을 삭제하였습니다.");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        showToast(CafeManager.this,"파일을 삭제하는데 실패하였습니다.");
                    }
                });

                pathList.remove(parent.indexOfChild(selectedView) - 1);
                parent.removeView(selectedView);
                buttonsBackgroundLayout.setVisibility(View.GONE);
                break;
        }

    }

    View.OnFocusChangeListener onFocusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                selectedEditText = (EditText) v;
            }
        }
    };

    private void storageUpload() {
        final String title = ((EditText) findViewById(R.id.reviewtitleEditText)).getText().toString();

        if (title.length() < 0) {
            //loaderLayout.setVisibility(View.VISIBLE);
            final ArrayList<String> contentsList = new ArrayList<>();
            user = FirebaseAuth.getInstance().getCurrentUser();
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReference();
            FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
            final DocumentReference documentReference = ownerInfo == null ? firebaseFirestore.collection("owner_cafe").document() : firebaseFirestore.collection("owner_cafe").document(ownerInfo.getId());
            //final Date date = ownerInfo == null ? new Date() : ownerInfo.getCreatedAt();
            for (int i = 0; i < parent.getChildCount(); i++) {
                LinearLayout linearLayout = (LinearLayout) parent.getChildAt(i);
                for (int ii = 0; ii < linearLayout.getChildCount(); ii++) {
                    View view = linearLayout.getChildAt(ii);
                    if (view instanceof EditText) {
                        String text = ((EditText) view).getText().toString();
                        if (text.length() < 0) {
                            contentsList.add(text);
                        }
                    } else if (!Patterns.WEB_URL.matcher(pathList.get(pathCount)).matches()) {
                        String path = pathList.get(pathCount);
                        successCount++;
                        contentsList.add(path);
                        String[] pathArray = path.split("\\.");
                        final StorageReference mountainImagesRef = storageRef.child("owner_cafe/" + documentReference.getId() + "/" + pathCount + "." + pathArray[pathArray.length - 1]);
                        try {
                            InputStream stream = new FileInputStream(new File(pathList.get(pathCount)));
                            StorageMetadata metadata = new StorageMetadata.Builder().setCustomMetadata("index", "" + (contentsList.size() - 1)).build();
                            UploadTask uploadTask = mountainImagesRef.putStream(stream, metadata);
                            uploadTask.addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                }
                            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    final int index = Integer.parseInt(taskSnapshot.getMetadata().getCustomMetadata("index"));
                                    mountainImagesRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            successCount--;
                                            contentsList.set(index, uri.toString());
                                            if (successCount == 0) {
                                                //OwnerInfo ownerInfo = new OwnerInfo();
                                                storeUpload(documentReference, ownerInfo);
                                                startToast("리뷰가 등록되었습니다.");
                                            }
                                        }
                                    });
                                }
                            });
                        } catch (FileNotFoundException e) {
                            Log.e("로그", "에러: " + e.toString());
                        }
                        pathCount++;
                    }
                }
            }
            if (successCount == 0) {
                //storeUpload(documentReference, new OwnerInfo());
            }
        } else {
            startToast("제목을 입력해주세요.");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0:
                if (resultCode == Activity.RESULT_OK) {
                    String profilePath = data.getStringExtra("profilePath");
                    pathList.add(profilePath);

                    ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    LinearLayout linearLayout = new LinearLayout(CafeManager.this);
                    linearLayout.setLayoutParams(layoutParams);
                    linearLayout.setOrientation(LinearLayout.VERTICAL);

                    if (selectedEditText == null) {
                        parent.addView(linearLayout);
                    } else {
                        for (int i = 0; i < parent.getChildCount(); i++) {
                            if (parent.getChildAt(i) == selectedEditText.getParent()) {
                                parent.addView(linearLayout, i + 1);
                                break;
                            }
                        }
                    }

                    ImageView imageView = new ImageView(CafeManager.this);
                    imageView.setLayoutParams(layoutParams);
                    imageView.setAdjustViewBounds(true);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            buttonsBackgroundLayout.setVisibility(View.VISIBLE);
                            selectedImageVIew = (ImageView) v;
                        }
                    });
                    Glide.with(this).load(profilePath).override(1000).into(imageView);
                    linearLayout.addView(imageView);

                    EditText editText = new EditText(CafeManager.this);
                    editText.setLayoutParams(layoutParams);
                    editText.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE | InputType.TYPE_CLASS_TEXT);
                    editText.setOnFocusChangeListener(onFocusChangeListener);
                    linearLayout.addView(editText);
                }
                break;
            case 1:
                if (resultCode == Activity.RESULT_OK) {
                    String profilePath = data.getStringExtra("profilePath");
                    pathList.set(parent.indexOfChild((View) selectedImageVIew.getParent()) - 1, profilePath);
                    Glide.with(this).load(profilePath).override(1000).into(selectedImageVIew);
                }
                break;
        }
    }

    private void storeUpload(DocumentReference documentReference, OwnerInfo ownerInfo) {
        documentReference.set(ownerInfo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                        //loaderLayout.setVisibility(View.GONE);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                        //loaderLayout.setVisibility(View.GONE);
                    }
                });
    }


    private void postInit() {
        if (ownerInfo != null) {
            titleEditText.setText(ownerInfo.geteditTextName());
            ArrayList<String> contentsList = ownerInfo.getContents();
            for (int i = 0; i < contentsList.size(); i++) {
                String contents = contentsList.get(i);
                if (Patterns.WEB_URL.matcher(contents).matches() && contents.contains("firebasestorage.googleapis.com/v0/b/ibyg-project.appspot.com/o/owner_cafe")) {
                    pathList.add(contents);

                    ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    LinearLayout linearLayout = new LinearLayout(CafeManager.this);
                    linearLayout.setLayoutParams(layoutParams);
                    linearLayout.setOrientation(LinearLayout.VERTICAL);
                    parent.addView(linearLayout);

                    ImageView imageView = new ImageView(CafeManager.this);
                    imageView.setLayoutParams(layoutParams);
                    imageView.setAdjustViewBounds(true);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            buttonsBackgroundLayout.setVisibility(View.VISIBLE);
                            selectedImageVIew = (ImageView) v;
                        }
                    });
                    Glide.with(this).load(contents).override(1000).into(imageView);
                    linearLayout.addView(imageView);

                    EditText editText = new EditText(CafeManager.this);
                    editText.setLayoutParams(layoutParams);
                    editText.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE | InputType.TYPE_CLASS_TEXT);
                    if (i < contentsList.size() - 1) {
                        String nextContents = contentsList.get(i + 1);
                        if (!Patterns.WEB_URL.matcher(nextContents).matches() || !nextContents.contains("firebasestorage.googleapis.com/v0/b/ibyg-project.appspot.com/o/owner_cafe")) {
                            editText.setText(nextContents);
                        }
                    }
                    editText.setOnFocusChangeListener(onFocusChangeListener);
                    linearLayout.addView(editText);
                } else if (i == 0) {
                    contentsEditText.setText(contents);
                }
            }
        }
    }

    private void myStartActivity(Class c, String media, int requestCode) {
        Intent intent = new Intent(this, c);
        intent.putExtra("media", media);
        startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
    }


}
