package com.example.anirudh.material3;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import static android.view.MotionEvent.*;

public class MainActivity extends AppCompatActivity {

    static float initialY, finalY, tempX=0;
    String from, msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab= (FloatingActionButton) findViewById(R.id.myFAB);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("hello","clicked");
            }
        });

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(getApplication(), ChatHeadService.class));
                Log.i("test","button clicked!!");
            }
        });


        Intent incomingIntent = getIntent();
        from = incomingIntent.getStringExtra("From");
        msg = incomingIntent.getStringExtra("Msg");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(from);
        builder.setMessage(msg);
        Log.d("Activity", "Came in dialog");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("Activity", "Came in OK ");
                MainActivity.this.finish();
            }
        });

        builder.show();


        Button button = (Button) findViewById(R.id.button);
        /*button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()){
                    case MotionEvent.ACTION_MOVE :
                        if(tempX - event.getY()>15){
                            Log.i("test","moved");
                        }
                        tempX = event.getY();
                        break;
                    case MotionEvent.ACTION_DOWN :
                        MainActivity.initialY =  event.getY();
                        break;
                    case MotionEvent.ACTION_UP :
                        MainActivity.finalY = event.getY();
                        if(MainActivity.finalY-MainActivity.initialY>=0){
                            Log.i("test","swipe down");
                        }
                        else{
                            Log.i("test","swipe up");
                        }
                        break;
                }
                return false;
            }
        });*/
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
