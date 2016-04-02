package com.example.anirudh.material3;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.anirudh.material3.R;

/**
 * Created by anirudh on 16/3/16.
 */
public class ChatHeadService extends Service {

    String from,msg;
    private WindowManager windowManager;
    private ImageView chatHead;
    WindowManager.LayoutParams params;

    @Override public IBinder onBind(Intent intent) {
        from = intent.getStringExtra("From");
        msg = intent.getStringExtra("Msg");
        return null;
    }

    @Override public void onCreate() {
        super.onCreate();

        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        chatHead = new ImageView(this);
        chatHead.setImageResource(R.drawable.ic_language_black_24dp);

        params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.TOP | Gravity.LEFT;
        params.x = 0;
        params.y = 100;

        windowManager.addView(chatHead, params);

        chatHead.setOnTouchListener(new View.OnTouchListener() {
            private int initialX;
            private int initialY;
            private float initialTouchX;
            private float initialTouchY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        initialX = params.x;
                        initialY = params.y;
                        initialTouchX = event.getRawX();
                        initialTouchY = event.getRawY();
                        Intent i = new Intent(getBaseContext(),MainActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        i.setClass(getBaseContext(), MainActivity.class);
                        i.putExtra("Msg", msg);
                        i.putExtra("From", from);
                        startActivity(i);
                        return true;
                    case MotionEvent.ACTION_UP:
                        return true;
                    case MotionEvent.ACTION_MOVE:


                        params.x = initialX + (int) (event.getRawX() - initialTouchX);
                        params.y = initialY + (int) (event.getRawY() - initialTouchY);
                        windowManager.updateViewLayout(chatHead, params);
                        return true;
                }
                return false;
            }
        });

        /*chatHead.setOnTouchListener(new View.OnTouchListener() {
            private int initialX;
            private int initialY;
            private float initialTouchX;
            private float initialTouchY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        initialX = params.x;
                        initialY = params.y;
                        initialTouchX = event.getRawX();
                        initialTouchY = event.getRawY();
                        return true;
                    case MotionEvent.ACTION_UP:
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        params.x = initialX
                                + (int) (event.getRawX() - initialTouchX);
                        params.y = initialY
                                + (int) (event.getRawY() - initialTouchY);
                        windowManager.updateViewLayout(chatHead, params);
                        return true;
                }
                return false;
            }
        });*/
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        from = intent.getStringExtra("From");
        msg = intent.getStringExtra("Msg");
        Log.d("In Start", "In start");
        return startId;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (chatHead != null) windowManager.removeView(chatHead);
    }
}
