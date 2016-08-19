package com.litehttptest.user.param;

import com.litehttptest.user.UserData;
import com.litehttptest.utils.BuildJsonParams;
import com.litesuits.http.annotation.HttpCacheExpire;
import com.litesuits.http.annotation.HttpCacheMode;
import com.litesuits.http.annotation.HttpMethod;
import com.litesuits.http.annotation.HttpUri;
import com.litesuits.http.request.content.HttpBody;
import com.litesuits.http.request.content.JsonBody;
import com.litesuits.http.request.param.CacheMode;
import com.litesuits.http.request.param.HttpMethods;
import com.litesuits.http.request.param.HttpRichParamModel;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by shijie.guo on 2016-7-27.
 */

@HttpUri("/user/login")
@HttpMethod(HttpMethods.Post)
@HttpCacheMode(CacheMode.CacheFirst)
@HttpCacheExpire(value = 1, unit =TimeUnit.HOURS)
//@HttpCacheExpire(value = 10, unit = TimeUnit.SECONDS)
public class UserParam extends HttpRichParamModel<UserData> {

    private String username;
    private String password;
    private String imei;

    public UserParam(String username, String password, String imei) {
        this.username = username;
        this.password = password;
        this.imei = imei;
    }

    @Override
    protected HttpBody createHttpBody() {
        HashMap<String, String> parmas = new HashMap<>();
        parmas.put("username", username);
        parmas.put("password", password);
        parmas.put("imei", imei);
        return new JsonBody(BuildJsonParams.Build(parmas));
    }
}
