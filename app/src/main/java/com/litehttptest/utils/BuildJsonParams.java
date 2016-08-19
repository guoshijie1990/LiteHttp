package com.litehttptest.utils;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by shijie.guo on 2015-11-13.
 */
public class BuildJsonParams {
    public static String Build(HashMap parmas) {
        Gson gson = new Gson();
        String params=gson.toJson(parmas);
        LogUtil.d(params);
        return params;
    }

    public static String BuildGetParam(HashMap parmas){
        StringBuffer stringBuffer=new StringBuffer();
        Iterator iter = parmas.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            stringBuffer.append(key);
            stringBuffer.append("=");
            stringBuffer.append(val);
            stringBuffer.append("&");
        }
        return  stringBuffer.substring(0,stringBuffer.length()-1);
    }



}
