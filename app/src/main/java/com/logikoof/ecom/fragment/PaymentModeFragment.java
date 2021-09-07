package com.logikoof.ecom.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import androidx.databinding.ViewDataBinding;

import com.logikoof.ecom.R;
import com.logikoof.ecom.activity.FragmentActivity;
import com.logikoof.ecom.adapter.CustomAddToCartAdapter;
import com.logikoof.ecom.base.BaseFragment;
import com.logikoof.ecom.base.CustomFragmentManager;
import com.logikoof.ecom.databinding.FragmentCheckoutBinding;
import com.logikoof.ecom.databinding.FragmentPaymentmodeBinding;
import com.logikoof.ecom.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niharika on 04-08-2021.
 */
public class PaymentModeFragment extends BaseFragment implements View.OnClickListener {
    protected Context context;
    private FragmentPaymentmodeBinding binding;
    CustomAddToCartAdapter adapter;
    private List<String> addtocartlist = new ArrayList<>();

    public static PaymentModeFragment newInstance(Bundle args) {
        PaymentModeFragment fragment = new PaymentModeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init() {
        layoutId = R.layout.fragment_paymentmode;
    }

    @Override
    protected void setUpUi(View view, ViewDataBinding viewDataBinding) {
        binding = (FragmentPaymentmodeBinding) viewDataBinding;
        ((FragmentActivity) getActivity()).btn_back.setVisibility(View.GONE);
        ((FragmentActivity) getActivity()).btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });
        binding.layproceedtopayment.setOnClickListener(this);
        binding.ivback.setOnClickListener(this);
        binding.laycard.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layproceedtopayment:
                CustomFragmentManager.replaceFragment(getFragmentManager(), new SuccessfullyFragment(),true);
                break;
            case R.id.ivback:
                getFragmentManager().popBackStack();
                break;
            case R.id.laycard:
                Alertcarddetail();
                break;
        }
    }
    //AlertPopUp
    private void Alertcarddetail() {
        final Dialog dialog = new Dialog(getActivity(),android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.alert_carddetail);
        Window window = dialog.getWindow();
        RelativeLayout button=window.findViewById(R.id.laydone);
        Spinner month=window.findViewById(R.id.spmonth);
        Spinner year=window.findViewById(R.id.spyear);

        List<String> monthlist= new ArrayList<String>();
        monthlist.add("1");
        monthlist.add("2");
        monthlist.add("3");
        monthlist.add("4");
        monthlist.add("5");
        monthlist.add("6");
        monthlist.add("7");
        monthlist.add("8");
        monthlist.add("9");
        monthlist.add("10");
        monthlist.add("11");
        monthlist.add("12");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinnertextfontleft, monthlist);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown2);
        // attaching data adapter to spinner
        month.setAdapter(dataAdapter);

        List<String> yearlist= new ArrayList<String>();
        yearlist.add("2011");
        yearlist.add("2012");
        yearlist.add("2013");
        yearlist.add("2014");
        yearlist.add("2015");
        yearlist.add("2016");
        yearlist.add("2017");
        yearlist.add("2018");
        yearlist.add("2019");
        yearlist.add("2010");
        yearlist.add("2011");
        yearlist.add("2012");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getContext(), R.layout.spinnertextfontleft, yearlist);
        // Drop down layout style - list view with radio button
        dataAdapter2.setDropDownViewResource(R.layout.spinner_dropdown2);
        // attaching data adapter to spinner
        year.setAdapter(dataAdapter2);

        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
        window.setAttributes(wlp);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.show();

        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               dialog.dismiss();
            }
        });

        dialog.show();
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