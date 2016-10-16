package com.alexkaz.zigzag;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.alexkaz.zigzag.game_components.GameController;

public class GameFieldView extends View implements View.OnTouchListener {
    private boolean renderFlag = true;
    private int delay = 100;
    private MyHandler myHandler;
    private GameController gameController;

    public GameFieldView(Context context) {
        super(context);
        initComponents(context);
    }

    public GameFieldView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initComponents(context);
    }

    private void initComponents(Context context){
        myHandler = new MyHandler(this);
        gameController = new GameController();
        setOnTouchListener(this);
        startInvalidating(context);
    }

    private void startInvalidating(Context context){
        renderFlag = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (renderFlag){
                    try {
                        Thread.sleep(delay);
                        myHandler.sendEmptyMessage(0);
                    } catch (InterruptedException ignored) {}
                }
            }
        }).start();
    }

    private void stopInvalidating(){
        renderFlag = false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        gameController.draw(canvas);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            gameController.changeBallDirection();
            return true;
        }
        return false;
    }

    private class MyHandler extends Handler{
        private View view;

        MyHandler(View view) {
            this.view = view;
        }

        @Override
        public void handleMessage(Message msg) {
            view.invalidate();
        }
    }
}
