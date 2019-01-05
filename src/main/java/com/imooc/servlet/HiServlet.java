package com.imooc.servlet;

import com.imooc.MyRequset;
import com.imooc.MyResponse;
import com.imooc.MySetvlet;

import java.io.IOException;

/**
 * Description:
 *
 * @author 周启江
 * @ClassName: HiServlet
 * @date 2019/1/5 16:24
 */
public class HiServlet extends MySetvlet {
    @Override
    public void doGet(MyRequset requset, MyResponse response) {
        try {
            response.writer("this is Hi POST");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(MyRequset requset, MyResponse responses) {
        try {
            responses.writer("this is Hi POST");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
