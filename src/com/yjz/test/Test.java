package com.yjz.test;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class Test {

    public static void main(String[] args) {
        try {
            // 创建并调整单例型的配置对象
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
            String path = Test.class.getResource("/").toURI().getPath();
            cfg.setDirectoryForTemplateLoading(new File(path + "templates"));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

            // 创建数据模型
            Map root = new HashMap<>();
            root.put("user", "Big Joe");
            Map latest = new HashMap();
            root.put("latestProduct", latest);
            latest.put("url", "products/greenhouse.html");
            latest.put("name", "green house");

            // 获得模板
            Template temp = cfg.getTemplate("aa.ftl");

            // 设置输出位置
            Writer out = new OutputStreamWriter(System.out);

            // 内容输出
            temp.process(root, out);


        } catch (URISyntaxException | IOException | TemplateException e) {
            e.printStackTrace();
        }

    }
}
