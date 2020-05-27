package com.example.api_0527.utils;

import android.content.Context;
import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ServerUtil {

    private static final String BASE_URL = "http://15.165.177.142";

    public  interface JsonResponseHandler {
        void onResponse(JSONObject json);
    }
    public static void postLogin(Context context, String email, String pw, final JsonResponseHandler handler){

//        안드로이드 앱이 클라이언트로서의 역할을 하도록 도와주는 객체
        OkHttpClient client = new OkHttpClient();

//        post메소드는  formBody에 필요한 데이터 첨부
        RequestBody requestBody = new FormBody.Builder()
                .add("email",email)
                .add("password",pw)
                .build();
//        API에 접근하기 위한 정보가 적혀있는 Request 변수를 만들자

        Request request = new Request.Builder()
                .url(BASE_URL+"/user")
                .post(requestBody)
//                헤더가 필요하다면 이시점에서 첨부
                .build();
//        client 를 이용해서 실제로 서버에 접근
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d("서버연결실패","로그인기능실패");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String body = response.body().string();
                Log.d("서버연결성공",body);

//                String body를 Jsonobject 로 변환
                try {
                    JSONObject jsonObject = new JSONObject(body);

//                    변환된 JSON을 액티비티에 전달 + 처리 실행.
                    if(handler !=null){
                        handler.onResponse(jsonObject);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
