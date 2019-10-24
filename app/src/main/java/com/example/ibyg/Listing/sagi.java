package com.example.ibyg.Listing;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ibyg.BasicActivity;
import com.example.ibyg.Manager.OwnerInfo;
import com.example.ibyg.R;
import com.example.ibyg.fragment.ListingActivity;
import com.example.ibyg.fragment.LoginFragment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class sagi extends BasicActivity {
    private RecyclerView recyclerView;
    private FindAdapter adapter;
    private List<OwnerInfo> cafeList;
    private ProgressBar progressBar;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sagi);

        setToolbarTitle("내 주변 카페");

        findViewById(R.id.mapButton).setOnClickListener(onClickListener);


        //하단 네비게이션바 추가
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_android:   //홈버튼
                        myStartActivity(ListingActivity.class);
                        break;

                    case R.id.ic_books:     //설정버튼
                        myStartActivity(LoginFragment.class);
                        break;
                }
                return false;
            }
        });

        recyclerView = findViewById(R.id.recyclerview_find);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cafeList = new ArrayList<>();
        adapter = new FindAdapter(this, cafeList);

        recyclerView.setAdapter(adapter);


        db = FirebaseFirestore.getInstance();


        db.collection("owner_cafe").orderBy("editTextName", Query.Direction.DESCENDING).get()
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

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.mapButton:        //카카오맵으로
//                    Intent intent = new Intent (Intent.ACTION_VIEW, Uri.parse("https://map.kakao.com"));
//                    startActivity(intent);
                    myStartActivity(KakaoWeb.class);
                    break;
            }
        }
    };

}


