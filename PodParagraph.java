package com.example.admin.checklist;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

public class PodParagraph {
    private int screniky;
    private int x;
    private int y;
    private int width;
    private int height;
    private String s;
    Rect rectangle;
    String procedure;
    private boolean toutch = false;
    private Paint paint = new Paint();
    String[] words;
    public PodParagraph(int x, int y,int width,  int height,String procedure,int screniky){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.s = s;
        this.screniky = screniky;
        this.procedure = procedure;
        rectangle = new Rect(x,y,x+ width,y + height);
        words = procedure.split("\\s");
    }
    public void upDate(){
    }
    public void setToutch(boolean toutch) {
        this.toutch = toutch;
    }

    public void draw (Canvas canvas){
        paint.setTypeface(Typeface.create(Typeface.SERIF, Typeface.NORMAL));
        if (toutch){
            paint.setARGB(220,0,139,26);
        }else {
            paint.setARGB(220,168,217,0);
        }
        canvas.drawRect( x,y,x+width,y+height, paint);
        paint.setColor(Color.BLACK);
        paint.setTextSize(screniky * 3);
        float widthS;
        float widthS2;
        int bS;
        int bS2;
        int bs1= x+20 ;
        int by1= y + (screniky * 3);
        for (int i = 0; i < words.length;i++){
            canvas.drawText(words[i],bs1,by1,paint);
            widthS = paint.measureText(words[i]);
            if (i < words.length-1) {
                widthS2 = paint.measureText(words[i + 1]);
            }else {
                widthS2 = 0;
            }
            bS=(int)widthS;
            bS2=(int)widthS2;
            bs1 = bs1 + bS+5;
            if (bs1+bS2> width){
                bs1 =10;
                by1 = by1 + (screniky * 3);
            }
        }
    }
    public int getY() {
        return y;
    }
    public Rect getRectangle() {
        return rectangle;
    }
    public boolean isToutch() {
        return toutch;
    }
    public void handleActionDown(int eventX, int eventY){
        if(eventX>=(x)&&(eventX<=(x + width))) {
            if (eventY >= (y) && (eventY <= (y + height))) {
                toutch = !toutch;
            }
        }
    }
}


