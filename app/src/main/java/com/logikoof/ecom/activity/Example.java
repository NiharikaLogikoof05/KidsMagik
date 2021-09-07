package com.logikoof.ecom.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.logikoof.ecom.R;

import java.util.ArrayList;

import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

/**
 * Created by Niharika on 30-07-2021.
 */
public class Example extends AppCompatActivity {
    private FeatureCoverFlow coverFlow;
    private CoverFlowAdapter adapter;
    private ArrayList<Game> games;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        coverFlow = (FeatureCoverFlow) findViewById(R.id.coverflow);

        settingDummyData();
        adapter = new CoverFlowAdapter(this, games);
        coverFlow.setAdapter(adapter);
        //coverFlow.setOnScrollPositionListener(onScrollListener());
    }

/*
    private FeatureCoverFlow.OnScrollPositionListener onScrollListener() {
        return new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                Log.v("MainActiivty", "position: " + position);
            }

            @Override
            public void onScrolling() {
                Log.i("MainActivity", "scrolling");
            }
        };
    }
*/

    private void settingDummyData() {
        games = new ArrayList<>();
        games.add(new Game(R.drawable.uniball, "Assassin Creed 3"));
        games.add(new Game(R.drawable.uniball2, "Avatar 3D"));
        games.add(new Game(R.drawable.uniball3, "Call Of Duty Black Ops 3"));
       /* games.add(new Game(R.mipmap.dota_2, "DotA 2"));
        games.add(new Game(R.mipmap.halo_5, "Halo 5"));
        games.add(new Game(R.mipmap.left_4_dead_2, "Left 4 Dead 2"));
        games.add(new Game(R.mipmap.starcraft, "StarCraft"));
        games.add(new Game(R.mipmap.the_witcher_3, "The Witcher 3"));
        games.add(new Game(R.mipmap.tomb_raider, "Tom raider 3"));
        games.add(new Game(R.mipmap.need_for_speed_most_wanted, "Need for Speed Most Wanted"));*/
    }
}