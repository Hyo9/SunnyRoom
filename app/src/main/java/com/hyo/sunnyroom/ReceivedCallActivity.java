package com.hyo.sunnyroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;

public class ReceivedCallActivity extends AppCompatActivity {

    SwipeButton swipeOnButton, swipeOffButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_received_call);

        swipeOnButton = (SwipeButton) findViewById(R.id.on_swipe_button);
        swipeOffButton = (SwipeButton) findViewById(R.id.off_swipe_button);

        // on 스와이프
        swipeOnButton.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(boolean active) {
                Log.d("swipe on", "액티비티 호출");
                Intent intent = new Intent(ReceivedCallActivity.this, CallingActivity.class);
                startActivity(intent);

            }
        });


        // Off 스와이프
        swipeOffButton.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(boolean active) {
                //Log.d("swipe oFf", "액티비티 호출");
                finish();
            }
        });
    }
}