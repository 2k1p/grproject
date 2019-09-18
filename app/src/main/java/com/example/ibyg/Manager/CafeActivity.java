package com.example.ibyg.Manager;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ibyg.BasicActivity;
import com.example.ibyg.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

//등록한 카페정보 표시(recyclerview)
public class CafeActivity extends BasicActivity {

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


}
