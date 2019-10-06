package com.example.ibyg.Listing;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.ibyg.Manager.OwnerInfo;
import com.example.ibyg.R;

import java.util.ArrayList;

//
//내 주변 카페, 해당카페 누를시 카페정보 보여줄 화면 어뎁터
public class FindAdapter2 extends LinearLayout {
    private Context context;
    private int moreIndex = -1;
    private Activity activity;
    private ArrayList<OwnerInfo> mDataset;

    public FindAdapter2(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public FindAdapter2(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.context = context;
        initView();
    }

    private void initView(){
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setOrientation(LinearLayout.VERTICAL);
        LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.view_post, this, true);
    }

    public void setMoreIndex(int moreIndex){
        this.moreIndex = moreIndex;
    }

    public void setPostInfo(OwnerInfo ownerInfo){



        TextView text11 = findViewById(R.id.Text11);
        text11.setText("카페명");
        TextView text1 = findViewById(R.id.Text1);
        text1.setText(ownerInfo.geteditTextName());

        TextView text12 = findViewById(R.id.Text12);
        text12.setText("주소");
        TextView text2 = findViewById(R.id.Text2);
        text2.setText(ownerInfo.geteditTextAddress());

        TextView text13 = findViewById(R.id.Text13);
        text13.setText("전화번호");
        TextView text3 = findViewById(R.id.Text3);
        text3.setText(ownerInfo.geteditTextPhone());

        TextView text14 = findViewById(R.id.Text14);
        text14.setText("영업시간");
        TextView text4 = findViewById(R.id.Text4);
        text4.setText(ownerInfo.geteditTexttime());


        TextView text15 = findViewById(R.id.Text15);    //메뉴이름
        text15.setText(ownerInfo.geteditCoffee1());
        TextView text5 = findViewById(R.id.Text5);      //메뉴가격
        text5.setText(ownerInfo.geteditPrice1());

        TextView text16 = findViewById(R.id.Text16);
        text16.setText(ownerInfo.geteditCoffee2());
        TextView text6 = findViewById(R.id.Text6);
        text6.setText(ownerInfo.geteditPrice2());

        TextView text17 = findViewById(R.id.Text17);
        text17.setText(ownerInfo.geteditCoffee3());
        TextView text7 = findViewById(R.id.Text7);
        text7.setText(ownerInfo.geteditPrice3());

        TextView text18 = findViewById(R.id.Text18);
        text18.setText(ownerInfo.geteditCoffee4());
        TextView text8 = findViewById(R.id.Text8);
        text8.setText(ownerInfo.geteditPrice4());

    }
}