package cn.itcast.travel.web.servlet;

import cn.itcast.travel.dao.ForumDao;
import cn.itcast.travel.domain.Forum;
import cn.itcast.travel.service.ForumService;
import cn.itcast.travel.service.impl.ForumServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/forumServlet")
public class ForumServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ForumService service = new ForumServiceImpl();
        List<Forum> forums = service.findAllForum();
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json,charset=utf-8");
        mapper.writeValue(response.getOutputStream(), forums);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
