package com.gnetop.sdk.demo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gentop.ltgame.ltgamesdkcore.common.LTGameOptions;
import com.gentop.ltgame.ltgamesdkcore.common.LTGameSdk;
import com.gentop.ltgame.ltgamesdkcore.common.Target;
import com.gentop.ltgame.ltgamesdkcore.impl.OnRechargeListener;
import com.gentop.ltgame.ltgamesdkcore.manager.RechargeManager;
import com.gentop.ltgame.ltgamesdkcore.model.RechargeObject;
import com.gentop.ltgame.ltgamesdkcore.model.RechargeResult;
import com.gentop.ltgame.ltgamesdkcore.util.DeviceUtils;

import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Executors;


public class PayWallActivity extends AppCompatActivity {

    Button mBtnPay;
    TextView mTxtResult;
    String LTAppKey = "MJwk6bLlpGErRgLKkJPLP7VavHRGvTpA";
    String LTAppID = "28576";
    String packageName = "com.gnetop.sdk.demo";
    private String mGoodsID = "3";
    String productID = "czjj3";
    Map<String, Object> params = new WeakHashMap<>();
    private String mAdID;
    private String mProjectKey = "dce9216c0193425a4b35a20733ac58d0";
    private String mSecretKey = "177037759bb0a6fcb5454cd89bd6ae05";
    private double amount = 10;
    private String mCurrency = "USD";
    private String mItemName = "Super Fund";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_wall);
        initView();
    }

    private void initView() {
        params.put("key", "123");
        mTxtResult = findViewById(R.id.txt_pay_result);
        mBtnPay = findViewById(R.id.btn_pay);
        mBtnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RechargeObject result = new RechargeObject();
                result.setGoodsID(mGoodsID);
                result.setPayTest(0);
                result.setParams(params);
                result.setAmount(amount);
                result.setmCurrency(mCurrency);
                result.setmItemName(mItemName);
                result.setmProjectKey(mProjectKey);
                result.setmSecretKey(mSecretKey);

                RechargeManager.recharge(PayWallActivity.this, Target.RECHARGE_PAY_WALL,
                        result, mOnRechargeListener);
            }
        });

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    mAdID = DeviceUtils.getGoogleAdId(getApplicationContext());
                    if (!TextUtils.isEmpty(mAdID)) {
                        init();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void init() {
        LTGameOptions options = new LTGameOptions.Builder(this)
                .debug(true)
                .isServerTest(true)
                .appID(LTAppID)
                .appKey(LTAppKey)
                .payWall()
                .setAdID(mAdID)
                .packageID(packageName)
                .build();
        LTGameSdk.init(options);
    }

    OnRechargeListener mOnRechargeListener = new OnRechargeListener() {
        @Override
        public void onState(Activity activity, RechargeResult result) {

            switch (result.state) {
                case RechargeResult.STATE_RECHARGE_START:
                    mTxtResult.setText("开始支付");
                    break;
                case RechargeResult.STATE_RECHARGE_FAILED:
                    mTxtResult.setText(result.getErrorMsg());
                    break;
                case 1:
                    mTxtResult.setText("购买成功");
                    break;
                case 2:
                    mTxtResult.setText("错误");
                    break;
                case 3:
                    mTxtResult.setText("购买失败");
                    break;
                case 4:
                    mTxtResult.setText("处理中");
                    break;
                case 5:
                    mTxtResult.setText("取消购买");
                    break;
                case 6:
                    mTxtResult.setText("商务处理");
                    break;
            }
        }
    };
}
