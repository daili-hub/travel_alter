package cn.itcast.travel.service;

import java.util.*;
import cn.itcast.travel.domain.Forum;

public interface ForumService {
    List<Forum> findAllForum();
    void insertForum(Forum forum,int uid);
}
