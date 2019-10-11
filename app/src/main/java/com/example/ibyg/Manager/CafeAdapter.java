package com.example.ibyg.Manager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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

        Glide.with(mCtx).load(ownerInfo.getPhotoUrl()).into(holder.image);
        holder.text_1.setText("카페명");
        holder.text_2.setText("주소");
        holder.text_3.setText("전화번호");
        holder.text_4.setText("영업시간");
        holder.text_5.setText("와이파이");
        holder.text_6.setText("좌석수");
        holder.text_7.setText("콘센트");
        holder.text_8.setText("최소가격");
        holder.textViewName.setText(ownerInfo.geteditTextName());
        holder.textViewAddress.setText(ownerInfo.geteditTextAddress());
        holder.textViewPhone.setText(ownerInfo.geteditTextPhone());
        holder.textViewTime.setText(ownerInfo.geteditTexttime());
        holder.textViewWifi.setText(ownerInfo.geteditTextwifi() + "개");
        holder.textViewSeat.setText(ownerInfo.geteditTextseat() + "개");
        holder.textViewConsent.setText(ownerInfo.geteditTextconsent() + "개");
        holder.textViewPrice.setText(ownerInfo.geteditTextprice() + "원");

    }

    @Override
    public int getItemCount() {
        return cafeList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewName, textViewAddress, textViewPhone, textViewTime, textViewWifi, textViewSeat, textViewConsent, textViewPrice;
        TextView text_1, text_2, text_3, text_4, text_5, text_6, text_7, text_8;
        ImageView image;
        //Button update;

        public ProductViewHolder(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.imageView);
            text_1 = itemView.findViewById(R.id.text_1);
            text_2 = itemView.findViewById(R.id.text_2);
            text_3 = itemView.findViewById(R.id.text_3);
            text_4 = itemView.findViewById(R.id.text_4);
            text_5 = itemView.findViewById(R.id.text_5);
            text_6 = itemView.findViewById(R.id.text_6);
            text_7 = itemView.findViewById(R.id.text_7);
            text_8 = itemView.findViewById(R.id.text_8);
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
