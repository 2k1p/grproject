package com.example.ibyg.Manager;

import android.os.Bundle;
import android.view.View;

import com.example.ibyg.BasicActivity;
import com.example.ibyg.R;

public class CafeAhp extends BasicActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cafeahp);

        setToolbarTitle("카페정보 동의");  //액션바 제목설정


        findViewById(R.id.yesButton).setOnClickListener(this);
        findViewById(R.id.noButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.yesButton:  //예
                myStartActivity(CafeManager.class);
                break;
            case R.id.noButton:  //아니오
                myStartActivity(CafeManager0.class);
                break;


        }

    }


}
