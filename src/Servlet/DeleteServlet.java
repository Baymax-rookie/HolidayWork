package Servlet;

import Been.Message;
import Service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;


public class DeleteServlet extends HttpServlet {
        private MessageService messageService;
        private String OK = "{\"status\":\"10001\"}";
        private String ERROR = "{\"status\":\"10000\"}";

        @Override
        public void init() throws ServletException {
            messageService = MessageService.getInstance();
        }

        public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
            int id = Integer.parseInt(request.getParameter("id"));
            HttpSession session = request.getSession(false);
            Message messageInfo = messageService.selectMessage(id);
            String test = (String) session.getAttribute("username");
            String res = ERROR;
            if(session != null){
                if(test.equals(messageInfo.getUsername())) {
                    messageService.deleteMessage(id);
                    res = OK;
                }
            }
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));
            writer.write(res);
            writer.flush();
            writer.close();
        }
    }

