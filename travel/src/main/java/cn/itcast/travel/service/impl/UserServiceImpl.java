package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();
    @Override
    public boolean register(User user) {
        //1.根据用户名查询用户对象
        User userByUsername = dao.findUserByUsername(user.getUsername());
        if(userByUsername!=null){
            //数据库存在返回false
            return false;
        }
        //2.保存用户信息
        //2.1设置激活码，唯一字符串
        user.setCode(UuidUtil.getUuid());
        //2.2设置激活状态
        user.setStatus("N");
        dao.save(user);

        //3.1发送邮件，邮件正文？
        String content = "<a href='http://localhost/travel/user/active?code="+user.getCode()+"' >点击激活【黑马旅游网】</a>";
        MailUtils.sendMail(user.getEmail(), content, "激活邮件");
        return true;
    }

    @Override
    public boolean active(String code) {
        //1.根据激活码查询user对象
        User user = dao.findUserByCode(code);
        if(user!=null){
            dao.updateStatus(user);
            return true;
        }else{
            return false;
        }

        //2.根据返回的user对象修改激活状态


    }

    @Override
    public User login(User user) {
        return dao.findByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public void revise(int uid, User user) {
        dao.revise(uid, user);
    }
}
