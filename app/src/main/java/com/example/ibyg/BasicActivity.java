package com.example.ibyg;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class BasicActivity extends AppCompatActivity {  //중복되는 기능 상속가능한 액티비티
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    public void setToolbarTitle(String title){
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle(title);
            actionBar.setDisplayHomeAsUpEnabled(true);   //뒤로가기버튼
        }
    }

    public void startToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void myStartActivity(Class c){
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
    }


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {   //핸드폰 자체 뒤로가기
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                //Intent i = new Intent(this, FirstReady.class);
                //i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //startActivity(i);
                finish();
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
                return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {   //툴바 뒤로가기
        switch (item.getItemId()){
            case android.R.id.home:{
                //Intent i = new Intent(this, MainActivity.class);
                //i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //startActivity(i);
                finish();
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
