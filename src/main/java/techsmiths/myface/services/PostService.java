package techsmiths.myface.services;

import org.jdbi.v3.core.mapper.reflect.BeanMapper;
import org.springframework.stereotype.Service;
import techsmiths.myface.models.dbmodels.Post;
import techsmiths.myface.models.dbmodels.Receiver;
import techsmiths.myface.models.dbmodels.Sender;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService extends DatabaseService {

    public List<Post> getAllPosts(int limit, int offset) {
        return jdbi.withHandle(handle ->
                new ArrayList<>(handle.createQuery("" +
                            "SELECT * FROM posts as post " +
                            "JOIN users as sender on posts.sender_user_id = user.id " +
                            "JOIN users as receiver on posts.receiver_user_id = user.id " +
                            "LIMIT :limit OFFSET :offset")
                        .bind("limit", limit)
                        .bind("offset", offset)
                        .registerRowMapper(BeanMapper.factory(Post.class, "post"))
                        .registerRowMapper(BeanMapper.factory(Sender.class, "sender"))
                        .registerRowMapper(BeanMapper.factory(Receiver.class, "receiver"))
                        .reduceRows(new LinkedHashMap<Integer, Post>(), (map, row) -> {
                            Post post = map.computeIfAbsent(
                                    row.getColumn("post.id", Integer.class), id -> row.getRow(Post.class)
                            );

                            post.setSender(row.getRow(Sender.class));
                            post.setSender(row.getRow(Receiver.class));

                            return map;
                        })
                        .values())
        );
    }

    public void createPost(Post post) {
        jdbi.withHandle(handle ->
                handle.createUpdate(
                        "INSERT INTO posts " +
                                "(sender_user_id, receiver_user_id, message, image) " +
                                "VALUES " +
                                "(:senderUserId, :receiverUserId, :message, :image)")
                        .bind("senderUserId", post.getSender().getId())
                        .bind("receiverUserId", post.getReceiver().getId())
                        .bind("message", post.getMessage())
                        .bind("image", post.getImage())
                        .execute()
        );
    }
}
