package com.logikoof.ecom.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.logikoof.ecom.R;
import com.logikoof.ecom.activity.FragmentActivity;
import com.logikoof.ecom.adapter.CustomAddToCartAdapter;
import com.logikoof.ecom.base.BaseFragment;
import com.logikoof.ecom.base.CustomFragmentManager;
import com.logikoof.ecom.databinding.FragmentAddtocartBinding;
import com.logikoof.ecom.databinding.FragmentCheckoutBinding;
import com.logikoof.ecom.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niharika on 03-08-2021.
 */
public class CheckoutFragment extends BaseFragment implements View.OnClickListener {
    protected Context context;
    private FragmentCheckoutBinding binding;
    CustomAddToCartAdapter adapter;
    private List<String> addtocartlist = new ArrayList<>();

    public static CheckoutFragment newInstance(Bundle args) {
        CheckoutFragment fragment = new CheckoutFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init() {
        layoutId = R.layout.fragment_checkout;
    }

    @Override
    protected void setUpUi(View view, ViewDataBinding viewDataBinding) {
        binding = (FragmentCheckoutBinding) viewDataBinding;
        ((FragmentActivity) getActivity()).btn_back.setVisibility(View.GONE);
        ((FragmentActivity) getActivity()).btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });
        binding.layproceedtopayment.setOnClickListener(this);
        binding.ivback.setOnClickListener(this);
        binding.layaddaddress.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layproceedtopayment:
                CustomFragmentManager.replaceFragment(getFragmentManager(), new PaymentModeFragment(),true);
                break;
            case R.id.ivback:
                getFragmentManager().popBackStack();
                break;
            case R.id.layaddaddress:
                CustomFragmentManager.replaceFragment(getFragmentManager(), new AddAddressFragment(),true);
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