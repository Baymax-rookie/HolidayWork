package Service;

import Been.Message;
import Dao.MessageDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MessageService {
    private static class MessageHolder{
        private static  MessageService instance=new MessageService();
    }
    private MessageService(){}
    public static MessageService getInstance(){
        return MessageHolder.instance;
    }

      private static MessageDao messageDao=new MessageDao();

    public List<Message> findAllContentChild(Message content){
        List<Message> list = messageDao.findMessageInfoByPid(content.getId());
        for (Message message: list) {
            List<Message> childContent = findAllContentChild(message);
            message.setChildContent(childContent);
        }
        return list;
    }
    public List<Message> findAllMessage(){
        List<Message> list = messageDao.findMessageInfoByPid(0);
        for (Message message: list) {
            List<Message> childContent = findAllContentChild(message);
            message.setChildContent(childContent);
        }
        return list;
    }
    public String messageToJson(List<Message> messageInfos){
        JSONObject Msg = new JSONObject();
        List<Message> list = messageInfos;
        JSONArray data = new JSONArray();
        for (Message message: list) {
            JSONArray child = new JSONArray();
            JSONObject content = new JSONObject();
            content.put("id", message.getId());
            content.put("username", message.getUsername());
            content.put("text", message.getText());
            child = getChildJSON(message.getChildContent());
            content.put("child", child);
            data.add(content);
        }
        Msg.put("data", data);
        Msg.put("status",10001);

        return Msg.toString();
    }
    private JSONArray getChildJSON(List<Message> messageInfos){
        List<Message> list = messageInfos;
        JSONArray data = new JSONArray();
        if(list.size() == 0) return null;
        for(Message message: list){
            JSONObject content = new JSONObject();
            JSONArray child = new JSONArray();
            content.put("id", message.getId());
            content.put("username", message.getUsername());
            content.put("text", message.getText());
            child = getChildJSON(message.getChildContent());
            content.put("child", child);
            data.add(content);
        }
        return data;
    }
    public  boolean insertMessage(Message message){
        if(message.getUsername() != null && message.getText() != null){
            messageDao.messageInsert(message);
            return true;
        }
        return false;
    }
    public Message selectMessage(int id){
        return messageDao.messageSelect(id);
    }
    public void deleteMessage(int id){
        Message messageInfo = selectMessage(id);
        if(messageInfo == null){
            return;
        }
        List<Message> list = new ArrayList<>();
        list = findAllContentChild(messageInfo);
        for (Message message: list) {
            deleteMessage(message.getId());
            messageDao.messageDelete(message.getId());
        }
        messageDao.messageDelete(messageInfo.getId());
    }
}
