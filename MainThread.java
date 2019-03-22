package com.example.admin.checklist;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class MainThread extends Thread {
    private static final String TAG= MainThread.class.getSimpleName();
    //флаг, указывающий на то, что игра запущена.

    private boolean running;
    private SurfaceHolder surfaceHolder;
    private MainGamePanel gamePanel;

    public MainThread(SurfaceHolder surfaceHolder, MainGamePanel gamePanel) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }
    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run(){
        Canvas canvas;
        Log.d(TAG,"Starting game loop");
        while(running){
            canvas=null;
// пытаемся заблокировать canvas
// для изменение картинки на поверхности
            try{
                canvas= this.surfaceHolder.lockCanvas();
                synchronized(surfaceHolder){
// здесь будет обновляться состояние игры
// и формироваться кадр для вывода на экран
                    this.gamePanel.update();
                    this.gamePanel.onDraw(canvas);//Вызываем метод для рисования
                }
            } finally{
// в случае ошибки, плоскость не перешла в
//требуемое состояние
                if(canvas!=null){
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}
