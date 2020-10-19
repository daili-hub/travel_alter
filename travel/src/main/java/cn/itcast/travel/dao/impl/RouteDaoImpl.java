package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public int findTotalCount(int cid,String rname) {
        //String sql = "select count(*) from tab_route where cid = ? like %?% ";
//        String sql = "select count(*) from tab_route where cid = ?  ";
//        return template.queryForObject(sql, Integer.class,cid);
        String sql = "select count(*) from tab_route where 1 =1 ";

        StringBuilder sb = new StringBuilder(sql);
        List params = new ArrayList();
//        List<Object> list = new ArrayList<Object>();
        if(cid!=0){
            sb.append(" and cid = ? ");
            params.add(cid);  //添加？对应的值
        }
        if(rname!=null&&rname.length()>0){
            sb.append(" and rname like ? ");
            params.add("%"+rname+"%");  //这里注意了。。。。。。。。。。。。。。。。。。。。。。
        }
//        sb.append(" limit ? , ? ");
        sql = sb.toString();
//        list.add(start);
//        list.add(pageSize);


        return template.queryForObject(sql,Integer.class,params.toArray());
    }

    @Override
    public List<Route> findByPage(int cid, int start, int pageSize,String rname) {
        // String sql = "select * from tab_route where cid = ? and like rname = ? limit ? , ? ";
//        String sql = "select * from tab_route where cid = ? limit ? , ?  ";
//
//        return template.query(sql, new BeanPropertyRowMapper<Route>(Route.class),cid,start,pageSize);
        // String sql = "select * from tab_route where cid = ? and like rname = ? limit ? , ? ";
        String sql = "select * from tab_route where 1 =1 ";

        StringBuilder sb = new StringBuilder(sql);
        List params = new ArrayList<>();
        if(cid!=0){
            sb.append(" and cid = ? ");
            params.add(cid);  //添加？对应的值
        }
        if(rname!=null&&rname.length()>0){
            sb.append(" and rname like ? ");
            params.add("%"+rname+"%");  //这里注意了。。。。。。。。。。。。。。。。。。。。。。
        }
        sb.append(" limit ? , ? ");
        sql = sb.toString();
        params.add(start);
        params.add(pageSize);


        return template.query(sql, new BeanPropertyRowMapper<Route>(Route.class),params.toArray());
    }

    @Override
    public Route findOne(int rid) {
        String sql = "select * from tab_route where rid=?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<Route>(Route.class),rid);
    }


}
