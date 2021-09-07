package com.logikoof.ecom.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.logikoof.ecom.R;
import com.logikoof.ecom.base.CustomFragmentManager;
import com.logikoof.ecom.fragment.SubCategoryDetailFragment;

import java.util.List;

/**
 * Created by Niharika on 28-07-2021.
 */
public class CustomCategoryDetailAdpter extends RecyclerView.Adapter<CustomCategoryDetailAdpter.MyViewHolder> {
    private List<String> categorydetaillist;
    private Context context;
    private int selectedItemPosition  = 0;
    boolean click=true;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView productname,actualprice,ratting;
        RelativeLayout footer;
        CardView cardViewback;
        ImageView imgheart,product;

        public MyViewHolder(View itemView) {
            super(itemView);
            productname = (TextView) itemView.findViewById(R.id.tvproductname);
            actualprice = (TextView) itemView.findViewById(R.id.tvactualprice);
            ratting = (TextView) itemView.findViewById(R.id.tvratting);
            footer = (RelativeLayout) itemView.findViewById(R.id.layfotter);
            cardViewback = itemView.findViewById(R.id.laydiscount);
            imgheart = (ImageView) itemView.findViewById(R.id.ivheart);
            product = (ImageView) itemView.findViewById(R.id.ivproduct);
        }
    }

    public CustomCategoryDetailAdpter(Context context,List<String> categorydetaillist) {
        this.context=context;
        this.categorydetaillist = categorydetaillist;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_item, parent, false);

        //view.setOnClickListener(MainActivity.myOnClickListener);

        MyViewHolder myViewHolder = new  MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final CustomCategoryDetailAdpter.MyViewHolder holder, final int listPosition) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedItemPosition = listPosition;
                notifyDataSetChanged();
                FragmentManager manager = ((FragmentActivity) v.getContext()).getSupportFragmentManager();
                CustomFragmentManager.replaceFragment(manager, new SubCategoryDetailFragment(), true);
            }
        });
        if(selectedItemPosition == listPosition) {
            holder.productname.setTextColor(Color.parseColor("#ffffff"));
            holder.actualprice.setTextColor(Color.parseColor("#f26b61"));
            holder.ratting.setTextColor(Color.parseColor("#ffffff"));
            holder.footer.setBackgroundColor(Color.parseColor("#fef0ee"));
            holder.cardViewback.setCardBackgroundColor(Color.parseColor("#f5a926"));
        }else {
            holder.productname.setTextColor(Color.parseColor("#FF000000"));
            holder.actualprice.setTextColor(Color.parseColor("#090a0e"));
            holder.ratting.setTextColor(Color.parseColor("#CCC9C9"));
            holder.footer.setBackgroundColor(Color.parseColor("#CCC9C9"));
            holder.cardViewback.setCardBackgroundColor(Color.parseColor("#fbfafa"));
        }

        if (listPosition==1) {
            holder.productname.setText("Uni Ball Pen 8");
            holder.product.setBackgroundResource(R.drawable.uniball);

        }
        if (listPosition==2) {
            holder.productname.setText("Uni Ball Pen 8");
            holder.product.setBackgroundResource(R.drawable.uniball2);

        }
        if (listPosition==2) {
            holder.productname.setText("Uni Ball Pen 8");
            holder.product.setBackgroundResource(R.drawable.uniball3);

        }
        holder.imgheart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (click==true) {
                    holder.imgheart.setBackgroundResource(R.drawable.checked_wishlist);
                    click = false;
                }else {
                    holder.imgheart.setBackgroundResource(R.drawable.uncheked_wishlist);
                    click = true;
                }

            }
        });
    }
    @Override
    public int getItemCount() {
        return 20;
    }
}
