package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.FavoriteService;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.FavoriteServiceImpl;
import cn.itcast.travel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    private RouteService service = new RouteServiceImpl();
    private FavoriteService favoriteService = new FavoriteServiceImpl();

    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求数据
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");
        String rname = request.getParameter("rname");
        //转码.............................................................
//       if(rname!=null&&rname.length()>0&&"null".equals(rname)){
        rname = new String(rname.getBytes("iso-8859-1"), "utf-8");
//       }
        /**
         * 精髓啊   很nice
         */
        if ("null".equals(rname)) {
            rname = "";
            System.out.println("rname==void");
        } else {
            System.out.println("跑掉了");
        }


        int cid = 0;
        if (cidStr != null && cidStr.length() > 0 && !"null".equals(cidStr)) {
            cid = Integer.parseInt(cidStr);
        }
        int currentPage = 0; //当前页面，如果不传递，默认为第一页
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }
        int pageSize = 0;//显示条数，如果不传递，默认为5
        if (pageSizeStr != null && pageSizeStr.length() > 0) {
            pageSize = Integer.parseInt(pageSizeStr);
        } else {
            pageSize = 5;
        }
        //调用service查询PageBean对象
        PageBean<Route> pb = service.pageQuery(cid, currentPage, pageSize, rname);
        writeValue(pb, response);
    }

    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("rid");
        Route route = service.findOne(rid);
        writeValue(route, response);
    }
    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取线路id
        String rid = request.getParameter("rid");
        //2.获取当前登录的用户
        User user = (User)request.getSession().getAttribute("user");
        int uid;
        if(user==null){
            uid=0;
        }else{
            uid=user.getUid();
        }
        //3.调用FavoriteService查询是否收藏
        boolean favorite = favoriteService.isFavorite(rid, uid);
        //4.写回客户端
        writeValue(favorite, response);
    }
    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("rid");
        User user = (User)request.getSession().getAttribute("user");
        int uid;
        if(user==null){
            return;
        }else {
            uid = user.getUid();
        }
        favoriteService.add(rid,uid);

    }
}
