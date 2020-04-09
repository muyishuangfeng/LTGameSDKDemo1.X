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


public class GuestActivity extends AppCompatActivity {

    Button mBtnLogin, mBtnBind, mBtnFB;
    TextView mTxtResult;
    String TAG = "GuestActivity";
    private OnLoginStateListener mOnLoginListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);
        initView();
        initData();
    }

    private void initView() {
        //LoginEventManager.getInstance().guestInit(this, true, true);

        mTxtResult = findViewById(R.id.txt_result);
        mBtnLogin = findViewById(R.id.btn_login);
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginEventManager.getInstance().guestLogin(GuestActivity.this, "1",false,
                        mOnLoginListener);

            }
        });
        mBtnFB = findViewById(R.id.btn_bind_fb);
        mBtnFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginEventManager.getInstance().guestLogin(GuestActivity.this, "2", false,
                        mOnLoginListener);
            }
        });
        mBtnBind = findViewById(R.id.btn_bind);
        mBtnBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginEventManager.getInstance().guestLogin(GuestActivity.this, "3",false,
                        mOnLoginListener);
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
                            String mLtToken = result.getResultModel().getData().getLt_uid_token();
                            String mLtId = result.getResultModel().getData().getLt_uid();
                            mTxtResult.setText(mLtToken + "====" + mLtId + "====" + result.getResultModel().getCode());
                        }
                        break;
                    case LoginResult.STATE_LOGIN_OUT:
                        if (result.getError().getMsg() != null) {
                            Toast.makeText(GuestActivity.this, result.getError().getMsg(), Toast.LENGTH_SHORT).show();
                            mTxtResult.setText(result.getError().getMsg());
                        }
                        break;
                    case LoginResult.STATE_FAIL:
                        if (result.getError() != null) {
                            mTxtResult.setText(result.getError().getMsg() + "=====" + result.getError().getCode());
                            Log.e(TAG, result.getError().getMsg() + "=====" + result.getError().getCode());
                            switch (result.getError().getCode()) {
                                case LTGameError.CODE_PARAM_ERROR: {
                                    Log.e(TAG, result.getError().getMsg());
                                    break;
                                }
                                case LTGameError.CODE_REQUEST_ERROR: {
                                    Log.e("RESULT123", result.getError().getMsg());
                                    break;
                                }
                                case LTGameError.CODE_NOT_SUPPORT: {
                                    Log.e("RESULT", result.getError().getMsg());
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
