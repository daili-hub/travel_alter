package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.ForumDao;
import cn.itcast.travel.domain.Forum;
import cn.itcast.travel.util.JDBCUtils;
import com.alibaba.druid.util.JdbcUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ForumDaoImpl implements ForumDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Forum> findAllForum() {
        String sql = "select * from tab_forum order by fid Desc";
        List<Forum> forums = template.query(sql, new BeanPropertyRowMapper<Forum>(Forum.class));
        return forums;
    }

    @Override
    public void insertForum(Forum forum) {
        String sql = "insert into tab_forum(title,content,uid,date) values(?,?,?,?)";
        template.update(sql, forum.getTitle(),forum.getContent(),forum.getUid(),forum.getDate());
    }
}
