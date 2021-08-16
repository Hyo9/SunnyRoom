package com.hyo.sunnyroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


/*
* 메인화면
*/

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 스플래시 띄워주고 스타일을 다시 기본 테마로 변경
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);

        // 이미지뷰에 버튼 리스너를 달아서 출력
        ImageView phone_call_button = (ImageView) findViewById(R.id.phone_call_button);

        phone_call_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PhoneCallActivity.class);
                startActivity(intent);
            }
        });

    }
}