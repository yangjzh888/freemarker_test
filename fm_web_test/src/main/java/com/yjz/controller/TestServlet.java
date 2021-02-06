package com.yjz.controller;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "TestServlet", value = "/TestServlet")
public class TestServlet extends HttpServlet {


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            // 创建Configuration实例,并调整设置
            // Configuration实例是存储FreeMarker应用级设置的核心部分
            // 处理创建和缓存预解析模板(比如Template对象)的工作
            // 只在应用(可能是servlet)生命周期的开始执行一次
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
            String path = TestServlet.class.getResource("/").toURI().getPath();
            cfg.setDirectoryForTemplateLoading(new File(path + "templates"));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

            // 创建数据模型
            Map<String, Object> root = new HashMap<>();
            root.put("user", "Big Joe");
            Map<String, Object> latest = new HashMap<>();
            root.put("latestProduct", latest);
            latest.put("url", "products/greenhouse.html");
            latest.put("name", "green house");

            // 获得模板
            Template temp = cfg.getTemplate("aa.ftl");

            // 合并模板和数据模型
            // 设置输出位置
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
             // 内容输出
            temp.process(root, out);

            out.flush();
            out.close();


        } catch (URISyntaxException | IOException | TemplateException e) {
            e.printStackTrace();
        }

//        response.setContentType("text/html");
//        PrintWriter out = response.getWriter();
//        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
//        out.println("<HTML>");
//        out.println("   <HEAD><TITLE>A Servlet</TITLE></HEAD>");
//        out.println("   <BODY>");
//        out.print("     This is ");
//        out.print(this.getClass());
//        out.println(", using the POST method");
//        out.println("   </BODY>");
//        out.println("</HTML>");
//        out.flush();
//        out.close();
    }

}
