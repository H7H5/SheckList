package com.example.admin.checklist;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

import java.util.ArrayList;

public class Paragraph {
    private int screniky;
    private int x;
    private int y;
    private int y1;
    private int width;
    private int height;
    private String s;
    private int countPodParagraph = 0;
    String[]procedure;
    Rect rectangle;
    private boolean flagComplete = false;
    private boolean toutch = false;
    private Paint paint = new Paint();
    private boolean flagStart = true;
    private boolean vip = false;
    ArrayList<PodParagraph> podParagraphs = new ArrayList<>();


    public String getS() {
        return s;
    }
    public Paragraph(int x, int y, int width, int height, String s, int countPodParagraph, String[] procedure, int screniky ){
        this.procedure = procedure;
        this.x = x;
        this.y = y;
        this.y1 = y;
        this.width = width;
        this.height = height;
        this.s = s;
        this.screniky = screniky;
        this.countPodParagraph = countPodParagraph;
        rectangle = new Rect(x,y,x+ width,y + height);
        createClass();

    }
    public void upDate(){
        flagComplete = true;
        for(int i = 0; i < podParagraphs.size();i++){
            if (podParagraphs.get(i).isToutch()==false){
                flagComplete = false;
            }
        }
    }

    public boolean isToutch() {
        return toutch;
    }

    public void setToutch(boolean toutch) {
        this.toutch = toutch;
    }

    public int getX() {
        return x;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void draw (Canvas canvas){
        if ((flagStart==true)||((flagStart==false)&&(vip == true))){
            paint.setTypeface(Typeface.create(Typeface.SERIF, Typeface.BOLD));
            if (toutch) {
                paint.setARGB(200, 0, 93, 170);
            } else {
                paint.setARGB(200, 150, 150, 150);
            }
            canvas.drawRect(x, y, x + width, y + height, paint);
            if (toutch){
                for (int i = 0; i < podParagraphs.size(); i++) {
                    podParagraphs.get(i).draw(canvas);
                }
            }
            paint.setColor(Color.BLACK);
            paint.setTextSize(screniky*2.5f);
            canvas.drawText(s + " ", x + 10, y + (screniky * 4.5f), paint);
            if (flagComplete) {
                paint.setARGB(200, 15, 167, 7);
                canvas.drawRect(x + width - ((height / 4) * 3), y + (height / 4), x + width - (height / 4), y + height - (height / 4), paint);
            }
        }
    }
    public int getY() {
        return y;
    }
    public Rect getRectangle() {
        return rectangle;
    }
    public void handleActionDown(int eventX, int eventY){
        if(eventX>=(x)&&(eventX<=(x + width))){
            if(eventY>=(y)&&(eventY<=(y + height))){
                toutch = true;

            }else {
                toutch = false;
            }
        }else {
            toutch = false;
        }
    }
    private void createClass(){
        int d = screniky*17;
        for(int i = 0;i<countPodParagraph; i++ ){
            podParagraphs.add(new PodParagraph(0,i*(screniky*10)+d,width,screniky*10,procedure[i],screniky));
            d +=screniky*2;
        }
    }
    public boolean isFlagComplete() {
        return flagComplete;
    }
    public void toZero(){
        for (int i = 0; i<podParagraphs.size();i++ ){
            podParagraphs.get(i).setToutch(false);
        }
    }

    public void setFlagStart(boolean flagStart) {
        this.flagStart = flagStart;
    }
    public void setVip(boolean vip) {
        this.vip = vip;
        setCoordinat(vip);
    }

    public void setCoordinat(boolean b){
        if (b) {
            y = screniky * 7;
        }else {
            y = y1;
        }
    }
}

