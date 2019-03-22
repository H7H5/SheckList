package com.example.admin.checklist;
import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;


import android.content.SharedPreferences;
public class MainActivity extends Activity {
    private static final String TAG= MainActivity.class.getSimpleName();
    public MainGamePanel mainGamePanel;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //SharedPreferences save = getSharedPreferences("Save",MODE_PRIVATE);
        //SharedPreferences.Editor editor = save.edit();
        //editor.putInt("save",0);
        //editor.commit();
        // запрос на отключение строки заголовка
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // перевод приложения в полноэкранный режим
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // устанавливаем MainGamePanel как View
        // createFile();
        setContentView(mainGamePanel = new MainGamePanel(this));
        Log.d(TAG,"View added");
    }
    @Override
    protected void onDestroy(){
        Log.d(TAG,"Destroying...");
        super.onDestroy();
    }
    @Override
    protected void onStop(){
        Log.d(TAG,"Stopping...");
        super.onStop();
    }
}
