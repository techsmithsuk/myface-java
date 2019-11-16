package techsmiths.myface.services;

import org.jdbi.v3.core.mapper.reflect.BeanMapper;
import org.springframework.stereotype.Service;
import techsmiths.myface.helpers.Pagination;
import techsmiths.myface.models.apiModels.CreatePostModel;
import techsmiths.myface.models.dbmodels.Post;
import techsmiths.myface.models.dbmodels.PostWithUsers;
import techsmiths.myface.models.dbmodels.Receiver;
import techsmiths.myface.models.dbmodels.Sender;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class PostService extends DatabaseService {

    public List<PostWithUsers> getAllPosts(Pagination pagination) {
        return jdbi.withHandle(handle ->
                new ArrayList<>(handle.createQuery("" +
                            "SELECT " +
                                selectAllPostColumns("post") +
                                selectAllUserColumns("sender") +
                                selectAllUserColumns("receiver") +
                            "FROM posts as post " +
                            "JOIN users as sender on post.sender_user_id = sender.id " +
                            "JOIN users as receiver on post.receiver_user_id = receiver.id " +
                            "ORDER BY post.posted_at DESC " +
                            "LIMIT :limit " +
                            "OFFSET :offset")
                        .bind("limit", pagination.getLimit())
                        .bind("offset", pagination.getOffset())
                        .registerRowMapper(BeanMapper.factory(Post.class, "post"))
                        .registerRowMapper(BeanMapper.factory(Sender.class, "sender"))
                        .registerRowMapper(BeanMapper.factory(Receiver.class, "receiver"))
                        .reduceRows(new LinkedHashMap<Integer, PostWithUsers>(), (map, row) -> {
                            PostWithUsers post = map.computeIfAbsent(
                                    row.getColumn("post_id", Integer.class), id -> row.getRow(PostWithUsers.class)
                            );

                            post.setSender(row.getRow(Sender.class));
                            post.setReceiver(row.getRow(Receiver.class));

                            return map;
                        })
                        .values())
        );
    }

    public void createPost(CreatePostModel post) {
        jdbi.withHandle(handle ->
                handle.createUpdate(
                        "INSERT INTO posts " +
                                "(sender_user_id, receiver_user_id, message, image, posted_at) " +
                                "VALUES " +
                                "(:senderUserId, :receiverUserId, :message, :image NOW())")
                        .bind("senderUserId", post.getSenderId())
                        .bind("receiverUserId", post.getReceiverId())
                        .bind("message", post.getMessage())
                        .bind("image", post.getImage())
                        .execute()
        );
    }

    public int countAllPosts() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT COUNT(*) FROM posts")
                        .mapTo(Integer.class)
                        .one()
        );
    }
}
