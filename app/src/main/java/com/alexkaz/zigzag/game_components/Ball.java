package com.alexkaz.zigzag.game_components;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Ball {

    private boolean direction = false;
    private int x;
    private int y;
    private int width;
    private int height;
    private int radius;
    private Paint paint;

    public Ball() {
        x = 240;
        y = 700;
        width = 40;
        height = 40;
        radius = 10;
        paint = new Paint();
        paint.setColor(Color.RED);
    }

    public void draw(Canvas canvas){
        canvas.drawCircle(x,y,radius,paint);
        move();
    }

    private void move(){
        if (direction){
            //move to right
            x = x + 4;
        } else {
            //move to left
            x = x - 4;
        }
    }

    public void changeDirection(){
        direction = !direction;
    }
}
