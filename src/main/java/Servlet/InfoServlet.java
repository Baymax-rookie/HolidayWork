package Servlet;

import Been.Message;
import Service.MessageService;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

@WebServlet("/info")
public class InfoServlet extends HttpServlet {
    private static MessageService messageService;

    @Override
    public void init() throws ServletException {
        messageService = MessageService.getInstance();
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        List<Message> list = messageService.findAllMessage();
        String res = messageService.messageToJson(list);

        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        response.getOutputStream()
                )
        );
        writer.write(res);
        writer.flush();
        writer.close();
    }
}
