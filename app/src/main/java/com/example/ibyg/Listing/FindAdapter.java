package com.example.ibyg.Listing;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ibyg.Manager.OwnerInfo;
import com.example.ibyg.R;

import java.util.List;
//내 주변 카페 어뎁터(리싸이클뷰)
public class FindAdapter extends RecyclerView.Adapter<FindAdapter.ProductViewHolder> {
    private Activity activity;
    private List<OwnerInfo> cafeList;
    private final int MORE_INDEX = 2;

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ProductViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }

    public FindAdapter(Activity activity, List<OwnerInfo> productList) {
        this.activity = activity;
        this.cafeList = productList;
    }

    @NonNull
    @Override
    public FindAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.find_cafe, parent, false);
        final ProductViewHolder mainViewHolder = new ProductViewHolder(cardView);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, FindView2.class);
                intent.putExtra("postInfo", cafeList.get(mainViewHolder.getAdapterPosition()));
                activity.startActivity(intent);
            }
        });

        return mainViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FindAdapter.ProductViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        TextView titleTextView = cardView.findViewById(R.id.titleTextView);

        OwnerInfo ownerInfo = cafeList.get(position);
        titleTextView.setText(ownerInfo.geteditTextName());

        FindAdapter2 readContentsVIew = cardView.findViewById(R.id.readContentsView);
            readContentsVIew.setMoreIndex(MORE_INDEX);
            readContentsVIew.setPostInfo(ownerInfo);



//        holder.textViewName.setText("카페명 : " + ownerInfo.geteditTextName());
//        holder.textViewAddress.setText("주소 : " + ownerInfo.geteditTextAddress());
//        holder.textViewPhone.setText("전화번호 : " + ownerInfo.geteditTextPhone());
//        holder.textViewTime.setText("영업시간 : " + ownerInfo.geteditTexttime());

    }

    @Override
    public int getItemCount() {
        return cafeList.size();
    }



}
