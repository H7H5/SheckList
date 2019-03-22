package com.example.admin.checklist;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Build;
import android.preference.PreferenceManager;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.util.ArrayList;
import java.util.Random;

import android.content.SharedPreferences;
import android.view.WindowManager;

import static java.lang.Math.abs;
import static java.lang.Math.asin;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

public class MainGamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;
    ArrayList<Paragraph> paragraphs = new ArrayList<>();
    Context context;
    private Bitmap bitmap;
    private Paint paint = new Paint();
    SharedPreferences prefs;
    SharedPreferences prefs1;
    String s = "Hello";
    Button button;
    private int butnum =0;
    private int screniky =0;
    private boolean flagStart = true;
    private int nemberParagraphs = 0;
    private int numberParagraphs = 0;
    String name = "";
    String name1 = "КАРТА КОНТРОЛЬНЫХ ПРОВЕРОК ИЛ-76";
    String name3 = "КАРТА КОНТРОЛЬНИХ ПЕРЕЕВІРОК ІЛ-76";
    String name2 = "MAP OF CONTROL CHECKS OF IL-76";
    String[] procedure;
    String[] procedure11 = {"ПЕРЕД ЗАПУСКОМ ДВИГАТЕЛЕЙ","ПЕРЕД ВЫРУЛИВАНИЕМ","НА РУЛЕНИИ","НА ПРЕДВАРИТЕЛЬНОМ СТАРТЕ"};
    String[] procedure13 = {"ПЕРЕД ЗАПУСКОМ ДВИГУНІВ", "ПЕРЕД ВИРУЛЮВАННЯМ", "НА РУЛЮВАННІ", "НА ПОПЕРЕДНЬОМУ СТАРТІ"};
    String[] procedure12 = {"BEFORE STARTING ENGINES", "BEFORE DRIVING OUT", "ON DRIVE", "ON PRELIMINARY START"};
    String[][] parts;
    String[][] parts1={{"Двери люки закрыты","Топливные насосы - насосы подкачки включены",
            "Задатчик стабилизатора - Положение (П,С,З) по центровке","Тримирование нейтрально"},
        {"Электросистемы, потребители - проверены, включены","Курс МП, АРК - включены, частота выставлена",
                "Бустера - включены, крышка закрыта","Точная курсовая система - включена, согласована, режим ГПК",
                "АБСУ - исправна, режим штурвальный"},
        {"Тормоза - проверены, исправны","Обогрев приемника полного давления - включен",
                "Электрический указатель поворота - включен"},
        {"Высотомер - высота ноль, давление аеродрома",
                "Механизация - выпущена на 15 градусов, табло горит",
                "Интерцепторы - убраны, табло не горит",
                "Авиагоризонты - проверены, риски совмещены",
                "Рули, элероны - проверены, свободны",
                "Вспомогательная силовая установка - выключена",
                "Топливная система - на автомате"}};
    String[][] parts2={{"The doors of the hatches are closed", "Fuel pumps - booster pumps are included",
            "Stabilizer setpoint - Position (П, C, З) by centering", "Trimming neutral"},
            {"Electrical systems, consumers - checked, included", "MP course, ARC - included, frequency set",
                    "Boosters - included, the lid is closed", "Exact exchange rate system - enabled, agreed, mode of the ГПК",
                    "ABSU is normal, steering mode"},
            {"Brakes - checked, operational", "Heating of the full pressure receiver is on",
                    "Electric direction indicator - on"},
            {"Altimeter - height zero, airfield pressure",
                    "Mechanization - released at 15 degrees, the board is lit",
                    "Interceptors are removed, the board does not burn,",
                    "Artificial horizons are checked, risks are combined",
                    "Steering wheels, ailerons - checked, free",
                    "Auxiliary power unit - off",
                    "Fuel system - on the machine"}};
    String[][] parts3={{"Двері люки закриті","Паливні насоси - насоси підкачки включені",
            "Задатчик стабілізатора - положення (П, С, З) по центрівці", "Тримуваняя нейтрально"},
        {"Електросистеми, споживачі - перевірені, включені", "Курс МП, АРК - включені, частота виставлена",
                "Бустера - включені, кришка закрита", "Точна курсова система - включена, узгоджена, режим ГПК",
                "АБСУ - справна, режим штурвальний"},
        { "Гальма - перевірені, справні", "Обігрів приймача повного тиску - включений",
                "Електричний покажчик повороту - включений"},
        { "Висотомір - висота нуль, тиск аеродрома",
                "Механізація - випущена на 15 градусів, табло горить ",
                "Інтерцептори - прибрані, табло не горить",
                "Авіагоризонт - перевірений, лінії суміщені",
                "Рулі, елерони - перевірені, вільні",
                "Допоміжна силова установка - виключена",
                "Паливна система - на автоматі"}};
    private boolean flagComplete = false;
    public MainGamePanel( Context context) {
        super(context);
        this.context = context;
        Context applicationContext = context;
        prefs = PreferenceManager.getDefaultSharedPreferences(applicationContext);
        prefs1 = PreferenceManager.getDefaultSharedPreferences(applicationContext);
        // Добавляем этот класс, как содержащий функцию обратного
        // вызова для взаимодействия с событиями
        getHolder().addCallback(this);
        // делаем GamePanel focusable, чтобы она могла обрабатывать сообщения
        screniky = getHeight(context)/100;
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.back);
        createClass(0);
        button = new Button(0,screniky*100,getWidth(context),screniky*10,screniky);
        //paragraphs.add(new Paragraph(50,50,50,50));
        thread = new MainThread(getHolder(), this);
        setFocusable(true);

    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.setRunning(true);
        thread.start();
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        //посылаем потоку команду на закрытие и дожидаемся,
        //пока поток не будет закрыт.
        boolean retry = true;
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
                // пытаемся снова остановить поток thread
            }
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                for (int i = 0; i < paragraphs.size(); i++) {
                    if (paragraphs.get(i).isToutch()) {
                        for (int j = 0; j < paragraphs.get(i).podParagraphs.size(); j++) {
                            paragraphs.get(i).podParagraphs.get(j).handleActionDown((int) event.getX(), (int) event.getY());
                        }
                    }
                }
                if ((int) event.getY() <= screniky*7) {
                    flagStart = true;
                    for(int i = 0;i<paragraphs.size();i++){
                        paragraphs.get(i).setVip(false);
                        paragraphs.get(i).setToutch(false);
                        paragraphs.get(i).toZero();
                        paragraphs.get(i).setFlagStart(flagStart);
                    }
                }
                if(flagStart) {
                    if ((int) event.getX() >= button.getX() && ((int) event.getX() <= button.getX()+button.getWidth())) {
                        if ((int) event.getY() >= button.getY() && ((int) event.getY() <= button.getY()+button.getHeight())) {
                            butnum++;
                            if(butnum>=3){
                                butnum = 0;
                            }
                            button.setNum(butnum);
                            createClass(butnum);
                        }
                    }
                    for (int i = 0; i < paragraphs.size(); i++) {
                        if ((int) event.getX() >= (paragraphs.get(i).getX()) && ((int) event.getX() <= (paragraphs.get(i).getX() + paragraphs.get(i).getWidth()))) {
                            if ((int) event.getY() >= (paragraphs.get(i).getY()) && ((int) event.getY() <= (paragraphs.get(i).getY() + paragraphs.get(i).getHeight()))) {
                                for (int j = 0; j < paragraphs.size(); j++) {
                                    paragraphs.get(j).setToutch(false);
                                    flagStart = false;
                                    paragraphs.get(j).setVip(false);
                                    paragraphs.get(j).setFlagStart(flagStart);

                                    if (j != i) {
                                        paragraphs.get(j).toZero();
                                    }
                                }
                                numberParagraphs = i;
                                paragraphs.get(i).setToutch(true);
                                paragraphs.get(i).setVip(true);
                            } else {
                            }
                        } else {
                        }
                    }
                }else{
                    if ((int) event.getX() >= (paragraphs.get(numberParagraphs).getX()) &&
                            ((int) event.getX() <= (paragraphs.get(numberParagraphs).getX() + paragraphs.get(numberParagraphs).getWidth()))) {
                        if ((int) event.getY() >= (paragraphs.get(numberParagraphs).getY()) &&
                                ((int) event.getY() <= (paragraphs.get(numberParagraphs).getY() + paragraphs.get(numberParagraphs).getHeight()))) {
                            paragraphs.get(numberParagraphs).setVip(false);
                            paragraphs.get(numberParagraphs).setToutch(false);
                            if(numberParagraphs >= paragraphs.size()-1){
                                numberParagraphs = 0;
                            }else {
                                numberParagraphs++;
                            }
                            paragraphs.get(numberParagraphs).setVip(true);
                            paragraphs.get(numberParagraphs).setToutch(true);
                            for(int j =0; j<paragraphs.size();j++) {
                                if (j != numberParagraphs) {
                                    paragraphs.get(j).toZero();
                                }
                            }
                        }
                    }
                }
            }
            if (event.getAction() == MotionEvent.ACTION_MOVE) {
                //if ((getMode() == 3) && (level != null)) {
                    //if (level.isTouched()) {
                       // level.action_move((int) event.getX(),(int) event.getY());
                    //}
                //}
            }
            if (event.getAction() == MotionEvent.ACTION_UP) {
                //if ((getMode() == 3) && (level != null)) {
                    //if (level.isTouched()) {
                    //    level.setTouched(false);
                    //}
                //}
            }
        return true;
    }
    @Override
    protected void onDraw(Canvas canvas) {///////////////////////////////////////////////////////////
        paint.setTypeface(Typeface.create(Typeface.SERIF, Typeface.BOLD));
        paint.setColor(Color.WHITE); //
        canvas.drawRect( 0,0,getWidth(),getHeight(), paint);
        canvas.drawBitmap(bitmap,0,0,null);// задний фон
        for (int i = 0; i < paragraphs.size(); i++) {
            paragraphs.get(i).draw(canvas);
        }
        if(flagStart == false) {

            if (flagComplete) {
                paint.setARGB(255, 2,118,24);
                canvas.drawRect(0,getHeight(context),getWidth(context),screniky*100,paint);
                paint.setColor(Color.BLACK);
                paint.setTextSize((getHeight(context) / 20) / 2);
                canvas.drawText(button.getYes(), button.getCoordX(), button.getCoordY(), paint);
            } else {
                paint.setARGB(255, 90, 90, 90);
                canvas.drawRect(0,getHeight(context),getWidth(context),screniky*100,paint);
                paint.setColor(Color.RED);
                paint.setTextSize((getHeight(context) / 20) / 2);
                canvas.drawText(button.getNo(),button.getCoordX(), button.getCoordY(),paint);
            }
        }else {
            button.draw(canvas);
        }
        paint.setTextSize((2.5f*screniky));
        paint.setColor(Color.BLACK);
        canvas.drawText(name,0,5*screniky,paint);
    }
    public void update(){////////////////////////////////////////////////////////////////////////////
        for (int i = 0; i < paragraphs.size(); i++) {
            paragraphs.get(i).upDate();
        }
        flagComplete = false;
        for (int i = 0; i < paragraphs.size(); i++) {
            if(paragraphs.get(i).isFlagComplete()==true){
                flagComplete = true;
                nemberParagraphs = i;
            }
        }
    }
    private void createClass(int l){
        int d = 7*screniky;
        switch (l){
            case 0:
                name = name1;
                procedure = procedure11;
                parts = parts1;
                paragraphs.clear();
                for(int i = 0;i<4; i++ ){
                    paragraphs.add(new Paragraph(0,i*(screniky*7)+d,getWidth(context),7*screniky,procedure[i],parts[i].length,parts[i],screniky));
                    d +=screniky;
                }
                break;
            case 1:
                name = name2;
                procedure = procedure12;
                parts = parts2;
                paragraphs.clear();
                for(int i = 0;i<4; i++ ){
                    paragraphs.add(new Paragraph(0,i*(screniky*7)+d,getWidth(context),7*screniky,procedure[i],parts[i].length,parts[i],screniky));
                    d +=screniky;
                }
                break;
            case 2:
                name = name3;
                procedure = procedure13;
                parts = parts3;
                paragraphs.clear();
                for(int i = 0;i<4; i++ ){
                    paragraphs.add(new Paragraph(0,i*(screniky*7)+d,getWidth(context),7*screniky,procedure[i],parts[i].length,parts[i],screniky));
                    d +=screniky;
                }
                break;
        }

    }
    public static int getWidth(Context mContext){
        int width;
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        if(Build.VERSION.SDK_INT>12){
            Point size = new Point();
            display.getSize(size);
            width = size.x;
        }
        else{
            width = display.getWidth();  // Deprecated
        }
        return width;
    }
    public static int getHeight(Context mContext){
        int height;
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        if(Build.VERSION.SDK_INT>12){
            Point size = new Point();
            display.getSize(size);
            height = size.y;
        }
        else{
            height = display.getHeight();  // Deprecated
        }
        return height;
    }
}



















