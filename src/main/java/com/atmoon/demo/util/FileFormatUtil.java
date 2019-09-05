package com.atmoon.demo.util;

import com.artofsolving.jodconverter.DefaultDocumentFormatRegistry;
import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.DocumentFormat;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * 文件类型转换
 *
 * @Author: zy
 */
public class FileFormatUtil {

    private static final String HOME = "C:/Program Files (x86)/OpenOffice 4/";
    private static final String COMMAND = "program\\\\soffice.exe -headless -accept=\\\"socket,HOST=127.0.0.1,port=8100;urp;StarOffice.ServiceManager\\\" -nofirststartwizard";
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8100;

    public static InputStream wordToPdf(InputStream wordStream) {
        Process pro = null;
        OpenOfficeConnection connection = new SocketOpenOfficeConnection(HOST, PORT);
        try {
            // 先尝试连接，失败时启动服务
            try {
                connection.connect();
            } catch (Exception e) {
                // 启动OpenOffice的服务
                String command = HOME + COMMAND;
                pro = Runtime.getRuntime().exec(command);
                // 等待3s
                Thread.sleep(3000);
                // 重新连接
                connection.connect();
            }
            //设置转换前后文件类型
            DefaultDocumentFormatRegistry formatRegistry = new DefaultDocumentFormatRegistry();
            DocumentFormat doc = formatRegistry.getFormatByFileExtension("doc");
            DocumentFormat pdf = formatRegistry.getFormatByFileExtension("pdf");
            //定义输出流
            ByteArrayOutputStream pdfStream = new ByteArrayOutputStream();
            //进行转换
            DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
            converter.convert(wordStream, doc, pdfStream, pdf);
            //把pdf流转成输入流
            InputStream pdfInput = new BufferedInputStream(new ByteArrayInputStream(pdfStream.toByteArray()));
            pdfStream.flush();
            pdfStream.close();
            return pdfInput;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            connection.disconnect();
            if (pro != null) {
                pro.destroy();
            }
        }
    }

}
