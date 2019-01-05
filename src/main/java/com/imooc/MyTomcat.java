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
    //定义一个端口 好的代码不是一蹴而就的 但是最好是在写第一行代码的时候 就是最好的
    private int port=8080;

    //这里的URLmapping是用来存请求的URL和对应的servlet的 因为一个Tomcat里面
    //是同时可以有多个host存在的 这里要区分的
    //我这里的实现的方式使用的是hashmap来存储对应的关系的
    //我看过有人把他们直接的封装了起来 ，然后的直接的使用list来存储的
    private Map<String,ServletClass> urlMapping = new HashMap<>();

    //这里是构造器的 含有端口的构造器
    public MyTomcat(Integer port){
        this.port=port;
    }

    //不含端口的构造器 默认的端口是8080
    public MyTomcat(){
        System.out.println("MyTomcat已启动");
    }

    //这个是主要的方法的 里面包含很多的有用的东西
    //就像springboot的run的里面的主要的功能
    //最开始的初始化都是子啊里面进行的 这个start就是相当于那个方法的
    public void start(ServletMappingConfig config){
        System.out.println("执行start");
        //初始化URLMaping的映射 从servletMappingconfig里面读取的， 我认为的
        //在项目启动的时候，Tomcat应该会解析web。xml和自己的web。xml 然后的对对应的形成获取
        //所有的servletmapping 然后的
        initUrlMapping(config);
        try {
            //这里是Tomact的核心地块了
            //这里的几行代码还是要背的
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

    //说起这个方法，我感觉还是很不错的 有自己的思想在里面的
    //虽然不具有通用型 但是要学会思考，因为
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
