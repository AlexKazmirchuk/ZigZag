package com.alexkaz.zigzag.game_components;

import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class Floor {

    private ArrayList<FloorElement> elementList;
    private int x;
    private int y;
    private int translationX;
    private int translationY;
    private int elemCount;
    private int lastX;

    public Floor() {
        x = 240;
        y = 500;
        translationX = 40;
        translationY = 20;
        elemCount = 0;
        lastX = x;
        elementList = new ArrayList<>();
        buildRandomFloor();
    }

    private void buildRandomFloor(){
        // записувати в ліст буліанів кожен шаг , по якому і визначатимемо колізію
        elementList.add(new FloorElement(x,y));
        elemCount = elementList.size();
        int leftBorder = 0;
        int rightBorder = 480;

        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            int toLeftOrRight = random.nextInt(100);
            if (toLeftOrRight >= 50){
                if (lastX - translationX > leftBorder){
                    elementList.add(new FloorElement(lastX - translationX, y - elemCount*translationY));
                    lastX = lastX - translationX;
                } else {
                    elementList.add(new FloorElement(lastX + translationX, y - elemCount*translationY));
                    lastX = lastX + translationX;
                }
            } else {
                if (lastX + translationX*2 < rightBorder){
                    elementList.add(new FloorElement(lastX + translationX, y - elemCount*translationY));
                    lastX = lastX + translationX;
                } else {
                    elementList.add(new FloorElement(lastX - translationX, y - elemCount*translationY));
                    lastX = lastX - translationX;
                }
            }
            elemCount = elementList.size();
        }
    }

    public void draw(Canvas canvas){
        for (int i = elementList.size()-1; i >= 0; i--) {
            elementList.get(i).draw(canvas);
        }
    }
}
