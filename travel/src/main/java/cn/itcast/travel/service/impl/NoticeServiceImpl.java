package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.NoticeDao;
import cn.itcast.travel.dao.impl.NoticeDaoImpl;
import cn.itcast.travel.domain.Notice;
import cn.itcast.travel.service.NoticeService;

import java.util.List;

public class NoticeServiceImpl implements NoticeService {
    NoticeDao noticeDao = new NoticeDaoImpl();
    @Override
    public List<Notice> findAllNotice() {
        return noticeDao.findAllNotice();
    }
}
