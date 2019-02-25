package Been;

import java.util.List;

public class Message {
    private int id;
    private int pid;
    private String username;
    private String text;
    private List<Message> childContent;

    public void setId(int id) {
        this.id = id;
    }

    public void setChildContent(List<Message> childContent) {
        this.childContent = childContent;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public  void setUsername(String username) {
        this.username = username;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public int getPid() {
        return pid;
    }

    public String getUsername() {
        return username;
    }

    public String getText() {
        return text;
    }

    public List<Message> getChildContent() {
        return childContent;
    }


}
