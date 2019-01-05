package com.imooc;

/**
 * Description:
 *
 * @author 周启江
 * @ClassName: ServletMapping
 * @date 2019/1/5 16:19
 */
public class ServletMapping {
    private String servletName;

    private String url;

    private String className;

    public ServletMapping(String servletName, String url, String className) {
        this.servletName = servletName;
        this.url = url;
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getServletName() {
        return servletName;
    }

    public void setServletName(String servletName) {
        this.servletName = servletName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
