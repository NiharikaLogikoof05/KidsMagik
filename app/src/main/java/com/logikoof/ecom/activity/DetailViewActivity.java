package com.logikoof.ecom.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.logikoof.ecom.R;

/**
 * Created by Niharika on 30-07-2021.
 */
public class DetailViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.detail_view);
        findViewById(R.id.exit).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
}
