package com.boot.mytt.core.util;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
 
@WebServlet("/fileUpload")
@MultipartConfig(location="E://temp",fileSizeThreshold=1024*100)
public class FileUploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            Part part = req.getPart("headImg");
            part.write("E://" + part.getSubmittedFileName());
            System.out.println("睡前...");
            Thread.sleep(3000);
            // 在此中间时间去观察临时目录 
            System.out.println("睡后...");
        } catch (Exception e) {
            req.setAttribute("msg", "异常信息");
            req.getRequestDispatcher("/upload.jsp").forward(req, resp);
        }
    }
}