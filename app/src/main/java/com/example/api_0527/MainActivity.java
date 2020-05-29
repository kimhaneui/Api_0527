package com.example.api_0527;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.example.api_0527.databinding.ActivityMainBinding;
import com.example.api_0527.datas.Topic;
import com.example.api_0527.datas.User;
import com.example.api_0527.utils.ServerUtil;

import org.json.JSONException;
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

                try {
                    int code= json.getInt("code");
                    if(code==200){
                        JSONObject data = json.getJSONObject("data");

                        JSONObject user = data.getJSONObject("user");

                        final User me = User.getUserFromJson(user);

                        JSONObject topic = data.getJSONObject("topic");
                        final Topic thisWeekTopic = Topic.getTopicFromJson(topic);


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                binding.nickNameTxt.setText(me.getNickName());
                                binding.emailTxt.setText(me.getEmail());

                                Glide.with(mContext).load(thisWeekTopic.getImageUrl()).into(binding.topicImg);
                                binding.topicTxt.setText(thisWeekTopic.getTitle());
                            }
                        });

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
