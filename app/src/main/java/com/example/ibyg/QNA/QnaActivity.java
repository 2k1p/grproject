package com.example.ibyg.QNA;

import android.os.Bundle;
import android.view.View;

import com.example.ibyg.BasicActivity;
import com.example.ibyg.R;

public class QnaActivity extends BasicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qna_main);

        setToolbarTitle("1:1문의");


        findViewById(R.id.qnaTextview).setOnClickListener(onClickListener);
        findViewById(R.id.qnaTextview2).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.qnaTextview2:
                    myStartActivity(QnaManager.class);
                    break;
            }
        }
    };



}