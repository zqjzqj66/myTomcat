package com.imooc;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author 周启江
 * @ClassName: ServletMappingConfig
 * @date 2019/1/5 16:20
 */
public class ServletMappingConfig {
    private List<ServletMapping> servletMappings =new ArrayList<>();

    public  void  init(){
        servletMappings.add(new ServletMapping("hello","/hello","com.imooc.servlet.HelloServlet"));
        servletMappings.add(new ServletMapping("hi","/hi","com.imooc.servlet.HiServlet"));
    }

    public List<ServletMapping> getServletMappings() {
        return servletMappings;
    }
}
