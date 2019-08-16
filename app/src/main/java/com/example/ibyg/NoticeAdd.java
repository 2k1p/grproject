package com.example.ibyg;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class NoticeAdd extends MainActivity {
    private static final String TAG = "NoticeAdd";
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notice_add);

        ActionBar actionBar = getSupportActionBar(); //제목줄 객체 얻어오기
        actionBar.setTitle("공지사항 추가");          //액션바 제목설정

        actionBar.setDisplayHomeAsUpEnabled(true);   //뒤로가기버튼 <- 만들기



        findViewById(R.id.noticeadd).setOnClickListener(onClickListener);


    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.noticeadd:    //등록하기 버튼
                    myStartActivity(NoticeManagement.class);
                    profileUpdate();
                    break;
            }
        }
    };


    //안드로이드 + 파이어베이스 SNS앱 만들기 part10 [게시글 작성 화면 구현]
    private void profileUpdate(){
        String title = ((EditText)findViewById(R.id.titleEditText)).getText().toString();
        String contents = ((EditText)findViewById(R.id.contentEditText)).getText().toString();

        if(title.length() > 0 && contents.length() > 0){
            user = FirebaseAuth.getInstance().getCurrentUser();
            OwnerNoticeInfo ownerNoticeInfo = new OwnerNoticeInfo(title, contents, user.getUid());
            uploader(ownerNoticeInfo);

        }else{
            startToast("회원정보를 입력해주세요.");
        }
    }

    private void uploader(OwnerNoticeInfo ownerNoticeInfo){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("owner_notice").add(ownerNoticeInfo)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot written with ID: "+ documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    private void startToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }



}
