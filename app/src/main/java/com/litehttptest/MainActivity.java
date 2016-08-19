package com.litehttptest;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.litehttptest.user.UserData;
import com.litehttptest.user.param.UserParam;
import com.litehttptest.utils.DeviceInfo;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.listener.HttpListener;
import com.litesuits.http.request.AbstractRequest;
import com.litesuits.http.request.param.HttpRichParamModel;
import com.litesuits.http.response.Response;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.login_btn)
    Button loginBtn;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.cache_textView)
    TextView cacheTextView;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("请稍后");
        StartNetwork();
    }


    @OnClick({R.id.login_btn})
    public void OnClick(View view) {
        if (view.getId() == R.id.login_btn) {
            StartNetwork();
        }
    }

    HttpListener httpListener=new HttpListener<UserData>() {
        @Override
        public void onStart(AbstractRequest<UserData> request) {
            super.onStart(request);
            progressDialog.show();
        }

        @Override
        public void onCacheSuccess(UserData userData, Response<UserData> response) {
            super.onCacheSuccess(userData, response);
            cacheTextView.setText("缓存" + "\n" + userData.getData().getUserName());
        }

        @Override
        public void onSuccess(UserData userData, Response<UserData> response) {
            super.onSuccess(userData, response);
            progressDialog.dismiss();
            if (userData.getCode() == 1) {
                textView.setText("非缓存" + "\n" + userData.getData().getUserName());
            } else {
                textView.setText(userData.getMessage());
            }
        }

        @Override
        public void onFailure(HttpException e, Response<UserData> response) {
            super.onFailure(e, response);
            textView.setText("失败");
            progressDialog.dismiss();
        }

        @Override
        public void onCancel(UserData userData, Response<UserData> response) {
            super.onCancel(userData, response);
            progressDialog.dismiss();
        }
    };

    public void StartNetwork() {
        HttpRichParamModel httpRichParamModel = new UserParam("18612936823", "123456", DeviceInfo.getInstance(this).getDiu()).setHttpListener(httpListener);
        RequestControl.getLiteHttp().executeAsync(httpRichParamModel, TAG);
//            RequestControl.getLiteHttp().cancelAll(TAG);
    }
}
