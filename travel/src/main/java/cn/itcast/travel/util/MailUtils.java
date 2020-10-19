package cn.itcast.travel.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public final class MailUtils {
    private static  final String USER = "852389033@qq.com";     //发件人称号，同邮箱地址
    private static  final String PASSWORD ="vefntzszbdbfbaif";  //如果是qq邮箱可以使用客户端授权码，或者登录密码

    /**
     *
     * @param to  收件人邮箱
     * @param text 邮件正文
     * @param title 标题
     * @return
     */
    public static boolean sendMail(String to, String text, String title){
        try {
            final Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.host", "smtp.qq.com");

            //发件人账号
            props.put("mail.user", USER);
            //发件人密码
            props.put("mail.password", PASSWORD);

            //构建授权信息，用于进行smtp进行身份验证
            Authenticator authenticator = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    String userName = props.getProperty("mail.user");
                    String password = props.getProperty("mail.password");
                    return new PasswordAuthentication(userName,password);
                }
            };
            Session mailSession = Session.getInstance(props, authenticator);

            MimeMessage message = new MimeMessage(mailSession);

            String username = props.getProperty("mail.user");
            InternetAddress form = new InternetAddress(username);
            message.setFrom(form);

            InternetAddress toAddress = new InternetAddress(to);
            message.setRecipient(Message.RecipientType.TO, toAddress);

            message.setSubject(title);

            message.setContent(text,"text/html;charset=utf-8");

            Transport.send(message);

            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        MailUtils.sendMail("18381134032@163.com", "你好，这是一封测试邮箱", "测试邮件");
        System.out.println("发送成功");
    }
}
