package com.gnetop.sdk.demo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gentop.ltgame.ltgamesdkcore.exception.LTGameError;
import com.gentop.ltgame.ltgamesdkcore.impl.OnLoginStateListener;
import com.gentop.ltgame.ltgamesdkcore.model.LoginResult;
import com.gnetop.sdk.demo.manager.LoginEventManager;


public class FacebookActivity extends AppCompatActivity {

    Button mBtnStart, mBtnLoginOut;
    TextView mTxtResult;
    String packageName = "com.gnetop.sdk.demo";
    String TAG = "FacebookActivity";
    private OnLoginStateListener mOnLoginListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_ui);
        initView();
        initData();
    }

    private void initView() {
        LoginEventManager.getInstance().fbInit(this, true, true);

        mTxtResult = findViewById(R.id.txt_result);
        mBtnLoginOut = findViewById(R.id.btn_loginOut);
        mBtnStart = findViewById(R.id.btn_start);
        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginEventManager.getInstance().fbLogin(FacebookActivity.this, false, mOnLoginListener);

            }
        });
        mBtnLoginOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginEventManager.getInstance().fbLogin(FacebookActivity.this, true, mOnLoginListener);

            }
        });
    }


    /**
     * 初始化数据
     */
    private void initData() {
        mOnLoginListener = new OnLoginStateListener() {
            @Override
            public void onState(Activity activity, LoginResult result) {
                switch (result.state) {
                    case LoginResult.STATE_SUCCESS:
                        if (result.getResultModel() != null) {
                            Log.e(TAG, result.getResultModel().toString());
                            mTxtResult.setText(result.getResultModel().toString());
                        }
                        break;
                    case LoginResult.STATE_LOGIN_OUT:
                        if (result.getError().getMsg() != null) {
                            Toast.makeText(FacebookActivity.this, result.getError().getMsg(), Toast.LENGTH_SHORT).show();
                            mTxtResult.setText(result.getError().getMsg());
                        }
                        break;
                    case LoginResult.STATE_FAIL:
                        if (result.getError() != null) {
                            switch (result.getError().getCode()) {
                                case LTGameError.CODE_PARAM_ERROR: {
                                    Log.e("RESULT123", result.getError().getMsg());
                                    break;
                                }
                                case LTGameError.CODE_REQUEST_ERROR: {
                                    Log.e("RESULT123", "CODE_REQUEST_ERROR" + result.getError().getMsg());
                                    break;
                                }
                                case LTGameError.CODE_NOT_SUPPORT: {
                                    Log.e("RESULT123", "CODE_NOT_SUPPORT" + result.getError().getMsg());
                                    break;
                                }
                            }
                        }
                        break;
                }
            }

        };
    }


}
