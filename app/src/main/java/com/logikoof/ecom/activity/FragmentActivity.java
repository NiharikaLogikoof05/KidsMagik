package com.logikoof.ecom.activity;

import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.ViewDataBinding;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;
import com.logikoof.ecom.R;
import com.logikoof.ecom.base.BaseActivity;
import com.logikoof.ecom.base.BaseFragmentInterface;
import com.logikoof.ecom.base.CustomFragmentManager;
import com.logikoof.ecom.databinding.ActivityNavigationBinding;
import com.logikoof.ecom.fragment.AddToCartFragment;
import com.logikoof.ecom.fragment.DashbordFragment;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niharika on 25-06-2021.
 */
public class FragmentActivity extends BaseActivity implements View.OnClickListener {
    private ProgressDialog dialog;
    public Bundle bundle;
    private String printContent="";
    private byte FONT_TYPE;
    private static BluetoothSocket btsocket;
    private static OutputStream btoutputstream;
    ConnectivityManager connMgr;
    NetworkInfo networkInfo;
    String currentVersion="",mActivityTitle,forplace;
    DrawerLayout drawer;
    ImageView menu,addcard,notification;
    RelativeLayout layback,layuser;
    TextView title;
    View headerview;
    public TextView mTitle;
    boolean click=true;
    private BaseFragmentInterface interFace;
    private ActivityNavigationBinding binding;
    private Toolbar toolbar;
    public ImageView btn_back,Menu;
    RelativeLayout profile_lay;
    LinearLayout home,shop,setting,me;
    ImageView imghome,imgshop,imgsetting,imgme,imgcart;
    TextView texthome,textshop,textsetting,textme;

    @Override
    protected void init() {
        layoutId = R.layout.activity_navigation;
    }

    @Override
    protected void setUpUi(Bundle savedInstanceState, ViewDataBinding viewDataBinding) {

        dialog = new ProgressDialog(FragmentActivity.this);
        dialog.setCancelable(false);

        connMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = connMgr.getActiveNetworkInfo();
        mActivityTitle = getTitle().toString();
        toolbar = findViewById(R.id.toolbar);
        CustomFragmentManager.addFragment(getSupportFragmentManager(), new DashbordFragment());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);

         menu = toolbar.findViewById(R.id.ivmenu);
        //n******************

        drawer = findViewById(R.id.drawer_layout);
        drawer.setScrimColor(Color.TRANSPARENT);
        drawer.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
                                           @Override
                                           public void onDrawerSlide(View drawerView, float slideOffset) {
                                              // labelView.setVisibility(slideOffset > 0 ? View.VISIBLE : View.GONE);

                                           }

                                           @Override
                                           public void onDrawerClosed(View drawerView) {
                                              // labelView.setVisibility(View.GONE);
                                           }
                                       }
        );
        View includedheader = findViewById(R.id.header);
        title=findViewById(R.id.tvdash);
        addcard=findViewById(R.id.ivbasket);
        notification=findViewById(R.id.ivnatification);
        btn_back=includedheader.findViewById(R.id.ivback);
        btn_back.setVisibility(View.GONE);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
            }
        });

        //drawer.useCustomBehavior(GravityCompat.START); //assign custom behavior for "Left" drawer
       // drawer.useCustomBehavior(GravityCompat.END); //assign custom behavior for "Right" drawer
        //drawer.setRadius(GravityCompat.START, 25);//set end container's corner radius (dimension)

        home=findViewById(R.id.layhome);
        shop=findViewById(R.id.layshop);
        me=findViewById(R.id.layme);
        setting=findViewById(R.id.laysetting);

        imghome=findViewById(R.id.ivhome);
        imgshop=findViewById(R.id.ivshop);
        imgme=findViewById(R.id.ivme);
        imgsetting=findViewById(R.id.ivsetting);

        texthome=findViewById(R.id.tvhome);
        textshop=findViewById(R.id.tvshop);
        textme=findViewById(R.id.tvme);
        textsetting=findViewById(R.id.tvsetting);
        imgcart=findViewById(R.id.ivbasket);

        home.setOnClickListener(this);
        shop.setOnClickListener(this);
        me.setOnClickListener(this);
        setting.setOnClickListener(this);
        imgcart.setOnClickListener(this);

        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        headerview = navigationView.getHeaderView(0);
        ImageView cancel=navigationView.findViewById(R.id.ivcancel);
        cancel.setOnClickListener(this);

        LinearLayout homenav=navigationView.findViewById(R.id.layhome2);
        homenav.setOnClickListener(this);
        LinearLayout logoutnav=navigationView.findViewById(R.id.laylogout);
        logoutnav.setOnClickListener(this);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                /* safsetup=headerview.findViewById(R.id.text_saf_setup);
                int id = safsetup.getId();
                if (id == R.id.text_saf_setup) {
                   //loadFragment(new Saf_Setup());
                } else if (id == R.id.text_saf) {
                    //loadFragment(new Saf());
                } else if (id == R.id.text_saf_application) {
                   //loadFragment(new Saf_Application());
                } */
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        //mNavigationManager = FragmentNavigationManager.obtain(this);
        try {
            currentVersion = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

//        if(networkInfo!= null) {

//        new GetVersionCode().execute();}
//        else{
//            showDialog(FragmentActivity.this, "Please Check your Internet Connection ", false, true, 0);
//
//        }
/*
Toast.makeText(getApplicationContext(),currentVersion,Toast.LENGTH_LONG).show();
*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel =
                    new NotificationChannel("MyNotifications", "MyNotifications", NotificationManager.IMPORTANCE_DEFAULT);
            //NotificationManager manager = getSystemService(NotificationManager.class);
            //manager.createNotificationChannel(channel);
        }
        // initItems();

        LayoutInflater inflater = getLayoutInflater();
        View listHeaderView = inflater.inflate(R.layout.layout_header, null, false);
        if (savedInstanceState == null) {
            // selectFirstItemAsDefault();
        }
        binding = (ActivityNavigationBinding) viewDataBinding;

        menu=listHeaderView.findViewById(R.id.ivmenu);

    }


    public void setScreenTitle(String title) {
       // mTitle.setText(title);
    }
    public void setHeaderview() {
        getSupportActionBar().hide();
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getVisibleFragment();
        if (fragment != null) interFace = (BaseFragmentInterface) fragment;
        if (interFace != null)
            interFace.onBackCustom();
    }
    protected Fragment getVisibleFragment() {
        FragmentManager fragmentManager = FragmentActivity.this.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                if (fragment != null && fragment.isVisible())
                    return fragment;
            }
        }
        return null;
    }
    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        switch (v.getId()) {
            case R.id.layhome:
                try {
                    getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                } catch (Exception e) {
                    e.getMessage();
                }
                drawer.closeDrawer(GravityCompat.START);
                if (click==true) {
                    binding.tvhome.setTextColor(Color.parseColor("#f5a926"));
                    binding.ivhome.setBackgroundResource(R.drawable.homeactive);

                    binding.tvshop.setTextColor(Color.parseColor("#FFFFFFFF"));
                    binding.ivshop.setBackgroundResource(R.drawable.shop);

                    binding.tvme.setTextColor(Color.parseColor("#FFFFFFFF"));
                    binding.ivme.setBackgroundResource(R.drawable.me);

                    binding.tvsetting.setTextColor(Color.parseColor("#FFFFFFFF"));
                    binding.ivsetting.setBackgroundResource(R.drawable.settingsactivee);
                }

                CustomFragmentManager.replaceFragment(getSupportFragmentManager(), new DashbordFragment(),true);
                break;
            case R.id.ivbasket:
                CustomFragmentManager.replaceFragment(getSupportFragmentManager(), new AddToCartFragment(),true);
                break;
            case R.id.layshop:
                if (click==true) {
                    binding.tvhome.setTextColor(Color.parseColor("#FFFFFFFF"));
                    binding.ivhome.setBackgroundResource(R.drawable.home1);

                    binding.tvshop.setTextColor(Color.parseColor("#f5a926"));
                    binding.ivshop.setBackgroundResource(R.drawable.shop_active);

                    binding.tvme.setTextColor(Color.parseColor("#FFFFFFFF"));
                    binding.ivme.setBackgroundResource(R.drawable.me);

                    binding.tvsetting.setTextColor(Color.parseColor("#FFFFFFFF"));
                    binding.ivsetting.setBackgroundResource(R.drawable.settingsactivee);
                }
                break;
            case R.id.layme:
                if (click==true) {
                    binding.tvhome.setTextColor(Color.parseColor("#FFFFFFFF"));
                    binding.ivhome.setBackgroundResource(R.drawable.home1);

                    binding.tvshop.setTextColor(Color.parseColor("#FFFFFFFF"));
                    binding.ivshop.setBackgroundResource(R.drawable.shop);

                    binding.tvme.setTextColor(Color.parseColor("#f5a926"));
                    binding.ivme.setBackgroundResource(R.drawable.user_active);

                    binding.tvsetting.setTextColor(Color.parseColor("#FFFFFFFF"));
                    binding.ivsetting.setBackgroundResource(R.drawable.settingsactivee);
                }
                break;
            case R.id.laysetting:
                if (click==true) {
                    binding.tvhome.setTextColor(Color.parseColor("#FFFFFFFF"));
                    binding.ivhome.setBackgroundResource(R.drawable.home1);

                    binding.tvshop.setTextColor(Color.parseColor("#FFFFFFFF"));
                    binding.ivshop.setBackgroundResource(R.drawable.shop);

                    binding.tvme.setTextColor(Color.parseColor("#FFFFFFFF"));
                    binding.ivme.setBackgroundResource(R.drawable.me);

                    binding.tvsetting.setTextColor(Color.parseColor("#f5a926"));
                    binding.ivsetting.setBackgroundResource(R.drawable.settings_active);
                }
                break;
            case R.id.ivcancel:
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.layhome2:
                CustomFragmentManager.replaceFragment(getSupportFragmentManager(), new DashbordFragment(),true);
                break;
            case R.id.laylogout:
                 AlertPopUp();
                break;
        }
    }
    //AlertPopUp
    private void AlertPopUp() {
        final Dialog dialog = new Dialog(this,android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.alert_logout);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
        window.setAttributes(wlp);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.show();

        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);

        dialog.show();
        TextView okButton = dialog.findViewById(R.id.btn_ok);
        TextView cancelButton = dialog.findViewById(R.id.btn_cancel);

        okButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),"Successfully Added",Toast.LENGTH_SHORT).show();
                onResume();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),"Successfully Added",Toast.LENGTH_SHORT).show();
                dialog.dismiss();

                onResume();
            }
        });
    }

    @Override
    public void okDialogClick(int Id) {
    }

    @Override
    public void cancelDialogClick(int Id) {
    }
    //    @Override
/*
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
*/
    private void showDialog(String message) {
        dialog.setMessage(message);
        dialog.show();
    }

    private void closeDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
    public void showForceUpdateDialog(){
        Uri uri = Uri.parse("market://details?id=" + getApplication().getPackageName()); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        FragmentActivity.this.startActivity(intent);

    }
}
