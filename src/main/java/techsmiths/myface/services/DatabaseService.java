package techsmiths.myface.services;

import org.jdbi.v3.core.Jdbi;

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
}
