package com.example.ibyg.Manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ibyg.BasicActivity;
import com.example.ibyg.MainActivity;
import com.example.ibyg.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

//등록한 카페정보 표시(recyclerview)
public class CafeActivity extends BasicActivity {
    private static final String TAG = "CafeActivity";
    private RecyclerView recyclerView;
    private CafeAdapter adapter;
    private List<OwnerInfo> cafeList;
    private ProgressBar progressBar;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cafe_recyclerview);

        setToolbarTitle("카페 관리");

        progressBar = findViewById(R.id.progressbar);

        recyclerView = findViewById(R.id.recyclerview_cafe);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cafeList = new ArrayList<>();
        adapter = new CafeAdapter(this, cafeList);

        recyclerView.setAdapter(adapter);


        db = FirebaseFirestore.getInstance();


        db.collection("owner_cafe").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        progressBar.setVisibility(View.GONE);

                        if (!queryDocumentSnapshots.isEmpty()) {

                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();

                            for (DocumentSnapshot d : list) {

                                OwnerInfo p = d.toObject(OwnerInfo.class);
                                p.setId(d.getId());
                                cafeList.add(p);

                            }

                            adapter.notifyDataSetChanged();

                        }


                    }
                });

    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {   //핸드폰 자체 뒤로가기
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                Intent i = new Intent(this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
                return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {   //툴바 뒤로가기
        switch (item.getItemId()){
            case android.R.id.home:{
                Intent i = new Intent(this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }


}
