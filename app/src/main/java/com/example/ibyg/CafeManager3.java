package com.example.ibyg;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class CafeManager3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cafemanager3);

        ActionBar actionBar = getSupportActionBar();  //제목줄 객체 얻어오기
        actionBar.setTitle("카페 관리");  //액션바 제목설정

        //업버튼이 되려면 눌렀을 때 돌아갈 Activity를 지정해줘야 함
        //이 작업은 매니패스트에서 함

        // Intent 가져오기.
        Intent intent = getIntent();

        // Name 값을 String 타입 그대로 표시.
        TextView textViewName = findViewById(R.id.cafeTextRe5);
        String name = intent.getStringExtra("name");
        if (name != null)
            textViewName.setText(name);

        // Address 값을 String 타입 그대로 표시.
        TextView textViewAddress = findViewById(R.id.cafeTextRe6);
        String address = intent.getStringExtra("address");
        if (address != null)
            textViewAddress.setText(address);

        // Phone 값을 String 타입 그대로 표시.
        TextView textViewPhone = findViewById(R.id.cafeTextRe7);
        String phone = intent.getStringExtra("phone");
        if (phone != null)
            textViewPhone.setText(phone);

        // Time 값을 String 타입 그대로 표시.
        TextView textViewTime = findViewById(R.id.cafeTextRe8);
        String time = intent.getStringExtra("time");
        if (time != null)
            textViewTime.setText(time);


        //***해야할것: 카페등록하고 뒤로가기하면 메인으로 가는데 다시 들어가면 카페관리화면으로 들어가지도록 수정
        //액션바의 뒤로가기하고 폰의 뒤로가기버튼하고 똑같이 화면전환되도록 수정

        Button button = findViewById(R.id.cafebutton3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CafeManager3.this, CafeManager4.class));
            }
        });
        Button button2 = findViewById(R.id.deletebutton);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMessage();
            }
        });
    }
        public void showMessage() {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("안내");
            builder.setMessage("정말 삭제하시겠습니까?");
           builder.setIcon(android.R.drawable.ic_dialog_alert);

            builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    // Name 값 초기화
                    TextView textViewName = findViewById(R.id.cafeTextRe5);
                        textViewName.setText(null);
                    // Address 값 초기화
                    TextView textViewAddress = findViewById(R.id.cafeTextRe6);
                        textViewAddress.setText(null);
                    // Phone 값 초기화
                    TextView textViewPhone = findViewById(R.id.cafeTextRe7);
                        textViewPhone.setText(null);
                    // Time 값 초기화
                    TextView textViewTime = findViewById(R.id.cafeTextRe8);
                        textViewTime.setText(null);

                    Intent intent = new Intent(CafeManager3.this, CafeManager.class) ;
                    startActivity(intent) ;
                }
            });

            builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }
}

