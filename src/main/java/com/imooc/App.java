package com.imooc;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("启动MyTomcat");
        MyTomcat myTomcat = new MyTomcat();
        ServletMappingConfig servletMappingConfig = new ServletMappingConfig();
        servletMappingConfig.init();
        myTomcat.start(servletMappingConfig);
    }
}
