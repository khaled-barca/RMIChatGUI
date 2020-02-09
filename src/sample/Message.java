package sample;

import java.io.Serializable;

public class Message implements Serializable {
    String sender;
    String content;
    String imagePath;

    public Message() {

    }

    public Message(String sender, String content, String imagePath) {
        this.sender = sender;
        this.content = content;
        this.imagePath = imagePath;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
