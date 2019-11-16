package techsmiths.myface.models.dbmodels;

import java.util.Date;
import java.util.List;

public class Post {
    private int id;
    private User sender;
    private User receiver;
    private String message;
    private String image;
    private Date postedAt;

    public static List<String> getAllColumnNames() {
        return List.of(
                "id", "sender", "receiver", "message", "image", "postedAt"
        );
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(Date postedAt) {
        this.postedAt = postedAt;
    }
}
