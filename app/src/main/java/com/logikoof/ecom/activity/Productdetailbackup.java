package com.logikoof.ecom.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.logikoof.ecom.R;
import com.logikoof.ecom.adapter.CustomProductdetailAdpter;
import com.logikoof.ecom.base.BaseFragment;
import com.logikoof.ecom.database.PrefManger;
import com.logikoof.ecom.databinding.FragmentProductdetailBinding;
import com.logikoof.ecom.fragment.ProductDetailFragment;
import com.logikoof.ecom.utils.AppConstants;
import com.mingle.sweetpick.CustomDelegate;
import com.mingle.sweetpick.SweetSheet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niharika on 06-08-2021.
 */
public class Productdetailbackup extends BaseFragment implements View.OnClickListener {
    protected Context context;
    private FragmentProductdetailBinding binding;
    CustomProductdetailAdpter adapter;
    private List<String> categorydetaillist = new ArrayList<>();
    private PrefManger prefManager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private TextView[] dots;
    private int[] layouts;
    private SweetSheet mSweetSheet;
    boolean click=true;
    public final static int LOOPS = 1;
    public CarouselPagerAdapter adapter1;
    public ViewPager pager;
    public static int count = 3; //ViewPager items size
    /**
     * You shouldn't define first page = 0.
     * Let define firstpage = 'number viewpager size' to make endless carousel
     */
    public static int FIRST_PAGE = 1;

    public static Productdetailbackup newInstance(Bundle args) {
        Productdetailbackup fragment = new Productdetailbackup();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init() {
        layoutId = R.layout.fragment_productdetail;
    }

    @Override
    protected void setUpUi(View view, ViewDataBinding viewDataBinding) {
        binding = (FragmentProductdetailBinding) viewDataBinding;
        ((FragmentActivity) getActivity()).btn_back.setVisibility(View.GONE);
        ((FragmentActivity) getActivity()).btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });
        // Checking for first time launch - before calling setContentView()
        prefManager = new PrefManger(getActivity());
        if (!prefManager.isFirstTimeLaunch()) {
        }

        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        // add few more layouts if you want
        layouts = new int[]{
                R.layout.productscreen1,
                R.layout.productscreen2,
                R.layout.productscreen3};

        // adding bottom dots
        addBottomDots(0);

        // making notification bar transparent
        changeStatusBarColor();

        myViewPagerAdapter = new MyViewPagerAdapter();
        binding.viewPager.setAdapter(myViewPagerAdapter);
        binding.viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        adapter = new CustomProductdetailAdpter(getContext(), (ArrayList<String>) categorydetaillist);
        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerview.setLayoutManager(horizontalLayoutManagaer);
        binding.recyclerview.setAdapter(adapter);

        binding.ivdetailinfo.setOnClickListener(this);
        binding.ivtermspolicy.setOnClickListener(this);
        binding.ivusercomment.setOnClickListener(this);
        binding.layaddtocart.setOnClickListener(this);
        binding.ivheart.setOnClickListener(this);

        //pager settings
        binding.viewPager.setClipToPadding(false);
        binding.viewPager.setPageMargin(24);
        binding.viewPager.setPadding(48, 8, 48, 8);
        binding.viewPager.setOffscreenPageLimit(3);

        binding.viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.i("", "onPageScrolled: " + position);
                //CampaignPagerFragment sampleFragment = (CampaignPagerFragment) ((CampaignPagerAdapter) pager.getAdapter()).getRegisteredFragment(position);
                // float scale = 1 - (positionOffset * RATIO_SCALE);
                // Just a shortcut to findViewById(R.id.image).setScale(scale);
                //sampleFragment.scaleImage(scale);
                if (position + 1 < binding.viewPager.getAdapter().getCount()) {
                    /*sampleFragment = (CampaignPagerFragment) ((CampaignPagerAdapter) pager.getAdapter()).getRegisteredFragment(position + 1);
                    scale = positionOffset * RATIO_SCALE + (1 - RATIO_SCALE);
                    sampleFragment.scaleImage(scale);*/
                }
            }
            @Override
            public void onPageSelected(int position) {
                Log.i("", "onPageSelected: " + position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
                Log.i("", "onPageScrollStateChanged: " + state);
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    /*CampaignPagerFragment fragment = (CampaignPagerFragment) ((CampaignPagerAdapter) pager.getAdapter()).getRegisteredFragment(pager.getCurrentItem());
                    fragment.scaleImage(1);
                    if (pager.getCurrentItem() > 0) {
                        fragment = (CampaignPagerFragment) ((CampaignPagerAdapter) pager.getAdapter()).getRegisteredFragment(pager.getCurrentItem() - 1);
                        fragment.scaleImage(1 - RATIO_SCALE);
                    }

                    if (pager.getCurrentItem() + 1 < pager.getAdapter().getCount()) {
                        fragment = (CampaignPagerFragment) ((CampaignPagerAdapter) pager.getAdapter()).getRegisteredFragment(pager.getCurrentItem() + 1);
                        fragment.scaleImage(1 - RATIO_SCALE);
                    }*/
                }
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivdetailinfo:
                binding.ivdetailinfo.setBackgroundResource(R.drawable.up_arrow);
                binding.laydetailinformation.setVisibility(View.VISIBLE);

                binding.ivtermspolicy.setBackgroundResource(R.drawable.dropdown1);
                binding.laytermpolicy.setVisibility(View.GONE);

                binding.ivusercomment.setBackgroundResource(R.drawable.dropdown1);
                binding.layusercomments.setVisibility(View.GONE);

                break;
            case R.id.ivtermspolicy:
                binding.ivtermspolicy.setBackgroundResource(R.drawable.up_arrow);
                binding.laytermpolicy.setVisibility(View.VISIBLE);

                binding.ivdetailinfo.setBackgroundResource(R.drawable.dropdown1);
                binding.laydetailinformation.setVisibility(View.GONE);

                binding.ivusercomment.setBackgroundResource(R.drawable.dropdown1);
                binding.layusercomments.setVisibility(View.GONE);

                break;
            case R.id.ivusercomment:
                binding.ivusercomment.setBackgroundResource(R.drawable.up_arrow);
                binding.layusercomments.setVisibility(View.VISIBLE);

                binding.ivtermspolicy.setBackgroundResource(R.drawable.dropdown1);
                binding.laytermpolicy.setVisibility(View.GONE);

                binding.ivdetailinfo.setBackgroundResource(R.drawable.dropdown1);
                binding.laydetailinformation.setVisibility(View.GONE);
                break;
            case R.id.layaddtocart:
                setupCustomView();
                mSweetSheet.toggle();
                break;
            case R.id.ivheart:
                if (click==true) {
                    binding.ivheart.setBackgroundResource(R.drawable.checked_wishlist);
                    click = false;
                }else {
                    binding.ivheart.setBackgroundResource(R.drawable.uncheked_wishlist);
                    click = true;
                }
                break;
        }
    }
    private void setupCustomView() {
        mSweetSheet = new SweetSheet(binding.lay);
        CustomDelegate customDelegate = new CustomDelegate(true,
                CustomDelegate.AnimationType.DuangLayoutAnimation);

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.addtocard, null, false);
        customDelegate.setCustomView(view);
        mSweetSheet.setDelegate(customDelegate);

       /* view.findViewById(R.id.layminus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSweetSheet.dismiss();
                Toast.makeText(getContext(),"niha",Toast.LENGTH_LONG).show();
            }
        });*/
    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);


        binding.layoutDots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(getActivity());
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(45);
            dots[i].setTextColor(colorsInactive[currentPage]);
            binding.layoutDots.addView(dots[i]);

        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    private int getItem(int i) {
        return binding.viewPager.getCurrentItem() + i;
    }


    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);
            //dots = new TextView[layouts.length];

            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == layouts.length - 1) {
                // last page. make button text to GOT IT


            } else {
                // still pages are left

            }


        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * View pager adapter
     */
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
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