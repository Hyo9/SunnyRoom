package com.hyo.sunnyroom;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class PhoneCallActivity extends AppCompatActivity {
    private Context mContext;
    int mYear, mMonth, mDay, mHour, mMinute;
    int mCount = 0;
    TextView mTxtTime, mTxtIter, mTxtReceiver;
    EditText iTxtReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_call);

        // preference 세
        mContext = this;

        // 시간세팅
        mTxtTime = (TextView) findViewById(R.id.time_setting_text);

        Calendar cal = new GregorianCalendar();

        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);
        mHour = cal.get(Calendar.HOUR_OF_DAY);
        mMinute = cal.get(Calendar.MINUTE);

        UpdateNow();

        // 횟수 세팅
        mTxtIter = (TextView) findViewById(R.id.iter_setting_text);
        mTxtIter.setText(mCount+"");

        // 수신인 세팅
        mTxtReceiver = (TextView) findViewById(R.id.receiver_setting_text);
        mTxtReceiver.setText("");
        iTxtReceiver = (EditText) findViewById(R.id.receiver_setting_editor);
    }

    // 온클릭 이벤트를 만들어놓고, xml에서 대응시켜 어떤 행동을 할지 분기처
    public void mOnClick(View v){
        switch(v.getId()){

            //시간 대화상자 버튼이 눌리면 대화상자를 보여줌
            case R.id.time_setting_button:

                //여기서 리스너도 등록함
                new TimePickerDialog(PhoneCallActivity.this, mTimeSetListener, mHour,
                        mMinute, false).show();
                break;

            case R.id.iter_up_button:
                mCount++;
                mTxtIter.setText(mCount+"");
                break;

            case R.id.iter_down_button:

                if(mCount>0) {
                    mCount--;
                    mTxtIter.setText(mCount+"");
                }
                break;

            case R.id.receiver_setting_button:

                mTxtReceiver.setText(iTxtReceiver.getText().toString());
                break;

            case R.id.setting_done_button:
                // setting 완료시 sharedpreference에 저장
                //String sTime, sReceiver, sIter; // 알람시간, 수신인, 반복횟수

                if(mTxtReceiver.getText().toString().equals("")) {
                    Toast.makeText(PhoneCallActivity.this, "수신인 입력해주세요", Toast.LENGTH_LONG).show();
                    //Log.d("pre receiver", PreferenceManager.getString(mContext, "sReceiver"));
                }
                else if(mCount == 0) {
                    Toast.makeText(PhoneCallActivity.this, "횟수 입력해주세요", Toast.LENGTH_LONG).show();
                    //Log.d("pre time", PreferenceManager.getString(mContext, "sIter")+"");
                }
                else {
                    PreferenceManager.setString(mContext, "sTime", mTxtTime.getText().toString());
                    PreferenceManager.setString(mContext, "sReceiver", mTxtReceiver.getText().toString());
                    PreferenceManager.setInt(mContext, "sIter", mCount);

                    Log.d("pre time", PreferenceManager.getString(mContext, "sTime"));
                    Log.d("pre receiver", PreferenceManager.getString(mContext, "sReceiver"));
                    Log.d("pre time", PreferenceManager.getInt(mContext, "sIter") + "");

                    Toast.makeText(PhoneCallActivity.this, "저장완료", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    // 타임 피커 시간을 받는 리스너
    TimePickerDialog.OnTimeSetListener mTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                    //사용자가 입력한 값을 가져온뒤
                    mHour = hourOfDay;
                    mMinute = minute;

                    //텍스트뷰의 값을 업데이트함
                    UpdateNow();
                }
            };



    // 타임피커 값으로 날짜를 업데이트 하는 함수
   void UpdateNow() {

        // mTxtDate.setText(String.format("%d/%d/%d", mYear, mMonth + 1, mDay));
        mTxtTime.setText(String.format("%d:%d", mHour, mMinute));

    }

}