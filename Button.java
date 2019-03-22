package com.example.admin.checklist;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Button {
    private int screniky;
    private int x;
    private int y;
    private int width;
    private int height;
    private Paint paint = new Paint();
    private String[] procedure = {"РУССКИЙ","ENGLISH","УКРАЇНСЬКА"};
    private int  num =0;
    private String[]yess={"ВЫПОЛНЕНО","DONE","ВИКОНАНО"};
    private String[]nos={"НЕ ГОТОВ","NOT READY","НЕ ГОТОВИЙ"};
    private String yes = yess[0];
    private String no = nos[0];
    private int coordX;
    private int coordY;
    public Button(int x, int y, int width, int height,int screniky){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.screniky = screniky;
        coordX = width/2-width/6;
        coordY = y+screniky*4;
    }

    public String getYes() {
        return yes;
    }

    public String getNo() {
        return no;
    }

    public void draw (Canvas canvas){
        paint.setARGB(100, 0, 93, 170);
        canvas.drawRect(x , y , x + width, y + height, paint);

        paint.setColor(Color.BLACK);
        paint.setTextSize(screniky*3);
        canvas.drawText(procedure[num], width/2-width/6, y+screniky*4, paint);
    }
    public int getX() {
        return x;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
        yes=yess[num];
        no = nos[num];
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getCoordX() {
        return coordX;
    }

    public int getCoordY() {
        return coordY;
    }
}
