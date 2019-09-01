package com.example.ibyg.Manager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
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
        TextView textViewName = findViewById(R.id.cafeEdit) ;
        String name = intent.getStringExtra("name") ;
        if (name != null) textViewName.setText(name) ;

        TextView textViewAddress = findViewById(R.id.cafeEdit2) ;
        String address = intent.getStringExtra("address") ;
        if (address != null) textViewAddress.setText(address) ;

        TextView textViewPhone = findViewById(R.id.cafeEdit3) ;
        String phone = intent.getStringExtra("phone") ;
        if (phone != null) textViewPhone.setText(phone) ;

        TextView textViewTime = findViewById(R.id.cafeEdit4) ;
        String time = intent.getStringExtra("time") ;
        if (time != null) textViewTime.setText(time) ;

        TextView textViewwifi = findViewById(R.id.cafeEdit8) ;
        String wifi = intent.getStringExtra("wifi") ;
        if (wifi != null) textViewwifi.setText(wifi) ;

        TextView textViewseat = findViewById(R.id.cafeEdit5) ;
        String seat = intent.getStringExtra("seat") ;
        if (seat != null) textViewseat.setText(seat) ;

        TextView textViewconsent = findViewById(R.id.cafeEdit6) ;
        String consent = intent.getStringExtra("consent") ;
        if (consent != null) textViewconsent.setText(consent) ;

        TextView textViewprice = findViewById(R.id.cafeEdit7) ;
        String price = intent.getStringExtra("price") ;
        if (price != null) textViewprice.setText(price) ;

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

    private void CafeUpdate(){
        Intent intent = new Intent(CafeManager2.this, CafeManager3.class) ;

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

        startActivity(intent) ;
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);

        if(editTextName.length() > 0 ){
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            OwnerInfo ownerInfo = new OwnerInfo(editTextName, textViewAddress, editTextPhone, textViewTime, textViewwifi, textViewseat, textViewconsent, textViewprice);
            if(user != null){
                db.collection("owner_cafe").document(user.getUid()).set(ownerInfo)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                startToast("카페정보 등록을 성공하였습니다.");
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
