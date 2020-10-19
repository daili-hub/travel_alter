package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FavoriteDaoImpl implements FavoriteDao {
    private JdbcTemplate template= new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public Favorite findByRidAndUid(int rid, int uid) {
        Favorite favorite = null;
        try {
            String sql = "select * from tab_favorite where rid = ? and uid = ?";
            favorite = template.queryForObject(sql, new BeanPropertyRowMapper<Favorite>(Favorite.class), rid, uid);
        } catch (DataAccessException e) {

        }
        return favorite;
    }

    @Override
    public int findCountByRid(int rid) {
        String sql = "select count(*) from tab_favorite where rid = ?";
        return template.queryForObject(sql, Integer.class,rid);
    }

    @Override
    public void add(int rid, int uid) {
        String sql = "Insert into tab_favorite values(?,?,?)";
        template.update(sql, rid,new Date(),uid);
    }

    @Override
    public List<Favorite> findByUid(int uid) {
        List<Favorite> favorites_list = null;
        try {
            String sql = "select * from tab_favorite where uid = ? ";
            favorites_list = template.query(sql, new BeanPropertyRowMapper<Favorite>(Favorite.class),uid);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return favorites_list;
    }

    @Override
    public List<Route> findByFavorites(List<Integer> list) {
        List<Route> routes = null;

        try {
            if(list.toArray().length>0) {
                String sql = "select * from tab_route where ";
                StringBuilder sb = new StringBuilder(sql);
                int length = list.toArray().length;
                for (int i = 0; i < length - 1; i++) {
                    sb.append("rid = ? or ");
                }
                sb.append(" rid = ? ");
//SELECT * FROM tab_route WHERE rid = 1 OR rid =2
                routes = template.query(sb.toString(), new BeanPropertyRowMapper<Route>(Route.class), list.toArray());
            }
        } catch (DataAccessException e) {

        }

        return routes;
    }
}
