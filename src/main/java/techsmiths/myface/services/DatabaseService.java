package techsmiths.myface.services;

import org.jdbi.v3.core.Jdbi;
import techsmiths.myface.models.dbmodels.Post;

import java.util.List;
import java.util.stream.Collectors;

public abstract class DatabaseService {
    protected final Jdbi jdbi;

    protected DatabaseService() {
        String hostname = "localhost";
        String databaseName = System.getenv("DB_NAME");
        String port = "3306";
        String username = System.getenv("DB_USERNAME");;
        String password = System.getenv("DB_PASSWORD");;

        String connectionString = "jdbc:mysql://" + hostname +
                ":" + port +
                "/" + databaseName +
                "?user=" + username +
                "&password=" + password +
                "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT&useSSL=false&allowPublicKeyRetrieval=true";

        jdbi = Jdbi.create(connectionString);
    }

    private String selectColumns(List<String> columns, String table_alias) {
        return columns.stream()
                .map(column -> String.format("%s.%s as %s_%s ", table_alias, column, table_alias, column))
                .collect(Collectors.joining());
    }

    protected String selectAllPostColumns(String posts_alias) {
        return selectColumns(Post.getAllColumnNames(), posts_alias);
    }

    protected String selectAllUserColumns(String user_alias) {
        return selectColumns(Post.getAllColumnNames(), user_alias);
    }
}
