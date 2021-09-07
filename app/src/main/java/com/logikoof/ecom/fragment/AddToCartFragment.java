package com.logikoof.ecom.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.logikoof.ecom.R;
import com.logikoof.ecom.activity.FragmentActivity;
import com.logikoof.ecom.adapter.CustomAddToCartAdapter;
import com.logikoof.ecom.adapter.CustomCategoryDetailAdpter;
import com.logikoof.ecom.base.BaseFragment;
import com.logikoof.ecom.base.CustomFragmentManager;
import com.logikoof.ecom.databinding.FragmentAddtocartBinding;
import com.logikoof.ecom.databinding.FragmentCategorydetailsBinding;
import com.logikoof.ecom.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niharika on 02-08-2021.
 */
public class AddToCartFragment extends BaseFragment implements View.OnClickListener {
    protected Context context;
    private FragmentAddtocartBinding binding;
    CustomAddToCartAdapter adapter;
    private List<String> addtocartlist = new ArrayList<>();

    public static AddToCartFragment newInstance(Bundle args) {
        AddToCartFragment fragment = new AddToCartFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init() {
        layoutId = R.layout.fragment_addtocart;
    }

    @Override
    protected void setUpUi(View view, ViewDataBinding viewDataBinding) {
        binding = (FragmentAddtocartBinding) viewDataBinding;
        ((FragmentActivity) getActivity()).btn_back.setVisibility(View.GONE);
        ((FragmentActivity) getActivity()).btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });
        adapter=new CustomAddToCartAdapter(getContext(), (ArrayList<String>) addtocartlist);
        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerview.setLayoutManager(horizontalLayoutManagaer);
        binding.recyclerview.setAdapter(adapter);

        binding.laycheckout.setOnClickListener(this);
        binding.ivback.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.laycheckout:
                CustomFragmentManager.replaceFragment(getFragmentManager(), new CheckoutFragment(),true);
                 break;
            case R.id.ivback:
                getFragmentManager().popBackStack();
                //CustomFragmentManager.replaceFragment(getFragmentManager(), new DashbordFragment(),true);
                break;
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