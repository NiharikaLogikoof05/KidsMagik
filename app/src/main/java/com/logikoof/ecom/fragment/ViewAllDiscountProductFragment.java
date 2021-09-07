package com.logikoof.ecom.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.GridLayoutManager;

import com.logikoof.ecom.R;
import com.logikoof.ecom.activity.FragmentActivity;
import com.logikoof.ecom.adapter.CustomDashDiscountAdpter;
import com.logikoof.ecom.base.BaseFragment;
import com.logikoof.ecom.databinding.FragmentProductdetailBinding;
import com.logikoof.ecom.databinding.FragmentViewallproductBinding;
import com.logikoof.ecom.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niharika on 28-07-2021.
 */
public class ViewAllDiscountProductFragment extends BaseFragment implements View.OnClickListener {
    protected Context context;
    private FragmentViewallproductBinding binding;
    CustomDashDiscountAdpter adapter;
    private List<String> discountproductdetail = new ArrayList<>();

    public static ViewAllDiscountProductFragment newInstance(Bundle args) {
        ViewAllDiscountProductFragment fragment = new ViewAllDiscountProductFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init() {
        layoutId = R.layout.fragment_viewallproduct;
    }

    @Override
    protected void setUpUi(View view, ViewDataBinding viewDataBinding) {
        binding = (FragmentViewallproductBinding) viewDataBinding;
        ((FragmentActivity) getActivity()).btn_back.setVisibility(View.GONE);
        ((FragmentActivity) getActivity()).btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });
        adapter=new CustomDashDiscountAdpter(getContext(), (ArrayList<String>) discountproductdetail);
        binding.recyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        //binding.recyclerview.setLayoutManager(horizontalLayoutManagaer);
        binding.recyclerview.setAdapter(adapter);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
    @Override
    public void onBackCustom() {
        getFragmentManager().popBackStack();
    }
    @Override
    public void okDialogClick(int Id) {
        if (Id == AppConstants.DIALOG_LOGIN_BACK_ID) {
            getActivity().finish();
        }
    }
}