package com.heavymaverick.toolsshop;

import androidx.annotation.NonNull;

public class Drill {
    private String title;
    private int imageResourceId;
    private String info;

    public Drill(String title, String info, int imageResourceId) {
        this.title = title;
        this.imageResourceId = imageResourceId;
        this.info = info;
    }

    public String getTitle() {
        return title;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String getInfo() {
        return info;
    }

    @NonNull
    @Override
    public String toString() {
        return title;
    }
}
