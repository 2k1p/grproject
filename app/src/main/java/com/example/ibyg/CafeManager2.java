package com.example.ibyg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

        // Intent 가져오기.
        Intent intent = getIntent();

        // Name 값을 String 타입 그대로 표시.
        TextView textViewName = findViewById(R.id.cafeEdit) ;
        String name = intent.getStringExtra("name") ;
        if (name != null)
            textViewName.setText(name) ;

        // Address 값을 String 타입 그대로 표시.
        TextView textViewAddress = findViewById(R.id.cafeEdit2) ;
        String address = intent.getStringExtra("address") ;
        if (address != null)
            textViewAddress.setText(address) ;

        // Phone 값을 String 타입 그대로 표시.
        TextView textViewPhone = findViewById(R.id.cafeEdit3) ;
        String phone = intent.getStringExtra("phone") ;
        if (phone != null)
            textViewPhone.setText(phone) ;

        // Time 값을 String 타입 그대로 표시.
        TextView textViewTime = findViewById(R.id.cafeEdit4) ;
        String time = intent.getStringExtra("time") ;
        if (time != null)
            textViewTime.setText(time) ;

        TextView textViewwifi = findViewById(R.id.cafeEdit8) ;
        String wifi = intent.getStringExtra("wifi") ;
        if (wifi != null)
            textViewwifi.setText(wifi) ;

        TextView textViewseat = findViewById(R.id.cafeEdit5) ;
        String seat = intent.getStringExtra("seat") ;
        if (seat != null)
            textViewseat.setText(seat) ;

        TextView textViewconsent = findViewById(R.id.cafeEdit6) ;
        String consent = intent.getStringExtra("consent") ;
        if (consent != null)
            textViewconsent.setText(consent) ;

        TextView textViewprice = findViewById(R.id.cafeEdit7) ;
        String price = intent.getStringExtra("price") ;
        if (price != null)
            textViewprice.setText(price) ;



        Button button = findViewById(R.id.cafebutton2); //등록버튼
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
