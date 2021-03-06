package com.alexkaz.zigzag.game_components;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public class GameController {
    private BGPainter bgPainter;
    private Floor floor;
    private Ball ball;

    public GameController() {
        bgPainter = new ArcBGPainter();
        floor = new Floor();
        ball = new Ball();
    }

    public void draw(Canvas canvas){
        drawGrid(canvas);
        bgPainter.draw(canvas);
        floor.draw(canvas);
        floor.move();
        ball.draw(canvas);
        ball.move();
        ////////////////////
        // TODO code for debugging
        Paint txtPaint = new Paint();
        txtPaint.setTextSize(50);
        if (floor.isPointInBounds(ball.getX(),ball.getY())){
            txtPaint.setColor(Color.GREEN);
            canvas.drawText("В межах", 50,50,txtPaint);
        } else {
            txtPaint.setColor(Color.RED);
            canvas.drawText("За межами", 50,50,txtPaint);
        }
        ////////////////////
    }

    public void changeBallDirection(){
        ball.changeDirection();
    }

    private void drawGrid(Canvas canvas){
        canvas.drawColor(Color.GREEN);
        Log.d("myLog",canvas.getWidth() + " " + canvas.getHeight()); //480x800
        int cellSize = 40;
        int horizontalLineCount = canvas.getWidth()/cellSize;
        int verticalLineCount = canvas.getHeight()/cellSize;

        for (int i = 0; i < horizontalLineCount; i++) {
            canvas.drawLine(i*cellSize,0,i*cellSize,canvas.getHeight(),new Paint());
        }
        for (int i = 0; i < verticalLineCount; i++) {
            canvas.drawLine(0,i*cellSize,canvas.getWidth(),i*cellSize,new Paint());
        }
    }
}
