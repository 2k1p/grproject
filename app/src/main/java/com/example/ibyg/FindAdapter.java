package com.example.ibyg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ibyg.Manager.OwnerInfo;

import java.util.List;

public class FindAdapter extends RecyclerView.Adapter<FindAdapter.ProductViewHolder> {

    private Context mCtx;
    private List<OwnerInfo> cafeList;

    public FindAdapter(Context mCtx, List<OwnerInfo> productList) {
        this.mCtx = mCtx;
        this.cafeList = productList;
    }

    @NonNull
    @Override
    public FindAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FindAdapter.ProductViewHolder(
                LayoutInflater.from(mCtx).inflate(R.layout.find_cafe, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull FindAdapter.ProductViewHolder holder, int position) {
        OwnerInfo ownerInfo = cafeList.get(position);

        holder.textViewName.setText("카페명 : " + ownerInfo.geteditTextName());
        holder.textViewAddress.setText("주소 : " + ownerInfo.geteditTextAddress());
        holder.textViewPhone.setText("전화번호 : " + ownerInfo.geteditTextPhone());
        holder.textViewTime.setText("영업시간 : " + ownerInfo.geteditTexttime());

    }

    @Override
    public int getItemCount() {
        return cafeList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewName, textViewAddress, textViewPhone, textViewTime;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textview_name2);
            textViewAddress = itemView.findViewById(R.id.textview_address2);
            textViewPhone = itemView.findViewById(R.id.textview_phone2);
            textViewTime = itemView.findViewById(R.id.textview_time2);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            OwnerInfo ownerInfo = cafeList.get(getAdapterPosition());
            //Intent intent = new Intent(mCtx, CafeManager2.class);
            //intent.putExtra("product", ownerInfo);
            //mCtx.startActivity(intent);
        }



    }

}
