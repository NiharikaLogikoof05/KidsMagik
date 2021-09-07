package com.logikoof.ecom.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.databinding.ViewDataBinding;

import com.logikoof.ecom.R;
import com.logikoof.ecom.adapter.CustomProductdetailAdpter;
import com.logikoof.ecom.base.BaseFragment;
import com.logikoof.ecom.base.CustomFragmentManager;
import com.logikoof.ecom.database.PrefManger;
import com.logikoof.ecom.databinding.FragmentProductdetailBinding;
import com.logikoof.ecom.databinding.FragmentSuccessfullyBinding;
import com.logikoof.ecom.utils.AppConstants;
import com.mingle.sweetpick.SweetSheet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niharika on 05-08-2021.
 */
public class SuccessfullyFragment extends BaseFragment implements View.OnClickListener {
    protected Context context;
    private FragmentSuccessfullyBinding binding;


    public static SuccessfullyFragment newInstance(Bundle args) {
        SuccessfullyFragment fragment = new SuccessfullyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init() {
        layoutId = R.layout.fragment_successfully;
    }

    @Override
    protected void setUpUi(View view, ViewDataBinding viewDataBinding) {
        binding = (FragmentSuccessfullyBinding) viewDataBinding;
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