package cn.itcast.travel.dao;

import java.util.List;
import cn.itcast.travel.domain.Forum;

public interface ForumDao {
    List<Forum> findAllForum();
    void insertForum(Forum forum);
}
