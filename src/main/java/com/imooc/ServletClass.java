package com.imooc;

import java.lang.reflect.Method;

/**
 * Description:
 *
 * @author 周启江
 * @ClassName: ServletClass
 * @date 2019/1/5 17:33
 */
public class ServletClass {

    private Method doGet;

    private Method doPost;

    private MySetvlet obj;

    public ServletClass(Method doGet, Method doPost, MySetvlet obj) {
        this.doGet = doGet;
        this.doPost = doPost;
        this.obj = obj;
    }

    public Method getDoGet() {
        return doGet;
    }

    public Method getDoPost() {
        return doPost;
    }

    public MySetvlet getObj() {
        return obj;
    }
}
