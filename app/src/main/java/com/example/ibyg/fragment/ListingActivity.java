package com.example.ibyg.fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.example.ibyg.AhpActivity;
import com.example.ibyg.BasicActivity;
import com.example.ibyg.FindCafeActivity;
import com.example.ibyg.Qna.QnaAdd;
import com.example.ibyg.R;
import com.example.ibyg.SetLocActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

//카페, 초기화면
public class ListingActivity extends BasicActivity {
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);

        findViewById(R.id.textView9).setOnClickListener(onClickListener);
        findViewById(R.id.findcafeButton).setOnClickListener(onClickListener);
        findViewById(R.id.ahpButton).setOnClickListener(onClickListener);
        //findViewById(R.id.reviewButton).setOnClickListener(onClickListener);
        findViewById(R.id.qnaButton).setOnClickListener(onClickListener);

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
