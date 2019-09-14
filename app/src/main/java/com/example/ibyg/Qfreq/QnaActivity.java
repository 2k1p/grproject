package com.example.ibyg.Qfreq;

import android.os.Bundle;
import android.view.View;

import com.example.ibyg.BasicActivity;
import com.example.ibyg.Qna.QnaManager;
import com.example.ibyg.R;

public class QnaActivity extends BasicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qna_main);

        setToolbarTitle("1:1문의");


        findViewById(R.id.qnabutton).setOnClickListener(onClickListener);
        findViewById(R.id.qnabutton2).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.qnabutton:  //자주 묻는 질문
                    myStartActivity(Qfreq.class);
                    break;
                case R.id.qnabutton2: //1:1문의 관리
                    myStartActivity(QnaManager.class);
                    break;
            }
        }
    };



}