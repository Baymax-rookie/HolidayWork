package Servlet;

import Been.Message;
import Service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

@WebServlet("/send")
public class SendServlet extends HttpServlet {
    private static final String ERROR = "{\"status\":\"10001\"}";

    private static final String OK = "{\"status\":\"10000\"}";

    private static MessageService messageService;
    @Override
    public void init() throws ServletException {
        messageService = MessageService.getInstance();
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

//        String username = request.getParameter("username");
//        String text = request.getParameter("text");
//        int pid = Integer.parseInt(request.getParameter("pid"));
//
//        Message message = new Message(username, text, pid);
//
//        String res = ERROR;

        HttpSession session = request.getSession(false);
        String text = request.getParameter("text");
        int pid = Integer.parseInt(request.getParameter("pid"));
        Message message = new Message();
        message.setPid(pid);
        message.setText(text);
        String res = ERROR;
        if(session != null) {
            String username = (String) session.getAttribute("username");
            message.setUsername(username);
            if (messageService.insertMessage(message)) {
                res = OK;
            }
        }
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(
                        response.getOutputStream()
                )
        );
        out.write(res);
        out.flush();
        out.close();
    }
}