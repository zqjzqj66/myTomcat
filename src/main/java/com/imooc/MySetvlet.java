package com.imooc;

/**
 * Description:
 *
 * @author 周启江
 * @ClassName: MySetvlet
 * @date 2019/1/5 17:20
 */
public abstract class MySetvlet {

    public abstract void doGet(MyRequset requset,MyResponse response);
    public abstract void doPost(MyRequset requset,MyResponse responses);
}
