package com.example.ibyg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase Auth
        //메인에서 회원가입 안됐을때
        if(FirebaseAuth.getInstance().getCurrentUser() == null){
            startSignUpActivity();
        }

        findViewById(R.id.logout).setOnClickListener(onClickListener);














        ActionBar actionBar = getSupportActionBar();  //제목줄 객체 얻어오기
        actionBar.setTitle("알고가");  //액션바 제목설정

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {    //카페관리 눌렀을때 다음화면이동
                Intent intent = new Intent(getApplicationContext(), CafeManager.class);

                startActivity(intent);
            }
        });
    }


    //로그아웃 로직
    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            switch(v.getId()){
                case R.id.logout:
                    FirebaseAuth.getInstance().signOut();
                    startSignUpActivity();
                    break;
            }
        }
    };

    //회원가입 액티비티로 보내주는 함수
    private void startSignUpActivity(){
        Intent intent = new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }
}
