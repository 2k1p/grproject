package com.example.ibyg;


import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordResetActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        ActionBar actionBar = getSupportActionBar();    //제목줄 객체 얻어오기
        actionBar.setDisplayHomeAsUpEnabled(true);      //뒤로가기버튼

        findViewById(R.id.sendButton).setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.sendButton:
                    send();
                    break;
            }
        }
    };

    private void send(){
        String email = ((EditText)findViewById(R.id.emailEditText)).getText().toString();





        if(email.length() > 0){
                        mAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                startToast("이메일을 보냈습니다.");
                            }
                        }
                    });
        }else{
            startToast("이메일을 입력해 주세요.");
        }
        }


    private void startToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                Intent i = new Intent(this, LoginActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
                finish();
                return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {   //툴바 뒤로가기
        switch (item.getItemId()){
            case android.R.id.home:{
                Intent i = new Intent(this, LoginActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }


}

