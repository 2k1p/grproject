package com.example.ibyg.Manager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

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
//카페명, 주소, 전화번호, 영업시간
public class CafeManager0 extends BasicActivity implements View.OnClickListener {
    private static final String TAG = "CafeManager0";

    private EditText editTextName;
    private EditText editTextAddress;
    private EditText editTextPhone;
    private EditText editTexttime;

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

        editCoffee1 = findViewById(R.id.CafeEdit9);
        editCoffee2 = findViewById(R.id.CafeEdit10);
        editCoffee3 = findViewById(R.id.CafeEdit11);
        editCoffee4 = findViewById(R.id.CafeEdit12);
        editPrice1 = findViewById(R.id.CafeEdit13);
        editPrice2 = findViewById(R.id.CafeEdit14);
        editPrice3 = findViewById(R.id.CafeEdit15);
        editPrice4 = findViewById(R.id.CafeEdit16);

        findViewById(R.id.button_save).setOnClickListener(this);
    }


    private void saveProduct(){
        String name = editTextName.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String time = editTexttime.getText().toString().trim();

        String coffee1 = editCoffee1.getText().toString().trim();
        String coffee2 = editCoffee2.getText().toString().trim();
        String coffee3 = editCoffee3.getText().toString().trim();
        String coffee4 = editCoffee4.getText().toString().trim();
        String price1 = editPrice1.getText().toString().trim();
        String price2 = editPrice2.getText().toString().trim();
        String price3 = editPrice3.getText().toString().trim();
        String price4 = editPrice4.getText().toString().trim();

        OwnerInfo ownerInfo = new OwnerInfo(name, address, phone, time, null, null, null, null, coffee1, coffee2, coffee3, coffee4, price1, price2, price3, price4);

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



}
