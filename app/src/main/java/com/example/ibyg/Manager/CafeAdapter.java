package com.example.ibyg.Manager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ibyg.R;

import java.util.List;

//어뎁터(recyclerview)
public class CafeAdapter extends RecyclerView.Adapter<CafeAdapter.ProductViewHolder> {

    private Context mCtx;
    private List<OwnerInfo> cafeList;

    public CafeAdapter(Context mCtx, List<OwnerInfo> productList) {
        this.mCtx = mCtx;
        this.cafeList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductViewHolder(
                LayoutInflater.from(mCtx).inflate(R.layout.layout_cafe, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        OwnerInfo ownerInfo = cafeList.get(position);

        holder.textViewName.setText("카페명 : " + ownerInfo.geteditTextName());
        holder.textViewAddress.setText("주소 : " + ownerInfo.geteditTextAddress());
        holder.textViewPhone.setText("전화번호 : " + ownerInfo.geteditTextPhone());
        holder.textViewTime.setText("영업시간 : " + ownerInfo.geteditTexttime());
        holder.textViewWifi.setText("와이파이 : " + ownerInfo.geteditTextwifi() + "개");
        holder.textViewSeat.setText("좌석 수 : " + ownerInfo.geteditTextseat() + "개");
        holder.textViewConsent.setText("콘센트 : " + ownerInfo.geteditTextconsent() + "개");
        holder.textViewPrice.setText("최소가격 : " + ownerInfo.geteditTextprice() + "원");
    }

    @Override
    public int getItemCount() {
        return cafeList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewName, textViewAddress, textViewPhone, textViewTime, textViewWifi, textViewSeat, textViewConsent, textViewPrice;
        //Button update;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textview_name);
            textViewAddress = itemView.findViewById(R.id.textview_address);
            textViewPhone = itemView.findViewById(R.id.textview_phone);
            textViewTime = itemView.findViewById(R.id.textview_time);
            textViewWifi = itemView.findViewById(R.id.textview_wifi);
            textViewSeat = itemView.findViewById(R.id.textview_seat);
            textViewConsent = itemView.findViewById(R.id.textview_consent);
            textViewPrice = itemView.findViewById(R.id.textview_price);
//            update = itemView.findViewById(R.id.button_update2);
//            update.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    OwnerInfo ownerInfo = cafeList.get(getAdapterPosition());
//                    Intent intent = new Intent(mCtx, CafeManager2.class);
//                    intent.putExtra("product", ownerInfo);
//                    mCtx.startActivity(intent);
//
//                }
//            });

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
