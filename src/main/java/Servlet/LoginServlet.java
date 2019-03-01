package Servlet;

import Been.User;
import Service.LoginService;


import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private String OK = "{\"status\":\"10001\"}";
    private String ERROR = "{\"status\":\"10000\"}";

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        User user = new User(username, password);
        HttpSession session = null;
        String res=ERROR;
        if (LoginService.login(username,password)){
            request.setAttribute("message","登陆成功");
            res=OK;
            session = request.getSession();
            session.setAttribute("username", user.getUsername());

        }
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));
        writer.write(res);
        writer.flush();
        writer.close();
    }
    }

