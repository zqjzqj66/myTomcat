package com.imooc;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Description:
 *
 * @author 周启江
 * @ClassName: MyResponse
 * @date 2019/1/5 16:41
 */
public class MyResponse {
    private OutputStream outputStream;
    public MyResponse(OutputStream outputStream){
        this.outputStream=outputStream;
    }

    public void writer(String context) throws IOException {
        StringBuilder str=new StringBuilder();

        str.append("HTTP/1.1 200 OK\n")
                .append("Context-Type: text/html\n")
                .append("\r\n")
                .append("<html><body>")
                .append(context)
                .append("</body></html>");
        outputStream.write(str.toString().getBytes());
        outputStream.close();
    }

}
