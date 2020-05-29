package com.example.api_0527;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.api_0527.utils.ContentUtil;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {
//저장된 토큰이 있나 ? 자동 로그인이 체크됬나? => 메인으로 이동
//        둘중하나라도 틀리면 => 로그인으로 이동

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!ContentUtil.getLoginUserToken(mContext).equals("")&& ContentUtil.isAutoLogin(mContext)){
                    Intent intent = new Intent(mContext,MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(mContext,LoginActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        },2000);

    }
}
