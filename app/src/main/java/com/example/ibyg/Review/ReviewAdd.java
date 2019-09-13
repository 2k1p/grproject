package com.example.ibyg.Review;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
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
import com.example.ibyg.Notice.GalleryActivity;
import com.example.ibyg.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

import static com.example.ibyg.Notice.Util.showToast;

public class ReviewAdd extends BasicActivity {
    private static final String TAG = "ReviewAdd";
    private FirebaseUser user;
    private StorageReference storageRef;
    private ArrayList<String> pathList = new ArrayList<>();
    private LinearLayout parent;
    private RelativeLayout buttonsBackgroundLayout;
    private ImageView selectedImageVIew;
    private EditText selectedEditText;
    private EditText contentsEditText;
    private EditText titleEditText;
    private ReviewInfo reviewInfo;
    private int pathCount, successCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_add);

        setToolbarTitle("리뷰 작성");               //액션바 제목설정


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            checkVerify();
        }
        else
        {
            startApp();
        }


        parent = findViewById(R.id.reviewcontentsLayout);
        buttonsBackgroundLayout = findViewById(R.id.reviewbuttonsBackgroundLayout);
        //loaderLayout = findViewById(R.id.loaderLyaout);
        contentsEditText = findViewById(R.id.reviewcontentsEditText);
        titleEditText = findViewById(R.id.reviewtitleEditText);

        findViewById(R.id.reviewcheck).setOnClickListener(onClickListener);
        findViewById(R.id.reviewimage).setOnClickListener(onClickListener);
        findViewById(R.id.video).setOnClickListener(onClickListener);
        findViewById(R.id.reviewimageModify).setOnClickListener(onClickListener);
        findViewById(R.id.videoModify).setOnClickListener(onClickListener);
        findViewById(R.id.reviewdelete).setOnClickListener(onClickListener);

        buttonsBackgroundLayout.setOnClickListener(onClickListener);
        contentsEditText.setOnFocusChangeListener(onFocusChangeListener);
        titleEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    selectedEditText = null;
                }
            }
        });

        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();


        reviewInfo = (ReviewInfo) getIntent().getSerializableExtra("reviewInfo");
        postInit();
    }



    public void startApp()
    {
        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        String strDir = file.getAbsolutePath();

        File oFile = new File(strDir+"/abc.txt");
        FileOutputStream oFos;
        try
        {
            oFos = new FileOutputStream(oFile);
            oFos.write("abc".getBytes(), 0, 3);
            oFos.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void checkVerify()       //파일접근 권한묻기
    {
        if (
                checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                        checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
        )
        {
            // Should we show an explanation?
            if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE))
            {
                // ...
            }
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE},
                    1);
        }
        else
        {
            startApp();
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
                    LinearLayout linearLayout = new LinearLayout(ReviewAdd.this);
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

                    ImageView imageView = new ImageView(ReviewAdd.this);
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

                    EditText editText = new EditText(ReviewAdd.this);
                    editText.setLayoutParams(layoutParams);
                    editText.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE | InputType.TYPE_CLASS_TEXT);
                    editText.setHint("내용");
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

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
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

                    StorageReference desertRef = storageRef.child("review/"+reviewInfo.getId()+"/"+name);
                    desertRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            showToast(ReviewAdd.this,"파일을 삭제하였습니다.");
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            showToast(ReviewAdd.this,"파일을 삭제하는데 실패하였습니다.");
                        }
                    });

                    pathList.remove(parent.indexOfChild(selectedView) - 1);
                    parent.removeView(selectedView);
                    buttonsBackgroundLayout.setVisibility(View.GONE);
                    break;
            }
        }
    };

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

        if (title.length() > 0) {
            //loaderLayout.setVisibility(View.VISIBLE);
            final ArrayList<String> contentsList = new ArrayList<>();
            user = FirebaseAuth.getInstance().getCurrentUser();
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReference();
            FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
            final DocumentReference documentReference = reviewInfo == null ? firebaseFirestore.collection("review").document() : firebaseFirestore.collection("review").document(reviewInfo.getId());
            final Date date = reviewInfo == null ? new Date() : reviewInfo.getCreatedAt();
            for (int i = 0; i < parent.getChildCount(); i++) {
                LinearLayout linearLayout = (LinearLayout) parent.getChildAt(i);
                for (int ii = 0; ii < linearLayout.getChildCount(); ii++) {
                    View view = linearLayout.getChildAt(ii);
                    if (view instanceof EditText) {
                        String text = ((EditText) view).getText().toString();
                        if (text.length() > 0) {
                            contentsList.add(text);
                        }
                    } else if (!Patterns.WEB_URL.matcher(pathList.get(pathCount)).matches()) {
                        String path = pathList.get(pathCount);
                        successCount++;
                        contentsList.add(path);
                        String[] pathArray = path.split("\\.");
                        final StorageReference mountainImagesRef = storageRef.child("review/" + documentReference.getId() + "/" + pathCount + "." + pathArray[pathArray.length - 1]);
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
                                                ReviewInfo reviewInfo = new ReviewInfo(title, contentsList, user.getUid(), date);
                                                storeUpload(documentReference, reviewInfo);
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
                storeUpload(documentReference, new ReviewInfo(title, contentsList, user.getUid(), date));
            }
        } else {
            startToast("제목을 입력해주세요.");
        }
    }

    private void storeUpload(DocumentReference documentReference, ReviewInfo reviewInfo) {
        documentReference.set(reviewInfo)
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
        if (reviewInfo != null) {
            titleEditText.setText(reviewInfo.getTitle());
            ArrayList<String> contentsList = reviewInfo.getContents();
            for (int i = 0; i < contentsList.size(); i++) {
                String contents = contentsList.get(i);
                if (Patterns.WEB_URL.matcher(contents).matches() && contents.contains("firebasestorage.googleapis.com/v0/b/ibyg-project.appspot.com/o/review")) {
                    pathList.add(contents);

                    ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    LinearLayout linearLayout = new LinearLayout(ReviewAdd.this);
                    linearLayout.setLayoutParams(layoutParams);
                    linearLayout.setOrientation(LinearLayout.VERTICAL);
                    parent.addView(linearLayout);

                    ImageView imageView = new ImageView(ReviewAdd.this);
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

                    EditText editText = new EditText(ReviewAdd.this);
                    editText.setLayoutParams(layoutParams);
                    editText.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE | InputType.TYPE_CLASS_TEXT);
                    editText.setHint("내용");
                    if (i < contentsList.size() - 1) {
                        String nextContents = contentsList.get(i + 1);
                        if (!Patterns.WEB_URL.matcher(nextContents).matches() || !nextContents.contains("firebasestorage.googleapis.com/v0/b/ibyg-project.appspot.com/o/review")) {
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