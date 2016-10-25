package com.alexkaz.zigzag.game_components;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Random;

public class Floor {

    private ArrayList<FloorElement> elementList;
    private Random rand;
    private int x;
    private int y;
    private int translationX;
    private int translationY;
    private int elemCount;
    private int lastX;
    private int leftScreenBorder;
    private int rightScreenBorder;

    private int leftBorder;
    private int rightBorder;

    public Floor() {
        elementList = new ArrayList<>();
        rand = new Random();

        x = 240;
        y = 500;
        translationX = 40;
        translationY = 20;
        elemCount = 0;
        lastX = x;
        leftScreenBorder = 0;
        rightScreenBorder = 480;

        buildRandomFloor();
    }

    private void buildRandomFloor(){
        // записувати в ліст буліанів кожен шаг , по якому і визначатимемо колізію
        for (int i = 0; i < 20; i++) {
            addFloorElem();
        }
    }

    public void addFloorElem(){
        int toLeftOrRight = rand.nextInt(100);
        if (toLeftOrRight >= 50){
            if (lastX - translationX > leftScreenBorder){
                elementList.add(new FloorElement(lastX - translationX, y - elemCount*translationY));
                lastX = lastX - translationX;
            } else {
                elementList.add(new FloorElement(lastX + translationX, y - elemCount*translationY));
                lastX = lastX + translationX;
            }
        } else {
            if (lastX + translationX*2 < rightScreenBorder){
                elementList.add(new FloorElement(lastX + translationX, y - elemCount*translationY));
                lastX = lastX + translationX;
            } else {
                elementList.add(new FloorElement(lastX - translationX, y - elemCount*translationY));
                lastX = lastX - translationX;
            }
        }
        elemCount = elementList.size();
    }

    public void move(){
        for (FloorElement element : elementList) {
            element.setY(element.getY() + 2);
        }
    }

    public void draw(Canvas canvas){
        for (int i = elementList.size()-1; i >= 0; i--) {
            elementList.get(i).draw(canvas);
        }
    }
}
