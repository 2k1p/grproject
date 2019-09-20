package com.example.ibyg.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.ibyg.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class ListingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);

//        findViewById(R.id.textView9).setOnClickListener(onClickListener);
//        findViewById(R.id.findcafeButton).setOnClickListener(onClickListener);
//        findViewById(R.id.ahpButton).setOnClickListener(onClickListener);
//        //findViewById(R.id.reviewButton).setOnClickListener(onClickListener);
//        findViewById(R.id.qnaButton).setOnClickListener(onClickListener);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        NavigationUI.setupActionBarWithNavController(this, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }




//    View.OnClickListener onClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            switch(v.getId()){
//                case R.id.textView9:
//                    myStartActivity(SetLocActivity.class);
//                    break;
//                case R.id.findcafeButton:
//                    myStartActivity(FindCafeActivity.class);
//                    break;
//                case R.id.ahpButton:
//                    myStartActivity(AhpActivity.class);
//                    break;
////                case R.id.reviewButton:
////                    myStartActivity(ReviewAdd.class);
////                    break;
//                case R.id.qnaButton:
//                    myStartActivity(QnaAdd.class);
//                    break;
//            }
//        }
//    };

    public void myStartActivity(Class c){
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
    }




}
