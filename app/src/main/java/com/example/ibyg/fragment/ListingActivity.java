package com.example.ibyg.fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.ibyg.BasicActivity;
import com.example.ibyg.Listing.FindCafeActivity;
import com.example.ibyg.Login.SignUpActivity;
import com.example.ibyg.R;
import com.example.ibyg.SetLocActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

//카페, 초기화면
public class ListingActivity extends BasicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);


        findViewById(R.id.textView9).setOnClickListener(onClickListener);
        findViewById(R.id.findcafeButton).setOnClickListener(onClickListener);
        //findViewById(R.id.ahpButton).setOnClickListener(onClickListener);
        //findViewById(R.id.reviewButton).setOnClickListener(onClickListener);
        findViewById(R.id.addButton).setOnClickListener(onClickListener);

        //하단 네비게이션바 추가
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_android:
                        break;

                    case R.id.ic_books:
                        myStartActivity(LoginFragment.class);
                        break;

                }

                return false;
            }
        });
    }




    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.textView9:        //주소설정
                    myStartActivity(SetLocActivity.class);
                    break;
                case R.id.findcafeButton:   //내 주변 카페 보기
                    myStartActivity(FindCafeActivity.class);
                    break;
//                case R.id.ahpButton:
//                    myStartActivity(AhpActivity.class);
//                    break;
//                case R.id.reviewButton:
//                    myStartActivity(ReviewAdd.class);
//                    break;
//                case R.id.qnaButton:
//                    myStartActivity(QnaAdd.class);
//                    break;
                case R.id.addButton:        //카페 등록하러가기
                    myStartActivity(SignUpActivity.class);
                    break;
            }
        }
    };







}
