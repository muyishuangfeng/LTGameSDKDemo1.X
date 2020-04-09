package com.gnetop.sdk.demo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gentop.ltsdk.common.model.ResultData;
import com.gentop.ltsdk.ltsdkui.impl.OnReLoginInListener;
import com.gentop.ltsdk.ltsdkui.impl.OnResultClickListener;
import com.gnetop.sdk.demo.manager.LoginEventManager;



public class UIActivity extends AppCompatActivity {

    Button mBtnLogin, mBtnLoginOut;
    TextView mTxtResult;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui);
        initView();
    }


    protected void initView() {
        mTxtResult = findViewById(R.id.txt_result);
        mBtnLogin = findViewById(R.id.btn_login);
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginEventManager.getInstance().uiLogin(UIActivity.this,true,false,mResultListener,mOnReLoginListener);
            }
        });
        mBtnLoginOut = findViewById(R.id.btn_loginOut);
        mBtnLoginOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               LoginEventManager.getInstance().uiLoginOut(UIActivity.this,true,true,mResultListener);

            }
        });
    }


    /**
     * 登录结果
     */
    OnResultClickListener mResultListener = new OnResultClickListener() {
        @SuppressLint("SetTextI18n")
        @Override
        public void onResult(ResultData result) {
            mTxtResult.setText("=====登录成功:\n" + result.toString());
            Log.e("TAG", result.toString());
        }
    };
    /**
     * 自动登陆
     */
    OnReLoginInListener mOnReLoginListener = new OnReLoginInListener() {

        @SuppressLint("SetTextI18n")
        @Override
        public void OnLoginResult(ResultData result) {
            mTxtResult.setText("OnReLoginInListener=====结果:\n" + result.toString());
            Log.e("TAG", result.toString());
        }
    };
}
