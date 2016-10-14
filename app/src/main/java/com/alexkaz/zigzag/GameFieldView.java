package com.alexkaz.zigzag;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class GameFieldView extends View {
    public GameFieldView(Context context) {
        super(context);
    }

    public GameFieldView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GREEN);
        Log.d("myLog",canvas.getWidth() + " " + canvas.getHeight()); //480x800
        int horizontalLineCount = canvas.getWidth()/20;
        int verticalLineCount = canvas.getHeight()/20;

        for (int i = 0; i < horizontalLineCount; i++) {
            canvas.drawLine(i*20,0,i*20,canvas.getHeight(),new Paint());
        }
        for (int i = 0; i < verticalLineCount; i++) {
            canvas.drawLine(0,i*20,canvas.getWidth(),i*20,new Paint());
        }
    }
}
