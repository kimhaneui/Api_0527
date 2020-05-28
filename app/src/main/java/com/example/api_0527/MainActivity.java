package com.example.api_0527;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;

import com.example.api_0527.databinding.ActivityMainBinding;
import com.example.api_0527.utils.ServerUtil;

import org.json.JSONObject;

public class MainActivity extends BaseActivity {

    ActivityMainBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        setValues();
        setupEvents();
    }

    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {

        ServerUtil.getRequestMainInfo(mContext, new ServerUtil.JsonResponseHandler(){

            @Override
            public void onResponse(JSONObject json) {
                Log.d("화면응답",json.toString());
            }
        });
    }
}
