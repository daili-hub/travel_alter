package cn.itcast.travel.service;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

import java.util.List;

public interface RouteService {
    /**
     * 根据类别分页查询
     * @param cid
     * @param currentPage
     * @param pageSize
     * @param
     * @return
     */
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize,String rname);

    public Route findOne(String rid);

    List<Route> findByUid(int uid);
}
