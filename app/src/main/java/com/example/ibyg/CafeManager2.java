package com.example.ibyg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class CafeManager2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cafemanager2);

        ActionBar actionBar = getSupportActionBar();  //제목줄 객체 얻어오기
        actionBar.setTitle("카페 등록");  //액션바 제목설정
        actionBar.setDisplayHomeAsUpEnabled(true);   //뒤로가기버튼 <- 만들기
        //업버튼이 되려면 눌렀을 때 돌아갈 Activity를 지정해줘야 함
        //이 작업은 매니패스트에서 함





        Button button = findViewById(R.id.cafebutton2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CafeManager2.this, CafeManager3.class) ;

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

                EditText editTextwifi =  findViewById(R.id.cafeEdit8) ;
                intent.putExtra("wifi", editTextwifi.getText().toString()) ;



                EditText editTextseat =  findViewById(R.id.cafeEdit5) ;
                intent.putExtra("seat", editTextseat.getText().toString()) ;

                EditText editTextconsent =  findViewById(R.id.cafeEdit6) ;
                intent.putExtra("consent", editTextconsent.getText().toString()) ;

                EditText editTextprice =  findViewById(R.id.cafeEdit7) ;
                intent.putExtra("price", editTextprice.getText().toString()) ;

                startActivity(intent) ;
            }
        });

    }
}
