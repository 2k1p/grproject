package com.example.ibyg.Listing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.ibyg.BasicActivity;
import com.example.ibyg.Manager.OwnerInfo;
import com.example.ibyg.R;
import com.example.ibyg.fragment.ListingActivity;
import com.example.ibyg.fragment.LoginFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

//내 주변 카페, 해당카페 누를시 카페정보 보여줄 화면
public class FindView2 extends BasicActivity {
    private OwnerInfo ownerInfo;
    private FindAdapter2 readContentsVIew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        ownerInfo = (OwnerInfo) getIntent().getSerializableExtra("postInfo");

        readContentsVIew = findViewById(R.id.readContentsView);

        uiUpdate();

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
                    case R.id.ic_android:
                        myStartActivity(ListingActivity.class);
                        break;

                    case R.id.ic_books:
                        myStartActivity(LoginFragment.class);
                        break;
                }
                return false;
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0:
                if (resultCode == Activity.RESULT_OK) {
                    ownerInfo = (OwnerInfo)data.getSerializableExtra("postinfo");
                    uiUpdate();
                }
                break;
        }
    }



    private void uiUpdate(){
        setToolbarTitle(ownerInfo.geteditTextName());
        readContentsVIew.setPostInfo(ownerInfo);
    }

    private void myStartActivity(Class c, OwnerInfo ownerInfo) {
        Intent intent = new Intent(this, c);
        intent.putExtra("postInfo", ownerInfo);
        startActivityForResult(intent, 0);
    }
}
