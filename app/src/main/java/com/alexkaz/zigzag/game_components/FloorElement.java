package com.alexkaz.zigzag.game_components;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public class FloorElement {

    private int x;
    private int y;
    private int width;
    private int height;

    private Path topSidePath;
    private Path leftSidePath;
    private Path rightSidePath;

    private Paint paint;

    private int topColor;
    private int leftColor;
    private int rightColor;

    public FloorElement(int x, int y) {
        this.x = x;
        this.y = y;

        width = 80;
        height = 40;

        paint = new Paint();

        topColor = Color.parseColor("#24d2c7");
        leftColor = Color.parseColor("#1d9aa6");
        rightColor = Color.parseColor("#185686");

        topSidePath = new Path();
        leftSidePath = new Path();
        rightSidePath = new Path();

        topSidePath.moveTo(x, y + height/2);
        topSidePath.lineTo(x + width/2, y);
        topSidePath.lineTo(x + width, y + height/2);
        topSidePath.lineTo(x + width/2, y + height);

        leftSidePath.moveTo(x, y + height/2);
        leftSidePath.lineTo(x + width/2, y + height);
        leftSidePath.lineTo(x + width/2, y + height*3);
        leftSidePath.lineTo(x, y + height*3 - height/2);

        rightSidePath.moveTo(x + width/2, y + height);
        rightSidePath.lineTo(x + width, y + height/2);
        rightSidePath.lineTo(x + width, y + height*3 - height/2);
        rightSidePath.lineTo(x + width/2, y + height*3);
    }

    public void draw(Canvas canvas){
        drawTopSide(canvas);
        drawLeftSide(canvas);
        drawRightSide(canvas);
    }

    private void drawTopSide(Canvas canvas){
        paint.setColor(topColor);
        canvas.drawPath(topSidePath,paint);
    }

    private void drawLeftSide(Canvas canvas){
        paint.setColor(leftColor);
        canvas.drawPath(leftSidePath,paint);
    }

    private void drawRightSide(Canvas canvas){
        paint.setColor(rightColor);
        canvas.drawPath(rightSidePath,paint);
    }
}
