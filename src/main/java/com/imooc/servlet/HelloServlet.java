package com.imooc.servlet;

import com.imooc.MyRequset;
import com.imooc.MyResponse;
import com.imooc.MySetvlet;

import java.io.IOException;

/**
 * Description:
 *
 * @author 周启江
 * @ClassName: HelloServlet
 * @date 2019/1/5 16:23
 */
public class HelloServlet  extends MySetvlet{
    @Override
    public void doGet(MyRequset requset, MyResponse response) {
        try {
            response.writer("this is Hello GET");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(MyRequset requset, MyResponse response) {
        try {
            response.writer("this is Hello POST");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
