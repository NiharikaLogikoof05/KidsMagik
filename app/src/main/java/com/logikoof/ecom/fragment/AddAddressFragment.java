package com.logikoof.ecom.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.ViewDataBinding;

import com.logikoof.ecom.R;
import com.logikoof.ecom.adapter.CustomAddToCartAdapter;
import com.logikoof.ecom.base.BaseFragment;
import com.logikoof.ecom.databinding.FragmentAddaddressBinding;
import com.logikoof.ecom.databinding.FragmentAddtocartBinding;
import com.logikoof.ecom.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niharika on 05-08-2021.
 */
public class AddAddressFragment extends BaseFragment implements View.OnClickListener {
    protected Context context;
    private FragmentAddaddressBinding binding;

    public static AddAddressFragment newInstance(Bundle args) {
        AddAddressFragment fragment = new AddAddressFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init() {
        layoutId = R.layout.fragment_addaddress;
    }

    @Override
    protected void setUpUi(View view, ViewDataBinding viewDataBinding) {
        binding = (FragmentAddaddressBinding) viewDataBinding;
        binding.ivback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivback:
                getFragmentManager().popBackStack();
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
