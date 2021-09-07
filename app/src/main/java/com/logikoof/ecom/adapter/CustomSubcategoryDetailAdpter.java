package com.logikoof.ecom.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.logikoof.ecom.R;

import java.util.List;

/**
 * Created by Niharika on 28-07-2021.
 */
public class CustomSubcategoryDetailAdpter extends RecyclerView.Adapter<CustomSubcategoryDetailAdpter.MyViewHolder> {
    private List<String> subcategorydetaillist;
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
            footer = (RelativeLayout) itemView.findViewById(R.id.layfotter);
            cardViewback = itemView.findViewById(R.id.laydiscount);
            product = (ImageView) itemView.findViewById(R.id.ivproduct);
        }
    }

    public CustomSubcategoryDetailAdpter(Context context,List<String> subcategorydetaillist) {
        this.context=context;
        this.subcategorydetaillist = subcategorydetaillist;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.subcategory_item, parent, false);

        //view.setOnClickListener(MainActivity.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final CustomSubcategoryDetailAdpter.MyViewHolder holder, final int listPosition) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedItemPosition = listPosition;
                notifyDataSetChanged();
            }
        });
        if(selectedItemPosition == listPosition) {
            holder.productname.setTextColor(Color.parseColor("#ffffff"));
            holder.footer.setBackgroundColor(R.drawable.subcategorybottom_yellow);
        }else {
            holder.productname.setTextColor(Color.parseColor("#FF000000"));
            holder.footer.setBackgroundColor(R.drawable.subcatbottom_grey);
        }

        if (listPosition==1) {
            holder.productname.setText("Pilot Pen");
            holder.product.setBackgroundResource(R.drawable.uniball2);

        }
        if (listPosition==2) {
            holder.productname.setText("Jet Pen");
            holder.product.setBackgroundResource(R.drawable.jetpens);

        }
        if (listPosition==2) {
            holder.productname.setText("Fountain Pen");
            holder.product.setBackgroundResource(R.drawable.fountain);

        }
    }
    @Override
    public int getItemCount() {
        return 4;
    }
}
