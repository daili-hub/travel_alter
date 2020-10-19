package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.NoticeDao;
import cn.itcast.travel.domain.Notice;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class NoticeDaoImpl implements NoticeDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Notice> findAllNotice() {
        String sql = "select * from tab_notice order by id";
        List<Notice> notices = template.query(sql, new BeanPropertyRowMapper<Notice>(Notice.class));
        return notices;
    }
}
