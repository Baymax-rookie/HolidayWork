package Servlet;

import Service.SignUpService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;


public class signUpServlet extends HttpServlet {
    private String OK = "{\"status\":\"10001\"}";
    private String ERROR = "{\"status\":\"10000\"}";

@Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    String username=request.getParameter("username");
    String password=request.getParameter("password");
    String email=request.getParameter("email");
    String res=ERROR;
    if (SignUpService.signup(username,password,email)){
        res=OK;
    }
    BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));
    writer.write(res);
    writer.flush();
    writer.close();
}
}
