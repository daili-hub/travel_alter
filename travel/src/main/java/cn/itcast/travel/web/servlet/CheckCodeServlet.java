package cn.itcast.travel.web.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int withd = 100;
        int height = 50;
        //1.创建一对象，在内存中图片（验证码图片对象）
        BufferedImage image= new BufferedImage(withd,height, BufferedImage.TYPE_3BYTE_BGR);

        //2.美化图片
        //2.1填充背景色
        Graphics graphics = image.getGraphics();
        graphics.setColor(Color.pink);          //设置画笔颜色
        graphics.fillRect(0, 0, withd, height);

        //2.2画边框
        graphics.setColor(Color.blue);
        graphics.drawRect(0, 0, withd-1, height-1);

        //2.3写验证码
        //2.3.1生成随机字符
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(str.length());
            char c = str.charAt(index);
            graphics.drawString(c+"", withd/5*i+5, 25);
            sb.append(c);
        }
        String checkCode_session = sb.toString();
        request.getSession().setAttribute("checkCode_session",checkCode_session);
        //2.4画干扰线
        graphics.setColor(Color.green);
        for (int i = 0; i < 10; i++) {
            int x1 = random.nextInt(withd);
            int x2 = random.nextInt(withd);
            int y1 = random.nextInt(height);
            int y2 = random.nextInt(height);
            graphics.drawLine(x1, y1, x2, y2);
        }

        //3.将图片输出到页面显示
        ImageIO.write(image, "jpg", response.getOutputStream());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
