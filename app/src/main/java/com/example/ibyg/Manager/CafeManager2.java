package com.example.ibyg.Manager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ibyg.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class CafeManager2 extends AppCompatActivity {
    private static final String TAG = "CafeManager2";
    String sfName = "myFile";
    EditText textViewName, textViewAddress, textViewPhone, textViewTime, textViewwifi, textViewseat, textViewconsent, textViewprice;
    String name, address, phone, time, wifi, seat, consent, price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cafemanager2);

        ActionBar actionBar = getSupportActionBar();  //제목줄 객체 얻어오기
        actionBar.setTitle("카페 등록");  //액션바 제목설정
        actionBar.setDisplayHomeAsUpEnabled(true);   //뒤로가기버튼 <- 만들기
        //업버튼이 되려면 눌렀을 때 돌아갈 Activity를 지정해줘야 함
        //이 작업은 매니패스트에서 함

        findViewById(R.id.cafebutton2).setOnClickListener(onClickListener);


        // Intent 가져오기.
        Intent intent = getIntent();

        // Name 값을 String 타입 그대로 표시.
        textViewName = findViewById(R.id.cafeEdit) ;
        name = intent.getStringExtra("name") ;
        if (name != null) textViewName.setText(name) ;
        // 지난번 저장해놨던 사용자 입력값을 꺼내서 보여주기
        SharedPreferences sf = getSharedPreferences(sfName, 0);
        name = sf.getString("name", "");
        textViewName.setText(name);

        textViewAddress = findViewById(R.id.cafeEdit2) ;
        address = intent.getStringExtra("address") ;
        if (address != null) textViewAddress.setText(address) ;
        address = sf.getString("address", "");
        textViewAddress.setText(address);

        textViewPhone = findViewById(R.id.cafeEdit3) ;
        phone = intent.getStringExtra("phone") ;
        if (phone != null) textViewPhone.setText(phone) ;
        phone = sf.getString("phone", "");
        textViewPhone.setText(phone);

        textViewTime = findViewById(R.id.cafeEdit4) ;
        time = intent.getStringExtra("time") ;
        if (time != null) textViewTime.setText(time) ;
        time = sf.getString("time", "");
        textViewTime.setText(time);

        textViewwifi = findViewById(R.id.cafeEdit8) ;
        wifi = intent.getStringExtra("wifi") ;
        if (wifi != null) textViewwifi.setText(wifi) ;
        wifi = sf.getString("wifi", "");
        textViewwifi.setText(wifi);

        textViewseat = findViewById(R.id.cafeEdit5) ;
        seat = intent.getStringExtra("seat") ;
        if (seat != null) textViewseat.setText(seat) ;
        seat = sf.getString("seat", "");
        textViewseat.setText(seat);

        textViewconsent = findViewById(R.id.cafeEdit6) ;
        consent = intent.getStringExtra("consent") ;
        if (consent != null) textViewconsent.setText(consent) ;
        consent = sf.getString("consent", "");
        textViewconsent.setText(consent);

        textViewprice = findViewById(R.id.cafeEdit7) ;
        price = intent.getStringExtra("price") ;
        if (price != null) textViewprice.setText(price) ;
        price = sf.getString("price", "");
        textViewprice.setText(price);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.cafebutton2:  //등록버튼
                    CafeUpdate();
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



    private void CafeUpdate(){
        final Intent intent = new Intent(CafeManager2.this, CafeManager3.class) ;

        // Name 입력 값을 String 값으로 그대로 전달.
        String editTextName = ((EditText)findViewById(R.id.cafeEdit)).getText().toString();
        EditText editTextName2 = findViewById(R.id.cafeEdit);
        intent.putExtra("name", editTextName2.getText().toString()) ;

        String textViewAddress = ((EditText)findViewById(R.id.cafeEdit2)).getText().toString();
        EditText editTextAddress2 = findViewById(R.id.cafeEdit2) ;
        intent.putExtra("address", editTextAddress2.getText().toString()) ;

        String editTextPhone = ((EditText)findViewById(R.id.cafeEdit3)).getText().toString();
        EditText editTextPhone2 =  findViewById(R.id.cafeEdit3) ;
        intent.putExtra("phone", editTextPhone2.getText().toString()) ;

        String textViewTime = ((EditText)findViewById(R.id.cafeEdit4)).getText().toString();
        EditText editTexttime2 =  findViewById(R.id.cafeEdit4) ;
        intent.putExtra("time", editTexttime2.getText().toString()) ;

        String textViewwifi = ((EditText)findViewById(R.id.cafeEdit8)).getText().toString();
        EditText editTextwifi2 =  findViewById(R.id.cafeEdit8) ;
        intent.putExtra("wifi", editTextwifi2.getText().toString()) ;

        String textViewseat = ((EditText)findViewById(R.id.cafeEdit5)).getText().toString();
        EditText editTextseat2 =  findViewById(R.id.cafeEdit5) ;
        intent.putExtra("seat", editTextseat2.getText().toString()) ;

        String textViewconsent = ((EditText)findViewById(R.id.cafeEdit6)).getText().toString();
        EditText editTextconsent2 =  findViewById(R.id.cafeEdit6) ;
        intent.putExtra("consent", editTextconsent2.getText().toString()) ;

        String textViewprice = ((EditText)findViewById(R.id.cafeEdit7)).getText().toString();
        EditText editTextprice2 =  findViewById(R.id.cafeEdit7) ;
        intent.putExtra("price", editTextprice2.getText().toString()) ;


        if(editTextName.length() > 0 && textViewAddress.length() > 0 && editTextPhone.length() > 0 && textViewTime.length() > 0
                && textViewwifi.length() > 0 && textViewseat.length() > 0 && textViewconsent.length() > 0 && textViewprice.length() > 0){
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            OwnerInfo ownerInfo = new OwnerInfo(editTextName, textViewAddress, editTextPhone, textViewTime, textViewwifi, textViewseat, textViewconsent, textViewprice);
            if(user != null){
                db.collection("owner_cafe").document(user.getUid()).set(ownerInfo)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                startToast("카페정보 등록을 성공하였습니다.");
                                startActivity(intent);
                                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                startToast("카페정보 등록에 실패하였습니다.");
                                Log.w(TAG, "Error writing document", e);
                            }
                        });
            }

        }else{
            startToast("카페정보를 입력해주세요.");
        }

    }

    private void startToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void myStartActivity(Class c){
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {   //휴대폰 자체 뒤로가기
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                Intent i = new Intent(this, CafeManager.class);
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
                Intent i = new Intent(this, CafeManager.class);
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
