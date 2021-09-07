package com.logikoof.ecom.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.logikoof.ecom.R;
import com.logikoof.ecom.base.CustomFragmentManager;
import com.logikoof.ecom.fragment.ProductDetailFragment;
import com.logikoof.ecom.fragment.SubCategoryDetailFragment;

import java.util.List;

/**
 * Created by Niharika on 27-07-2021.
 */
public class CustomLatestProductAdpter extends RecyclerView.Adapter<CustomLatestProductAdpter.MyViewHolder> {
    private List<String> latestproductlist;
    private Context context;
    private int selectedItemPosition  = 0;
    boolean click=true;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView productname,discount,actualprice,ratting;
        LinearLayout footer;
        CardView cardViewback;
        ImageView imgheart,product;
        boolean click=true;

        public MyViewHolder(View itemView) {
            super(itemView);
            productname = (TextView) itemView.findViewById(R.id.tvproductname);
            discount = (TextView) itemView.findViewById(R.id.tvdiscountprice);
            actualprice = (TextView) itemView.findViewById(R.id.tvactualprice);
            ratting = (TextView) itemView.findViewById(R.id.tvratting);
            footer = (LinearLayout) itemView.findViewById(R.id.layfotter);
            cardViewback = itemView.findViewById(R.id.laydiscount);
            imgheart = (ImageView) itemView.findViewById(R.id.ivheart);
            product=(ImageView)itemView.findViewById(R.id.ivproduct);
        }
    }

    public CustomLatestProductAdpter(Context context,List<String> latestproductlist) {
        this.context=context;
        this.latestproductlist = latestproductlist;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                    int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.latestproduct_item, parent, false);

        //view.setOnClickListener(MainActivity.myOnClickListener);
        MyViewHolder myViewHolder = new  MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final CustomLatestProductAdpter.MyViewHolder holder, final int listPosition) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedItemPosition = listPosition;
                notifyDataSetChanged();
                FragmentManager manager = ((FragmentActivity) v.getContext()).getSupportFragmentManager();
                CustomFragmentManager.replaceFragment(manager, new ProductDetailFragment(), true);
            }
        });
        if(selectedItemPosition == listPosition) {
            holder.productname.setTextColor(Color.parseColor("#ffffff"));
            holder.discount.setTextColor(Color.parseColor("#f5a926"));
            holder.actualprice.setTextColor(Color.parseColor("#f26b61"));
            holder.ratting.setTextColor(Color.parseColor("#ffffff"));
            holder.footer.setBackgroundColor(Color.parseColor("#fef0ee"));
            holder.cardViewback.setCardBackgroundColor(Color.parseColor("#f5a926"));
            holder.discount.setPaintFlags(holder.discount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }else {
            holder.productname.setTextColor(Color.parseColor("#FF000000"));
            holder.discount.setTextColor(Color.parseColor("#ffffff"));
            holder.actualprice.setTextColor(Color.parseColor("#090a0e"));
            holder.ratting.setTextColor(Color.parseColor("#CCC9C9"));
            holder.footer.setBackgroundColor(Color.parseColor("#CCC9C9"));
            holder.cardViewback.setCardBackgroundColor(Color.parseColor("#fbfafa"));
            holder.discount.setPaintFlags(holder.discount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        if (listPosition==1) {
            holder.productname.setText("Bacgpack 2");
            holder.product.setBackgroundResource(R.drawable.backpack);

        }
        if (listPosition==2) {
            holder.productname.setText("Legal Papers");
            holder.product.setBackgroundResource(R.drawable.legalpaper);

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
        return 10;
    }

}