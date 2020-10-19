package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Notice;

import java.util.List;

public interface NoticeDao {
    List<Notice> findAllNotice();
}
