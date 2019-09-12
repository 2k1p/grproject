package com.example.ibyg.Notice;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ibyg.BasicActivity;
import com.example.ibyg.MemberInitActivity;
import com.example.ibyg.R;
import com.example.ibyg.SignUpActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Date;

import static com.example.ibyg.Notice.Util.showToast;

public class NoticeManagement extends BasicActivity {
    private static final String TAG = "NoticeManagement";
    private FirebaseUser firebaseUser;
    private FirebaseFirestore firebaseFirestore;
    private StorageReference storageRef;
    private MainAdapter mainAdapter;
    private ArrayList<OwnerNoticeInfo> postList;
    private int successCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notice_management);

        setToolbarTitle("공지사항 관리");

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();

        if (firebaseUser == null) {
            myStartActivity(SignUpActivity.class);
        } else {
            firebaseFirestore = FirebaseFirestore.getInstance();
            DocumentReference documentReference = firebaseFirestore.collection("users").document(firebaseUser.getUid());
            documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document != null) {
                            if (document.exists()) {
                                Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                            } else {
                                Log.d(TAG, "No such document");
                                myStartActivity(MemberInitActivity.class);
                            }
                        }
                    } else {
                        Log.d(TAG, "get failed with ", task.getException());
                    }
                }
            });
        }

        postList = new ArrayList<>();
        mainAdapter = new MainAdapter(NoticeManagement.this, postList);
        mainAdapter.setOnPostListener(onPostListener);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        findViewById(R.id.noticeaddbutton).setOnClickListener(onClickListener);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(NoticeManagement.this));
        recyclerView.setAdapter(mainAdapter);
    }

    @Override
    protected void onResume(){
        super.onResume();
        postsUpdate();
    }

    OnPostListener onPostListener = new OnPostListener() {
        @Override
        public void onDelete(int position) {
            final String id = postList.get(position).getId();
            ArrayList<String> contentsList = postList.get(position).getContents();
            for (int i = 0; i < contentsList.size(); i++) {
                String contents = contentsList.get(i);
                if (Patterns.WEB_URL.matcher(contents).matches() && contents.contains("https://firebasestorage.googleapis.com/v0/b/ibyg-project.appspot.com/o/owner_notice")) {
                    successCount++;
                    String[] list = contents.split("\\?");
                    String[] list2 = list[0].split("%2F");
                    String name = list2[list2.length - 1];
                    StorageReference desertRef = storageRef.child("owner_notice/"+id+"/"+name);
                    desertRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            successCount--;
                            storeUploader(id);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            showToast(NoticeManagement.this, "ERROR");
                        }
                    });
                }
            }
            storeUploader(id);
        }

        @Override
        public void onModify(int position) {
            myStartActivity(NoticeAdd.class, postList.get(position));
        }
    };

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                /*
                case R.id.logoutButton:
                    FirebaseAuth.getInstance().signOut();
                    myStartActivity(SignUpActivity.class);
                    break;
                */
                case R.id.noticeaddbutton:
                    myStartActivity(NoticeAdd.class);
                    break;
            }
        }
    };

    private void postsUpdate(){
        if (firebaseUser != null) {
            CollectionReference collectionReference = firebaseFirestore.collection("owner_notice");
            collectionReference.orderBy("createdAt", Query.Direction.DESCENDING).get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                postList.clear();
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d(TAG, document.getId() + " => " + document.getData());
                                    postList.add(new OwnerNoticeInfo(
                                            document.getData().get("title").toString(),
                                            (ArrayList<String>) document.getData().get("contents"),
                                            document.getData().get("publisher").toString(),
                                            new Date(document.getDate("createdAt").getTime()),
                                            document.getId()));
                                }
                                mainAdapter.notifyDataSetChanged();
                            } else {
                                Log.d(TAG, "Error getting documents: ", task.getException());
                            }
                        }
                    });
        }
    }

    private void storeUploader(String id){
        if(successCount == 0) {
            firebaseFirestore.collection("owner_notice").document(id)
                    .delete()
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            showToast(NoticeManagement.this,"게시글을 삭제하였습니다.");
                            postsUpdate();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            showToast(NoticeManagement.this,"게시글을 삭제하지 못하였습니다.");
                        }
                    });
        }
    }


    private void myStartActivity(Class c, OwnerNoticeInfo ownerNoticeInfo) {
        Intent intent = new Intent(this, c);
        intent.putExtra("ownerNoticeInfo", ownerNoticeInfo);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
    }



}