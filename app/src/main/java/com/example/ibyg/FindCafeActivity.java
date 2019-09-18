package com.example.ibyg;

import android.os.Bundle;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ibyg.Manager.OwnerInfo;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FindCafeActivity extends BasicActivity {

    private RecyclerView recyclerView;
    private FindAdapter adapter;
    private List<OwnerInfo> cafeList;
    private ProgressBar progressBar;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_cafe);

        setToolbarTitle("카페 관리");

        recyclerView = findViewById(R.id.recyclerview_find);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cafeList = new ArrayList<>();
        adapter = new FindAdapter(this, cafeList);

        recyclerView.setAdapter(adapter);


        db = FirebaseFirestore.getInstance();


        db.collection("owner_cafe").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {


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
