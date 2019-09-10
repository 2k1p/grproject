package com.example.ibyg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ibyg.Review.ReviewAdd;


public class ListingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);

        findViewById(R.id.textView9).setOnClickListener(onClickListener);
        findViewById(R.id.findcafeButton).setOnClickListener(onClickListener);
        findViewById(R.id.ahpButton).setOnClickListener(onClickListener);
        findViewById(R.id.reviewButton).setOnClickListener(onClickListener);
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
                case R.id.reviewButton:
                    myStartActivity(ReviewAdd.class);
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
