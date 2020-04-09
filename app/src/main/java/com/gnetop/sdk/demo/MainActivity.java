package com.gnetop.sdk.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gnetop.sdk.demo.manager.LoginEventManager;
import com.gnetop.sdk.demo.util.ConvertUtil;


public class MainActivity extends AppCompatActivity {

    Button mBtnGoogle, mBtnFB, mBtnGP, mBtnGuest, mBtnQQ, mBtnPhone, mBtnOneStore, mBtnUI,
            mBtnDevice,mBtnGpInit,mBtnPayWall;
    TextView mTxtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        LoginEventManager.getInstance().init(this,true,true);
        LoginEventManager.getInstance().addOrder(MainActivity.this);
        //TODO:新增数据统计方法
        LoginEventManager.getInstance().uiStatsInit(this);

        mTxtResult = findViewById(R.id.txt_result);
        mBtnGuest = findViewById(R.id.btn_guest);
        mBtnGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GuestActivity.class));
            }
        });

        mBtnGoogle = findViewById(R.id.btn_google);
        mBtnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GoogleActivity.class));
            }
        });
        mBtnFB = findViewById(R.id.btn_fb);
        mBtnFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FacebookActivity.class));
            }
        });
        mBtnGP = findViewById(R.id.btn_gp);
        mBtnGP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GooglePlayActivity.class));
            }
        });
        mBtnOneStore = findViewById(R.id.btn_onestore);
        mBtnOneStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OneStoreActivity.class));
            }
        });
        mBtnPhone = findViewById(R.id.btn_phone);
        mBtnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PhoneActivity.class));
            }
        });
        mBtnQQ = findViewById(R.id.btn_QQ);
        mBtnQQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, QQActivity.class));

            }
        });
        mBtnUI = findViewById(R.id.btn_ui);
        mBtnUI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, UIActivity.class));
            }
        });

        mBtnGpInit = findViewById(R.id.btn_gp_init);
        mBtnGpInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // LoginEventManager.getInstance().addOrder(MainActivity.this);

            Log.e("TAG",
                    ConvertUtil.convert("77:42:AA:91:B2:B0:E4:B8:69:70:1C:A2:2D:A6:A5:B9:F0:50:7F:CF",true));

            }
        });
        mBtnPayWall = findViewById(R.id.btn_pay_wall);
        mBtnPayWall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PayWallActivity.class));
            }
        });


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        LoginEventManager.getInstance().uiUnRegister(this);
    }
}
