package com.imooc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * Description:
 *
 * @author 周启江
 * @ClassName: MyRequset
 * @date 2019/1/5 16:42
 */
public class MyRequset {
    //获取请求的URl
    private String url;

    //获取请求的方法
    private String method;

    public MyRequset(InputStream inputStream) throws IOException {
        String str="";
        int n=-1;
        byte[] bytes = new byte[1024];
        /*while ((n=inputStream.read(bytes))!=-1){
            String strs=new String(bytes,0,n);
            str=str.append(strs);
        }*/
        if((n=inputStream.read(bytes))>0){
            str=new String(bytes,0,n);
        }
        //inputStream.close();

        String httpHeader=str.split("\n")[0];
        System.out.println(str+"这个是string");
        System.out.println(httpHeader+"这个是httpHeader");

        /*Arrays.toString(httpHeader.split("\\s+"));
        Arrays.toString(httpHeader.split("\\s"));*/
        method= httpHeader.split("\\s")[0];
        url= httpHeader.split("\\s")[1];
        System.out.println(this);
    }
/*

    public void loadINputStream(InputStream inputStream) throws IOException {

    }
*/

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }

    @Override
    public String toString() {
        return "MyRequset{" +
                "url='" + url + '\'' +
                ", method='" + method + '\'' +
                '}';
    }
}
