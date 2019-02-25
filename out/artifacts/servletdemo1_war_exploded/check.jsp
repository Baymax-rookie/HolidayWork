<%--
  Created by IntelliJ IDEA.
  User: 12589
  Date: 2019/2/12
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>检查是否登录成功</title>
</head>
<body>
<%
    String username=request.getParameter("username");
    String password=request.getParameter("pwd");
    if (username.equals("hj")&password.equals("123456")){
        request.getRequestDispatcher("success.jsp").forward(request,response);
        %><%}else {
            %><%=" 用户名或密码有误"%><%
}

%>
</body>
</html>
