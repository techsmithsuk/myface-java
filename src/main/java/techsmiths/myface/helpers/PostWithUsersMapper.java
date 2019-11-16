package techsmiths.myface.helpers;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import techsmiths.myface.models.dbmodels.PostWithUsers;
import techsmiths.myface.models.dbmodels.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PostWithUsersMapper implements RowMapper<PostWithUsers> {
    @Override
    public PostWithUsers map(ResultSet rs, StatementContext ctx) throws SQLException {
        PostWithUsers post = new PostWithUsers();

        post.setId(rs.getLong("post.id"));
        post.setSenderId(rs.getLong("post.sender_id"));
        post.setReceiverId(rs.getLong("post.receiver_id"));
        post.setMessage(rs.getString("post.message"));
        post.setImage(rs.getString("post.image"));
        post.setPostedAt(rs.getDate("post.posted_at"));
        post.setSender(createUser(rs, "sender"));
        post.setReceiver(createUser(rs, "receiver"));

        return post;
    }

    private User createUser(ResultSet rs, String tableAlias) throws SQLException {
        User user = new User();

        user.setId(rs.getLong(tableAlias + ".id"));
        user.setUsername(rs.getString(tableAlias + ".username"));
        user.setEmail(rs.getString(tableAlias + ".email"));
        user.setFirstName(rs.getString(tableAlias + ".first_name"));
        user.setLastName(rs.getString(tableAlias + ".last_name"));
        user.setProfileImage(rs.getString(tableAlias + ".profile_image"));
        user.setBannerImage(rs.getString(tableAlias + ".banner_image"));

        return user;
    }
}
