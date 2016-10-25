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

        initPaths();
    }

    private void initPaths(){
        topSidePath.reset();
        leftSidePath.reset();
        rightSidePath.reset();

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

    public void setY(int y) {
        this.y = y;
        initPaths();
    }

    public int getY() {
        return y;
    }

    public boolean isPointInBounds(int pointX, int pointY){
        // TODO 1 знайти діагоналі ромба
        double AC = Math.sqrt(Math.pow((x + width) - x,2) +  Math.pow((y + height /2) - (y + height /2),2));
        double BD = Math.sqrt(Math.pow((x + width /2) - (x + width /2),2) +  Math.pow((y + height) - y,2));
        // TODO 2 знайти площу ромба
        double S = (AC*BD)/2;
        // TODO 3 знайти чотири площі трикутників
        double sTr1 = Math.abs(((x-pointX)*(y-pointY)-((x + width /2)-pointX)*((y + height /2)-pointY))/2);   // ABQ
        double sTr2 = Math.abs(((x-(x + width /2))*(pointY-(y + height))-(pointX-(x + width /2))*((y + height /2)-(y + height)))/2);   // AQD
        double sTr3 = Math.abs((((x + width /2)-pointX)*((y + height /2)-pointY)-((x + width)-pointX)*(y-pointY))/2);   // BCQ
        double sTr4 = Math.abs((((x + width /2)-pointX)*((y + height /2)-pointY)-((x + width)-pointX)*((y + height)-pointY))/2);   // DCQ
        // TODO 4 порівняти результат
        double result = (sTr1 + sTr2 + sTr3 + sTr4) - S;

        return result <= 100 && result >= 0;
    }
}
