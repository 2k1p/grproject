package com.example.ibyg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ibyg.Manager.CafeActivity;
import com.example.ibyg.Manager.CafeManager;
import com.example.ibyg.Notice.NoticeManagement;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private FirebaseAuth mAuth;

    private BackPressCloseHandler backPressCloseHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();  //제목줄 객체 얻어오기
        actionBar.setTitle("알고가");  //액션바 제목설정

        backPressCloseHandler = new BackPressCloseHandler(this);


        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user == null){
            myStartActivity(SignUpActivity.class);
        }else{

            FirebaseFirestore db = FirebaseFirestore.getInstance();
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
                                myStartActivity(MemberInitActivity.class);
                            }

                        }
                    } else {
                        Log.d(TAG, "get failed with ", task.getException());
                    }
                }
            });

        }


        findViewById(R.id.logout).setOnClickListener(onClickListener);
        findViewById(R.id.button).setOnClickListener(onClickListener);
        findViewById(R.id.button3).setOnClickListener(onClickListener);
    }

    //로그아웃 로직
    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            switch(v.getId()){
                case R.id.logout:
                    FirebaseAuth.getInstance().signOut();
                    startToast( "로그아웃 하였습니다");
                    myStartActivity(SignUpActivity.class);
                    break;

                case R.id.button:   //카페관리 버튼
                    FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
                    CollectionReference allUsersRef = rootRef.collection("owner_cafe");

                    allUsersRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot document : task.getResult()) {
                                    String userName = document.getString("editTextName");
                                    if (userName == null) {
                                        //login();
                                        myStartActivity(CafeManager.class);
                                        startToast( "카페정보가 없어서 등록화면으로 이동합니다");
                                    } else {
                                        myStartActivity(CafeActivity.class);
                                        Log.d(TAG, "onEvent: username does not exists");
                                    }
                                }
                            } else {
                                Log.d("TAG", "Error getting documents: ", task.getException());
                            }
                        }
                    });
                    break;
                case R.id.button3:  //공지사항 버튼
                    myStartActivity(NoticeManagement.class);
                    break;

            }
        }
    };


//    private void login(){
//        String email = ((EditText)findViewById(R.id.emailEditText)).getText().toString();
//        String password = ((EditText)findViewById(R.id.passwordEditText)).getText().toString();
//
//
//
//        if(email.length() > 0 && password.length() > 0){
//            mAuth.signInWithEmailAndPassword(email, password)
//                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (task.isSuccessful()) {
//
//                                FirebaseUser user = mAuth.getCurrentUser();
//                                startToast("로그인 하였습니다");
//                                myStartActivity(CafeManager.class);
//                            } else {
//                                if(task.getException() != null){
//                                    startToast("이메일 또는 비밀번호가 일치하지 않습니다.");
//                                    //startToast(task.getException().toString());
//                                }
//                            }
//                        }
//                    });
//        }else{
//            startToast("이메일 또는 비밀번호를 입력해 주세요.");
//        }
//    }

    private void startToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    void myStartActivity(Class c){
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
    }


    public class BackPressCloseHandler {
        private long backKeyPressedTime = 0;
        private Toast toast;

        private Activity activity;

        public BackPressCloseHandler(Activity context) {
            this.activity = context;
        }
        public void onBackPressed() {
            if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
                backKeyPressedTime = System.currentTimeMillis();
                showGuide();

                return;
            }
            if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
                activity.finish();
                toast.cancel();
                finishAffinity();
            }
        }
        public void showGuide() {
            toast = Toast.makeText(activity, "\'뒤로\'버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
            toast.show();
        }
    }


    @Override public void onBackPressed() {     //뒤로가기 두번 누르면 종료
        //super.onBackPressed();
         backPressCloseHandler.onBackPressed();
    }

}
