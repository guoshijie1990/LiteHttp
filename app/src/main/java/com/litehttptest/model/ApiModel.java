package com.litehttptest.model;

/**
 * @author MaTianyu @http://litesuits.com
 * @date 2015-10-03
 */
public class ApiModel<T> extends BaseModel {

    public int code;
    public String message;

    /**
     * 变化的部分：使用泛型，数据类型的确认延迟到子类里。
     */
    public T data;

    public int getCode() {
        return code;
    }

    public ApiModel setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ApiModel setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public ApiModel setData(T data) {
        this.data = data;
        return this;
    }

}
