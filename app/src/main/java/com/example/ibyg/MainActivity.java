package com.example.ibyg;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user == null){
            myStartActivity(SignUpActivity.class);
        }else{
            //회원가입 or 로그인

            for (UserInfo profile : user.getProviderData()) {
                // Name, email address, and profile photo Url
                String name = profile.getDisplayName();
                Log.e("이름:", "이름:" + name);
                if(name != null){
                    if(name.length() == 0){
                        myStartActivity(MemberInitActivity.class);
                    }
                }
            }
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
                    myStartActivity(SignUpActivity.class);
                    break;
            }
        }
    };


    private void myStartActivity(Class c){
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
