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
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.logikoof.ecom.R;
import com.logikoof.ecom.base.CustomFragmentManager;
import com.logikoof.ecom.fragment.SubCategoryDetailFragment;

import java.util.List;

/**
 * Created by Niharika on 29-07-2021.
 */
public class CustomCategoryAdapter extends RecyclerView.Adapter<CustomCategoryAdapter.MyViewHolder> {
    private List<String> categorydetaillist;
    private Context context;
    private int selectedItemPosition  = 0;
    boolean click=true;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView img;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tvcategoryname);
            img = (ImageView) itemView.findViewById(R.id.ivcategory);
        }
    }

    public CustomCategoryAdapter(Context context,List<String> categorydetaillist) {
        this.context=context;
        this.categorydetaillist = categorydetaillist;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dashcategory_item, parent, false);

        //view.setOnClickListener(MainActivity.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final CustomCategoryAdapter.MyViewHolder holder, final int listPosition) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedItemPosition = listPosition;
                notifyDataSetChanged();
            }
        });
        if (listPosition==0) {
            holder.name.setText("Papers");
            holder.img.setBackgroundResource(R.drawable.doc);

        }
        if (listPosition==1) {
            holder.name.setText("Pens");
            holder.img.setBackgroundResource(R.drawable.pens);

        }
        if (listPosition==2) {
            holder.name.setText("File");
            holder.img.setBackgroundResource(R.drawable.files);

        }
        if (listPosition==3) {
            holder.name.setText("Begs");
            holder.img.setBackgroundResource(R.drawable.bags);

        }
    }
    @Override
    public int getItemCount() {
        return 4;
    }
}
