package com.example.api_0527;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.example.api_0527.databinding.ActivityLoginBinding;
import com.example.api_0527.utils.ServerUtil;

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

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = binding.emailEdt.getText().toString();
                String password = binding.pwEdt.getText().toString();

                ServerUtil.postLogin(mContext,email,password,null);
            }
        });

    }

    @Override
    public void setValues() {

    }
}
