package com.imooc;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 *
 * @author 周启江
 * @ClassName: MyTomcat
 * @date 2019/1/5 16:13
 */
public class MyTomcat {
    //定义一个端口
    private int port=8080;

    private Map<String,ServletClass> urlMapping = new HashMap<>();

    public MyTomcat(Integer port){
        this.port=port;
    }

    public MyTomcat(){
        System.out.println("MyTomcat已启动");
    }

    public void start(ServletMappingConfig config){
        System.out.println("执行start");
        initUrlMapping(config);

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while(true){
                Socket accept = serverSocket.accept();
                InputStream inputStream = accept.getInputStream();
                OutputStream outputStream = accept.getOutputStream();
                MyRequset myRequset = new MyRequset(inputStream);
                MyResponse myResponse = new MyResponse(outputStream);
                dispacter(myRequset,myResponse);
                accept.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void dispacter(MyRequset myRequset,MyResponse myResponse){
        ServletClass s = urlMapping.get(myRequset.getUrl());
        String method = myRequset.getMethod();
        if(method.equals("POST")){
            Method doPost = s.getDoPost();
            try {
                doPost.invoke(s.getObj(),myRequset,myResponse);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println(s);
            System.out.println(myRequset.getUrl());
            Method doGet = s.getDoGet();
            try {
                doGet.invoke(s.getObj(),myRequset,myResponse);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        System.out.println("dis执行完成");
    }

    public void initUrlMapping(ServletMappingConfig config)  {
        for (ServletMapping servletMapping:config.getServletMappings()) {
            try {
                Class<?> aClass = Class.forName(servletMapping.getClassName());
                Method doGet = aClass.getDeclaredMethod("doGet",new Class[]{MyRequset.class,MyResponse.class});
                Method doPost = aClass.getDeclaredMethod("doPost",new Class[]{MyRequset.class,MyResponse.class});
                ServletClass servletClass = new ServletClass(doGet, doPost, (MySetvlet)aClass.newInstance());
                urlMapping.put(servletMapping.getUrl(),servletClass);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("initUrlMapping执行完成");
    }


}
