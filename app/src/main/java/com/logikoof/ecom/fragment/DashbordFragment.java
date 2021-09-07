package com.logikoof.ecom.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.SweepGradient;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.material.tabs.TabLayout;
import com.logikoof.ecom.R;
import com.logikoof.ecom.activity.FragmentActivity;
import com.logikoof.ecom.adapter.CustomCategoryAdapter;
import com.logikoof.ecom.adapter.CustomDashDiscountAdpter;
import com.logikoof.ecom.adapter.CustomLatestProductAdpter;
import com.logikoof.ecom.base.BaseFragment;
import com.logikoof.ecom.base.CustomFragmentManager;
import com.logikoof.ecom.databinding.ActivityDashboardBinding;
import com.logikoof.ecom.utils.AppConstants;
import com.mingle.sweetpick.CustomDelegate;
import com.mingle.sweetpick.SweetSheet;

import java.util.ArrayList;
import java.util.List;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Niharika Gupta on 07-11-2020.
 */
public class DashbordFragment extends BaseFragment implements View.OnClickListener {
    protected Context context;
    private ActivityDashboardBinding binding;
    CustomDashDiscountAdpter adapter;
    private List<String> discountlist = new ArrayList<>();
    CustomLatestProductAdpter adapter2;
    private List<String> latestproductlist = new ArrayList<>();
    CustomCategoryAdapter adapter1;
    private List<String> categorylist = new ArrayList<>();
    private SweetSheet mSweetSheet;

    public static DashbordFragment newInstance(Bundle args) {
        DashbordFragment fragment = new DashbordFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init() {
        layoutId = R.layout.activity_dashboard;
    }

    @Override
    protected void setUpUi(View view, ViewDataBinding viewDataBinding) {
        binding = (ActivityDashboardBinding) viewDataBinding;
        ((FragmentActivity) getActivity()).btn_back.setVisibility(View.GONE);

        adapter1 = new CustomCategoryAdapter(getContext(), (ArrayList<String>) categorylist);
        LinearLayoutManager horizontalLayoutManagaer1
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerview1.setLayoutManager(horizontalLayoutManagaer1);
        binding.recyclerview1.setAdapter(adapter1);

        adapter = new CustomDashDiscountAdpter(getContext(), (ArrayList<String>) discountlist);
        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerview.setLayoutManager(horizontalLayoutManagaer);
        binding.recyclerview.setAdapter(adapter);

        adapter2 = new CustomLatestProductAdpter(getContext(), (ArrayList<String>) latestproductlist);
        LinearLayoutManager horizontalLayoutManagaer2
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerview2.setLayoutManager(horizontalLayoutManagaer2);
        binding.recyclerview2.setAdapter(adapter2);

        int id = binding.searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        TextView textView = (TextView) binding.searchView.findViewById(id);
        binding.searchView.setBackgroundColor(Color.TRANSPARENT);
        textView.setTextColor(Color.parseColor("#f5a926"));
        textView.setHintTextColor(Color.parseColor("#f5a926"));
        textView.setTextSize(12);

        binding.layviewall.setOnClickListener(this);
        binding.layviewalllatestproduct.setOnClickListener(this);
        binding.layviewalldisproduct.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layviewall:
                CustomFragmentManager.replaceFragment(getFragmentManager(), new CategoryDetailFragment(), true);
                break;
            case R.id.layviewalllatestproduct:
                CustomFragmentManager.replaceFragment(getFragmentManager(), new ViewAllLatestProductFragment(), true);
                break;
            case R.id.layviewalldisproduct:
                CustomFragmentManager.replaceFragment(getFragmentManager(), new ViewAllDiscountProductFragment(), true);
                break;
        }
    }
    @Override
    public void onBackCustom() {
        showDialog(getActivity(), getString(R.string.exit), true, true, AppConstants.DIALOG_LOGIN_BACK_ID);
    }

    @Override
    public void okDialogClick(int Id) {
        if (Id == AppConstants.DIALOG_LOGIN_BACK_ID) {
            getActivity().finish();
        }
    }
}