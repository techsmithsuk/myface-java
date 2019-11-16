package techsmiths.myface.models.dbmodels;

import java.util.Date;
import java.util.List;

public class Post {
    private Long id;
    private Long senderId;
    private Long receiverId;
    private String message;
    private String image;
    private Date postedAt;

    public static List<String> getAllColumnNames() {
        return List.of(
                "id", "senderId", "receiverId", "message", "image", "postedAt"
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
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
