package cn.itcast.travel.service.impl;
import cn.itcast.travel.dao.ForumDao;
import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.ForumDaoImpl;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.Forum;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.ForumService;

import java.util.Date;
import java.util.List;

public class ForumServiceImpl implements ForumService {
    ForumDao forumDao = new ForumDaoImpl();
    UserDao userDao = new UserDaoImpl();
    @Override
    public List<Forum> findAllForum() {
        List<Forum> forums= forumDao.findAllForum();

        for (Forum forum : forums) {

            User user = userDao.findUserById(forum.getUid());
            forum.setUsernames(user.getName());
        }
        System.out.println(forums);
        return forums;
    }

    @Override
    public void insertForum(Forum forum,int uid) {
        Date date = new Date();
        forum.setDate(date);
        forum.setUid(uid);
        forumDao.insertForum(forum);
    }


}
