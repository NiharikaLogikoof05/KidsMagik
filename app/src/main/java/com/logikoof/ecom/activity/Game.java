package com.logikoof.ecom.activity;

/**
 * Created by Niharika on 31-07-2021.
 */
public class Game {
    private String name;
    private int imageSource;

    public Game (int imageSource, String name) {
        this.name = name;
        this.imageSource = imageSource;
    }

    public String getName() {
        return name;
    }

    public int getImageSource() {
        return imageSource;
    }
}
