package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.RouteDao;

import cn.itcast.travel.dao.RouteImgDao;
import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.dao.impl.RouteDaoImpl;

import cn.itcast.travel.dao.impl.RouteImgImpl;
import cn.itcast.travel.dao.impl.SellerDaoImpl;
import cn.itcast.travel.domain.*;
import cn.itcast.travel.service.RouteService;

import java.util.ArrayList;
import java.util.List;

public class RouteServiceImpl implements RouteService {
    private RouteDao routeDao = new RouteDaoImpl();
    private RouteImgDao routeImgDao = new RouteImgImpl();
    private SellerDao sellerDao = new SellerDaoImpl();
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();
    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize,String rname) {
         //封装PageBean
        PageBean<Route> pb = new PageBean<Route>();
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);

        //设置总记录数
        int totalCount = routeDao.findTotalCount(cid,rname);
        pb.setTotalCount(totalCount);

        //设置当前页显示的数据集合
        int start = (currentPage-1)*pageSize;
        List<Route> list = routeDao.findByPage(cid, start, pageSize,rname);
        pb.setList(list);

        //设置总页数
        int totalPage = totalCount%pageSize == 0? totalCount/pageSize:(totalCount/pageSize)+1;
        pb.setTotalPage(totalPage);

        return pb;
    }

    @Override
    public Route findOne(String rid) {

        Route route = routeDao.findOne(Integer.parseInt(rid));

        List<RouteImg> routeImgList = routeImgDao.findByRid(route.getRid());

        Seller seller = sellerDao.findBySid(route.getSid());

        route.setSeller(seller);
        route.setRouteImgList(routeImgList);

        int count = favoriteDao.findCountByRid(route.getRid());
        route.setCount(count);
        return route;
    }

    @Override
    public List<Route> findByUid(int uid) {
        List<Favorite> favorites = favoriteDao.findByUid(uid);
        List<Integer> list = new ArrayList<>();
        for (Favorite favorite : favorites) {
            list.add(favorite.getRid());
        }
        List<Route> routes = favoriteDao.findByFavorites(list);
        return routes;
    }

}
