package com.example.api_0527;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.example.api_0527.databinding.ActivityLoginBinding;
import com.example.api_0527.databinding.ActivitySignUpBinding;

public class SignUpActivity extends BaseActivity {

    ActivitySignUpBinding binding;

//    응용문제
//    비번은 타이핑 할때 마다 길이 검사
//    =>0글자 : 비밀번호를 입력하세요
//    =>8글자 미만 : 비밀번호가 너무 짧습니다
//    그이상 : 사용해도 좋은 비밀번호 입니다

//    비번확인도 타이핑할때마다 검사
//    => 0글자 : 비밀번호 확인을 입력해주세요
//    =>비번과 같다 : 비밀번호 재입력이 확인 되었습니다
//    => 다르다 : 비밀번호가 서로 다릅니다.

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up);
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

       binding.pwEdt.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {
               checkpasswords();
           }

           @Override
           public void afterTextChanged(Editable s) {

           }
       });
       binding.pwRepeat.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {
               checkpasswords();

          }

//          응용문제 => 비번/비번확인중 어느것을 타이핑해도 매번 둘다 검사
           @Override
           public void afterTextChanged(Editable s) {

           }
       });
    }
    void checkpasswords(){
        String pw = binding.pwEdt.getText().toString();
        if (pw.length()==0){
            binding.pwCheckTxt.setText("비밀번호를 입력해주세요");
        }
        else if(pw.length()<8){
            binding.pwCheckTxt.setText("비밀번호가 너무 짧습니다");
        }
        else{
            binding.pwCheckTxt.setText("사용해도 좋은 비밀번호 입니다");
        }
        String pwRepeat = binding.pwRepeat.getText().toString();

        if (pwRepeat.length()==0){
            binding.pwRepeatCheckTxt.setText("비밀번호 확인을 입력해주세요");
        }
        else if(pwRepeat.equals(binding.pwEdt.getText().toString())){
            binding.pwRepeatCheckTxt.setText("비밀번호가 재입력이 확인되었습니다");
        }
        else{
            binding.pwRepeatCheckTxt.setText("비밀번호가 서로 다릅니다다");
        }
    }

    @Override
    public void setValues() {

    }
}
