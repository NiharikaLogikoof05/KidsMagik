package com.logikoof.ecom.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.EmbossMaskFilter;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.databinding.ViewDataBinding;

import com.logikoof.ecom.R;
import com.logikoof.ecom.base.BaseActivity;
import com.logikoof.ecom.databinding.ActivityLoginBinding;

import java.util.regex.Matcher;

/**
 * Created by Niharika on 25-06-2021.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private ActivityLoginBinding binding;
    boolean isUp;

    @Override
    protected void init() {
        layoutId = R.layout.activity_login;
    }

    @Override
    protected void setUpUi(Bundle savedInstanceState, ViewDataBinding viewDataBinding) {
        binding = (ActivityLoginBinding) viewDataBinding;

        binding.edusername.addTextChangedListener(new GenericTextWatcher(binding.edusername));
        binding.edpassword.addTextChangedListener(new GenericTextWatcher(binding.edpassword));

        isUp = false;

        binding.edusername.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // slideUp(binding.llLogin);
                return false;
            }
        });

        binding.laylogin.setOnClickListener(this);
        binding.layskip.setOnClickListener(this);
        binding.edusername.setOnClickListener(this);
        binding.edpassword.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.laylogin:
                 startActivity(new Intent(LoginActivity.this,FragmentActivity.class));
                break;
            case R.id.layskip:
                startActivity(new Intent(LoginActivity.this,FragmentActivity.class));
                break;
           /* case R.id.edusername:
                 binding.layusename.setBackgroundResource(R.drawable.ract_black);
                 binding.laypassword.setBackgroundResource(R.drawable.ract_grey);
                break;
            case R.id.edpassword:
                binding.laypassword.setBackgroundResource(R.drawable.ract_black);
                binding.layusename.setBackgroundResource(R.drawable.ract_grey);
                break;*/
        }
    }
    public class GenericTextWatcher implements TextWatcher
    {
        private View view;

        private GenericTextWatcher(View view)
        {
            this.view = view;
        }
        @Override
        public void afterTextChanged(Editable editable) {
            String text = editable.toString();
            switch(view.getId())
            {
                case R.id.edusername:
                    if(text.length()==1)
                        binding.edusername.requestFocus();
                    binding.layusename.setBackgroundResource(R.drawable.ract_black);
                    binding.laypassword.setBackgroundResource(R.drawable.ract_grey);
                    break;

                case R.id.edpassword:
                    if(text.length()==1)
                        binding.edpassword.requestFocus();
                    binding.laypassword.setBackgroundResource(R.drawable.ract_black);
                    binding.layusename.setBackgroundResource(R.drawable.ract_grey);
                    break;

            }
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            //Toast.makeText(getApplicationContext(),"test",Toast.LENGTH_LONG).show();

        }
        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
        }
    }

    @Override
    public void okDialogClick(int Id) {
    }

    @Override
    public void cancelDialogClick(int Id) {
    }
}
