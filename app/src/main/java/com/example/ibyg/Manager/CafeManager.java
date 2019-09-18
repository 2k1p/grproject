package com.example.ibyg.Manager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.example.ibyg.BasicActivity;
import com.example.ibyg.Login.MemberInitActivity;
import com.example.ibyg.R;
import com.example.ibyg.Login.SignUpActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class CafeManager extends BasicActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

    private EditText editTextName;
    private EditText editTextAddress;
    private EditText editTextPhone;
    private EditText editTexttime;
    private EditText editTextwifi;
    private EditText editTextseat;
    private EditText editTextconsent;
    private EditText editTextprice;

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
        editTextwifi = findViewById(R.id.CafeEdit5);
        editTextseat = findViewById(R.id.CafeEdit6);
        editTextconsent = findViewById(R.id.CafeEdit7);
        editTextprice = findViewById(R.id.CafeEdit8);

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

        OwnerInfo ownerInfo = new OwnerInfo(name, address, phone, time, wifi, seat, consent, price);

        if(name.length() > 0 && address.length() > 0 && phone.length() > 0 && time.length() > 0
                && wifi.length() <= 1 && seat.length() > 0 && consent.length() > 0 && price.length() > 0){
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
