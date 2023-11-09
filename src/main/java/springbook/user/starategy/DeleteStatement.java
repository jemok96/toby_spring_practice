package springbook.user.starategy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteStatement implements StatementStrategy {
    String id;

    public DeleteStatement(String id) {
        this.id = id;
    }

    @Override
    public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
        PreparedStatement ps = c.prepareStatement("delete from users where id = ?");
        ps.setString(1,id);
        return ps;
    }
}
