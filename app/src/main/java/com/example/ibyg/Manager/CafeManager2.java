package com.example.ibyg.Manager;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.example.ibyg.BasicActivity;
import com.example.ibyg.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CafeManager2 extends BasicActivity implements View.OnClickListener {
    private static final String TAG = "CafeManager2";

    private EditText editTextName;
    private EditText editTextAddress;
    private EditText editTextPhone;
    private EditText editTexttime;
    private EditText editTextwifi;
    private EditText editTextseat;
    private EditText editTextconsent;
    private EditText editTextprice;

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    private OwnerInfo ownerInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cafemanager2);

        setToolbarTitle("카페 수정");  //액션바 제목설정


        ownerInfo = (OwnerInfo) getIntent().getSerializableExtra("product");
        db = FirebaseFirestore.getInstance();

        editTextName = findViewById(R.id.CafeEdit);
        editTextAddress = findViewById(R.id.CafeEdit2);
        editTextPhone = findViewById(R.id.CafeEdit3);
        editTexttime = findViewById(R.id.CafeEdit4);
        editTextwifi = findViewById(R.id.CafeEdit5);
        editTextseat = findViewById(R.id.CafeEdit6);
        editTextconsent = findViewById(R.id.CafeEdit7);
        editTextprice = findViewById(R.id.CafeEdit8);

        editTextName.setText(ownerInfo.geteditTextName());
        editTextAddress.setText(ownerInfo.geteditTextAddress());
        editTextPhone.setText(ownerInfo.geteditTextPhone());
        editTexttime.setText(String.valueOf(ownerInfo.geteditTexttime()));
        editTextwifi.setText(String.valueOf(ownerInfo.geteditTextwifi()));
        editTextseat.setText(ownerInfo.geteditTextseat());
        editTextconsent.setText(ownerInfo.geteditTextconsent());
        editTextprice.setText(ownerInfo.geteditTextprice());


        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.button_update).setOnClickListener(this);
        findViewById(R.id.button_delete).setOnClickListener(this);
    }

    private void updateProduct() {  //수정
        String name = editTextName.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String time = editTexttime.getText().toString().trim();
        String wifi = editTextwifi.getText().toString().trim();
        String seat = editTextseat.getText().toString().trim();
        String consent = editTextconsent.getText().toString().trim();
        String price = editTextprice.getText().toString().trim();

        OwnerInfo p = new OwnerInfo(name, address, phone, time, wifi, seat, consent, price);

        db.collection("owner_cafe").document(ownerInfo.getId())
                .update(
                        "editTextAddress", p.geteditTextAddress(),
                        "editTextPhone", p.geteditTextPhone(),
                        "editTextName", p.geteditTextName(),
                        "editTexttime", p.geteditTexttime(),
                        "editTextwifi", p.geteditTextwifi(),
                        "editTextseat", p.geteditTextseat(),
                        "editTextconsent", p.geteditTextconsent(),
                        "editTextprice", p.geteditTextprice()
                )
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(CafeManager2.this, "카페정보 수정됨", Toast.LENGTH_LONG).show();
                    }
                });

    }

//    private void deleteProduct() {  //문서삭제
//        db.collection("owner_cafe").document(product.getId())
//                .delete()
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()) {
//                            Toast.makeText(UpdateProductActivity.this, "Product deleted", Toast.LENGTH_LONG).show();
//                            finish();
//                            startActivity(new Intent(UpdateProductActivity.this, ProductsActivity.class));
//                        }
//                    }
//                });
//    }

    public void deleteField() {
        DocumentReference docRef = db.collection("owner_cafe").document(mAuth.getUid());

        // Remove the 'capital' field from the document
        Map<String,Object> updates = new HashMap<>();
        updates.put("editTextName", FieldValue.delete());
        updates.put("editTextAddress", FieldValue.delete());
        updates.put("editTextPhone", FieldValue.delete());
        updates.put("editTexttime", FieldValue.delete());
        updates.put("editTextwifi", FieldValue.delete());
        updates.put("id", FieldValue.delete());
        updates.put("editTextseat", FieldValue.delete());
        updates.put("editTextconsent", FieldValue.delete());
        updates.put("editTextprice", FieldValue.delete());

        docRef.update(updates).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(CafeManager2.this, "카페정보 삭제됨", Toast.LENGTH_LONG).show();
                    finish();
                    startActivity(new Intent(CafeManager2.this, CafeManager.class));
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_update:
                updateProduct();
                break;
            case R.id.button_delete:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("안내");
                builder.setMessage("삭제하시겠습니까?");

                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteField();
                    }
                });

                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });


                final AlertDialog ad = builder.create();
                ad.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface arg0) {
                        ad.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
                        ad.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
                    }
                });
                ad.show();

                break;
        }
    }


}