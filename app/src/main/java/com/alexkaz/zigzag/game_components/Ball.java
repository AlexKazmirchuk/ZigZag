package com.alexkaz.zigzag.game_components;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Ball {

    private boolean direction = false;
    private int x;
    private int y;
    private int width;
    private int height;
    private Paint paint;

    public Ball() {

    }

    public void draw(Canvas canvas){

    }

    public void turnLeft(){
        direction = false;
    }

    public void turnRight(){
        direction = true;
    }
}
