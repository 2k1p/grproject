package com.example.ibyg.Manager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.example.ibyg.BasicActivity;
import com.example.ibyg.Login.MemberInitActivity;
import com.example.ibyg.Login.SignUpActivity;
import com.example.ibyg.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

//카페명, 주소, 전화번호, 영업시간, 와이파이, 콘센트, 좌석수, 최소가격
public class CafeManager extends BasicActivity implements View.OnClickListener {
    private static final String TAG = "CafeManager";

    private EditText editTextName;
    private EditText editTextAddress;
    private EditText editTextPhone;
    private EditText editTexttime;
    private EditText editTextwifi;
    private EditText editTextseat;
    private EditText editTextconsent;
    private EditText editTextprice;

    private EditText editCoffee1;
    private EditText editCoffee2;
    private EditText editCoffee3;
    private EditText editCoffee4;
    private EditText editPrice1;
    private EditText editPrice2;
    private EditText editPrice3;
    private EditText editPrice4;

    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cafemanager);

        setToolbarTitle("카페 등록");  //액션바 제목설정

        LinearLayout layout = findViewById(R.id.ahpshow);
        layout.setVisibility(View.VISIBLE); //의사결정시스템 정보수집 숨긴거 보이게
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        if(user == null){
            startActivity(new Intent(this, SignUpActivity.class));
        }else{

            db = FirebaseFirestore.getInstance();
            DocumentReference docRef = db.collection("users").document(user.getUid());
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if(document != null){

                            if (document.exists()) {
                                Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                            } else {
                                Log.d(TAG, "No such document");
                                startActivity(new Intent(getApplication(), MemberInitActivity.class));
                            }

                        }
                    } else {
                        Log.d(TAG, "get failed with ", task.getException());
                    }
                }
            });

        }

        db = FirebaseFirestore.getInstance();

        editTextName = findViewById(R.id.CafeEdit);
        editTextAddress = findViewById(R.id.CafeEdit2);
        editTextPhone = findViewById(R.id.CafeEdit3);
        editTexttime = findViewById(R.id.CafeEdit4);
        editTextwifi = findViewById(R.id.CafeEdit5);
        editTextseat = findViewById(R.id.CafeEdit6);
        editTextconsent = findViewById(R.id.CafeEdit7);
        editTextprice = findViewById(R.id.CafeEdit8);

        editCoffee1 = findViewById(R.id.CafeEdit9);
        editPrice1 = findViewById(R.id.CafeEdit13);
        editCoffee2 = findViewById(R.id.CafeEdit10);
        editPrice2 = findViewById(R.id.CafeEdit14);
        editCoffee3 = findViewById(R.id.CafeEdit11);
        editPrice3 = findViewById(R.id.CafeEdit15);
        editCoffee4 = findViewById(R.id.CafeEdit12);
        editPrice4 = findViewById(R.id.CafeEdit16);

        findViewById(R.id.button_save).setOnClickListener(this);
    }


    private void saveProduct(){
        String name = editTextName.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String time = editTexttime.getText().toString().trim();
        String wifi = editTextwifi.getText().toString().trim();
        String seat = editTextseat.getText().toString().trim();
        String consent = editTextconsent.getText().toString().trim();
        String price = editTextprice.getText().toString().trim();

        String coffee1 = editCoffee1.getText().toString().trim();
        String coffee2 = editCoffee2.getText().toString().trim();
        String coffee3 = editCoffee3.getText().toString().trim();
        String coffee4 = editCoffee4.getText().toString().trim();
        String price1 = editPrice1.getText().toString().trim();
        String price2 = editPrice2.getText().toString().trim();
        String price3 = editPrice3.getText().toString().trim();
        String price4 = editPrice4.getText().toString().trim();

        OwnerInfo ownerInfo = new OwnerInfo(name, address, phone, time, wifi, seat, consent, price, coffee1, coffee2, coffee3, coffee4, price1, price2, price3, price4);

        if(name.length() > 0 && address.length() > 0 && phone.length() > 0 && time.length() > 0
                ){
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            if(user != null){
                db.collection("owner_cafe").document(user.getUid()).set(ownerInfo)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                startToast("카페정보 등록을 성공하였습니다.");
                                myStartActivity(CafeActivity.class);
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


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_save:  //등록버튼
                saveProduct();
                break;

        }

    }

    private void myStartActivity(Class c, String media, int requestCode) {
        Intent intent = new Intent(this, c);
        intent.putExtra("media", media);
        startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
    }


}
