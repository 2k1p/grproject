package com.example.ibyg;

import android.os.Bundle;
import android.view.View;

import com.example.ibyg.Qna.QnaAdd;


public class ListingActivity extends BasicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);

        findViewById(R.id.textView9).setOnClickListener(onClickListener);
        findViewById(R.id.findcafeButton).setOnClickListener(onClickListener);
        findViewById(R.id.ahpButton).setOnClickListener(onClickListener);
        //findViewById(R.id.reviewButton).setOnClickListener(onClickListener);
        findViewById(R.id.qnaButton).setOnClickListener(onClickListener);
    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.textView9:
                    myStartActivity(SetLocActivity.class);
                    break;
                case R.id.findcafeButton:
                    myStartActivity(FindCafeActivity.class);
                    break;
                case R.id.ahpButton:
                    myStartActivity(AhpActivity.class);
                    break;
//                case R.id.reviewButton:
//                    myStartActivity(ReviewAdd.class);
//                    break;
                case R.id.qnaButton:
                    myStartActivity(QnaAdd.class);
                    break;
            }
        }
    };




}
