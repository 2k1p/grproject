package com.example.ibyg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class NoticeManagement extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notice_management);

        ActionBar actionBar = getSupportActionBar(); //제목줄 객체 얻어오기
        actionBar.setTitle("공지사항 관리");          //액션바 제목설정

        actionBar.setDisplayHomeAsUpEnabled(true);   //뒤로가기버튼 <- 만들기


        Button button = findViewById(R.id.noticeaddbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {    //카페관리 눌렀을때 다음화면이동
                Intent intent = new Intent(NoticeManagement.this, NoticeAdd.class);

                startActivity(intent);
            }
        });
    }
}