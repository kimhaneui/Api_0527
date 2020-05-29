package com.example.api_0527;

import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.api_0527.databinding.ActivityLoginBinding;
import com.example.api_0527.utils.ContentUtil;
import com.example.api_0527.utils.ServerUtil;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends BaseActivity {

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

        binding.signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(mContext,SignUpActivity.class);
                startActivity(myintent);
            }
        });
        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = binding.emailEdt.getText().toString();
                String password = binding.pwEdt.getText().toString();

//email : kj_cho@nepp.kr/pw:Test!123

                ServerUtil.postLogin(mContext, email, password, new ServerUtil.JsonResponseHandler() {
                    @Override
                    public void onResponse(JSONObject json) {
                        Log.d("JSON확인",json.toString());

                        try {
                            int code = json.getInt("code");
                            if(code==200){
                                Log.d("분석결과","로그인성공!");

                                JSONObject data = json.getJSONObject("data");
                                String token = data.getString("token");

                                ContentUtil.setLoginUserToken(mContext,token);
//                                로그인 성공시 자동로그인 사용 여부를 기록
                                ContentUtil.setIsAutoLogin(mContext,binding.autoLoginBox.isChecked());

                                binding.autoLoginBox.isChecked();
                                Intent myIntent = new Intent(mContext,MainActivity.class);
                                startActivity(myIntent);
                                finish();
                            }
                            else{
                                Log.d("분석결과","로그인실패!");

                                final String failreason = json.getString("message");

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(mContext,failreason,Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        });

    }

    @Override
    public void setValues() {

    }
}
