package com.logikoof.ecom.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.logikoof.ecom.R;

/**
 * Created by Niharika on 31-07-2021.
 */
public class ImageDetailsActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button button;
    private static final String DRAWABLE_RESOURE = "resource";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_full_image);

        imageView = (ImageView)findViewById(R.id.img);
        button = (Button)findViewById(R.id.btnClose);

        int drawbleResource = getIntent().getIntExtra(DRAWABLE_RESOURE, 0);
        imageView.setImageResource(drawbleResource);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}