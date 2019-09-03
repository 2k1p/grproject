package com.example.ibyg.Manager;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ibyg.MainActivity;
import com.example.ibyg.R;

public class CafeManager3 extends AppCompatActivity {
    String sfName = "myFile";
    TextView textViewName, textViewAddress, textViewPhone, textViewTime, textViewwifi, textViewseat, textViewconsent, textViewprice;
    String name, address, phone, time, wifi, seat, consent, price;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cafemanager3);

        ActionBar actionBar = getSupportActionBar();  //제목줄 객체 얻어오기
        actionBar.setTitle("카페 관리");  //액션바 제목설정


        // Intent 가져오기.
        Intent intent = getIntent();

        // Name 값을 String 타입 그대로 표시.
        textViewName = findViewById(R.id.cafeTextRe5);
        name = intent.getStringExtra("name");
        if (name != null) textViewName.setText(name);
        // 지난번 저장해놨던 사용자 입력값을 꺼내서 보여주기
        SharedPreferences sf = getSharedPreferences(sfName, 0);
        name = sf.getString("name", "");
        textViewName.setText(name);

        textViewAddress = findViewById(R.id.cafeTextRe6);
        address = intent.getStringExtra("address");
        if (address != null) textViewAddress.setText(address);
        address = sf.getString("address", "");
        textViewAddress.setText(address);

        textViewPhone = findViewById(R.id.cafeTextRe7);
        phone = intent.getStringExtra("phone");
        if (phone != null) textViewPhone.setText(phone);
        phone = sf.getString("phone", "");
        textViewPhone.setText(phone);

        textViewTime = findViewById(R.id.cafeTextRe8);
        time = intent.getStringExtra("time");
        if (time != null) textViewTime.setText(time);
        time = sf.getString("time", "");
        textViewTime.setText(time);

        textViewwifi = findViewById(R.id.cafeTextRe9);
        wifi = intent.getStringExtra("wifi");
        if (wifi != null) textViewwifi.setText(wifi);
        wifi = sf.getString("wifi", "");
        textViewwifi.setText(wifi);

        textViewseat = findViewById(R.id.cafeTextRe10);
        seat = intent.getStringExtra("seat");
        if (seat != null) textViewseat.setText(seat);
        seat = sf.getString("seat", "");
        textViewseat.setText(seat);

        textViewconsent = findViewById(R.id.cafeTextRe11);
        consent = intent.getStringExtra("consent");
        if (consent != null) textViewconsent.setText(consent);
        consent = sf.getString("consent", "");
        textViewconsent.setText(consent);

        textViewprice = findViewById(R.id.cafeTextRe12);
        String price = intent.getStringExtra("price");
        if (price != null) textViewprice.setText(price);
        price = sf.getString("price", "");
        textViewprice.setText(price);


        //***해야할것: 카페등록하고 뒤로가기하면 메인으로 가는데 다시 들어가면 카페관리2화면으로 들어가지도록 수정


        findViewById(R.id.modifybutton).setOnClickListener(onClickListener);
        findViewById(R.id.deletebutton).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.modifybutton: //수정버튼:누르면 카페등록화면으로 이동
                    Modify();
                    break;
                case R.id.deletebutton: //삭제버튼:누르면 메시지가 뜬다
                    showMessage();
                    break;
            }
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        // Activity 가 종료되기 전에 저장한다
        // SharedPreferences 에 설정값(특별히 기억해야할 사용자 값)을 저장하기
        SharedPreferences sf = getSharedPreferences(sfName, 0);
        SharedPreferences.Editor editor = sf.edit();//저장하려면 editor가 필요
        String name = textViewName.getText().toString(); // 사용자가 입력한 값
        editor.putString("name", name); // 값을 수정함
        String address = textViewAddress.getText().toString(); // 사용자가 입력한 값
        editor.putString("address", address); // 값을 수정함
        String phone = textViewPhone.getText().toString(); // 사용자가 입력한 값
        editor.putString("phone", phone); // 값을 수정함
        String time = textViewTime.getText().toString(); // 사용자가 입력한 값
        editor.putString("time", time); // 값을 수정함
        String wifi = textViewwifi.getText().toString(); // 사용자가 입력한 값
        editor.putString("wifi", wifi); // 값을 수정함
        String seat = textViewseat.getText().toString(); // 사용자가 입력한 값
        editor.putString("seat", seat); // 값을 수정함
        String consent = textViewconsent.getText().toString(); // 사용자가 입력한 값
        editor.putString("consent", consent); // 값을 수정함
        String price = textViewprice.getText().toString(); // 사용자가 입력한 값
        editor.putString("price", price); // 값을 수정함

        editor.commit(); //저장함
    }



    private void Modify() {
        Intent intent = new Intent(CafeManager3.this, CafeManager2.class);

        TextView editTextName = findViewById(R.id.cafeTextRe5);
        intent.putExtra("name", editTextName.getText().toString());

        TextView editTextAddress = findViewById(R.id.cafeTextRe6);
        intent.putExtra("address", editTextAddress.getText().toString());

        TextView editTextPhone = findViewById(R.id.cafeTextRe7);
        intent.putExtra("phone", editTextPhone.getText().toString());

        TextView editTextTime = findViewById(R.id.cafeTextRe8);
        intent.putExtra("time", editTextTime.getText().toString());

        TextView editTextwifi = findViewById(R.id.cafeTextRe9);
        intent.putExtra("wifi", editTextwifi.getText().toString());

        TextView editTextseat = findViewById(R.id.cafeTextRe10);
        intent.putExtra("seat", editTextseat.getText().toString());

        TextView editTextconsent = findViewById(R.id.cafeTextRe11);
        intent.putExtra("consent", editTextconsent.getText().toString());

        TextView editTextprice = findViewById(R.id.cafeTextRe12);
        intent.putExtra("price", editTextprice.getText().toString());

        startActivity(intent);
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
    }



    private void startToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
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

                Intent intent = new Intent(CafeManager3.this, CafeManager.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
            }


        });

        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        final AlertDialog dialog = builder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface arg0) {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
                dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
            }
        });

        dialog.show();


    }
    private void myStartActivity(Class c) {
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {   //휴대폰 자체 뒤로가기
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                Intent i = new Intent(this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
                finish();
                return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                Intent i = new Intent(this, MainActivity.class);
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

