package com.logikoof.ecom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.logikoof.ecom.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niharika on 02-08-2021.
 */
public class CustomAddToCartAdapter extends RecyclerView.Adapter<CustomAddToCartAdapter.MyViewHolder> {
    private List<String> categorydetaillist;
    private Context context;
    private int selectedItemPosition  = 0;
    boolean click=true;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView img;
        Spinner number;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tvcategoryname);
            img = (ImageView) itemView.findViewById(R.id.ivcategory);
            number=itemView.findViewById(R.id.spcount);


        }
    }

    public CustomAddToCartAdapter(Context context,List<String> categorydetaillist) {
        this.context=context;
        this.categorydetaillist = categorydetaillist;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.addtocart_item, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final CustomAddToCartAdapter.MyViewHolder holder, final int listPosition) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedItemPosition = listPosition;
                notifyDataSetChanged();
            }
        });
        List<String> countlist= new ArrayList<String>();
        countlist.add("1");
        countlist.add("2");
        countlist.add("3");
        countlist.add("4");
        countlist.add("5");
        countlist.add("6");
        countlist.add("7");
        countlist.add("8");
        countlist.add("9");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, R.layout.spinnertextfont, countlist);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        // attaching data adapter to spinner
        holder.number.setAdapter(dataAdapter);

    }
    @Override
    public int getItemCount() {
        return 4;
    }
}
