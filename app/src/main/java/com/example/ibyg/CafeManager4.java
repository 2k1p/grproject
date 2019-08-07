package com.example.ibyg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class CafeManager4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cafemanager4);

        ActionBar actionBar = getSupportActionBar();  //제목줄 객체 얻어오기
        actionBar.setTitle("카페 수정");  //액션바 제목설정

        actionBar.setDisplayHomeAsUpEnabled(true);   //업버튼 <- 만들기
        //업버튼이 되려면 눌렀을 때 돌아갈 Activity를 지정해줘야 함
        //이 작업은 매니패스트에서 함

        // Intent 가져오기.
        Intent intent = getIntent() ;

        // Name 값을 String 타입 그대로 표시.
        TextView textViewName = findViewById(R.id.cafeTextRe5) ;
        String name = intent.getStringExtra("name") ;
        if (name != null)
            textViewName.setText(name) ;

        // Address 값을 String 타입 그대로 표시.
        TextView textViewAddress = findViewById(R.id.cafeTextRe6) ;
        String address = intent.getStringExtra("address") ;
        if (address != null)
            textViewAddress.setText(address) ;

        // Phone 값을 String 타입 그대로 표시.
        TextView textViewPhone = findViewById(R.id.cafeTextRe7) ;
        String phone = intent.getStringExtra("phone") ;
        if (phone != null)
            textViewPhone.setText(phone) ;

        // Time 값을 String 타입 그대로 표시.
        TextView textViewTime = findViewById(R.id.cafeTextRe8) ;
        String time = intent.getStringExtra("time") ;
        if (time != null)
            textViewTime.setText(time) ;



        Button button = findViewById(R.id.cafebutton4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CafeManager4.this, CafeManager3.class) ;

                // Name 입력 값을 String 값으로 그대로 전달.
                EditText editTextName = findViewById(R.id.cafeEdit) ;
                intent.putExtra("name", editTextName.getText().toString()) ;

                // Address 입력 값을 String 값으로 그대로 전달.
                EditText editTextAddress = findViewById(R.id.cafeEdit2) ;
                intent.putExtra("address", editTextAddress.getText().toString()) ;

                // Phone 입력 값을 String 값으로 그대로 전달.
                EditText editTextPhone =  findViewById(R.id.cafeEdit3) ;
                intent.putExtra("phone", editTextPhone.getText().toString()) ;

                // Time 입력 값을 String 값으로 그대로 전달.
                EditText editTexttime =  findViewById(R.id.cafeEdit4) ;
                intent.putExtra("time", editTexttime.getText().toString()) ;

                startActivity(intent) ;
            }
        });


    }
}
