package springbook.user.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import springbook.user.context.JdbcContext;
import springbook.user.domain.User;
import springbook.user.starategy.AddStatement;
import springbook.user.starategy.DeleteAllStatement;
import springbook.user.starategy.DeleteStatement;
import springbook.user.starategy.StatementStrategy;

import javax.sql.DataSource;
import java.sql.*;

//DAO(Data Access Object)
// DB를 사용해 데이터를 조회하거나 조작하는 기능을 전담하도록 만든 오브젝트를 말한다.
public class UserDao {
    //    private ConnectionMaker connectionMaker; // 인터페이스에 의존한다.
    JdbcContext jdbcContext;
    DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.jdbcContext = new JdbcContext();
        this.jdbcContext.setDataSource(dataSource);
        this.dataSource = dataSource;
    }

    public void add(User user) throws ClassNotFoundException, SQLException {

        jdbcContext.worktWithStatementStrategy(new StatementStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
                PreparedStatement ps = c.prepareStatement("insert into users(id,name,password) values (?,?,?)");
                ps.setString(1, user.getId());
                ps.setString(2, user.getName());
                ps.setString(3, user.getPassword());
                return ps;
            }
        });
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection c = dataSource.getConnection();


        PreparedStatement ps = c.prepareStatement("select * from users where id= ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        User user = null;

        if (rs.next()) {
            user = new User();
            user.setId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
        }
        System.out.println("user = " + user);
        if (user == null) {
            System.out.println("Null입니다");
            //throw new EmptyResultDataAccessException(1);
        }
        rs.close();
        ps.close();
        c.close();
        return user;
    }

    public void delete(String id) throws ClassNotFoundException, SQLException {
        StatementStrategy st = new DeleteStatement(id);
        jdbcContext.worktWithStatementStrategy(st);
    }


    public void deleteAll() throws ClassNotFoundException, SQLException {
        this.jdbcContext.executeSql("delete from users");
    }


    public int getCount() throws ClassNotFoundException, SQLException {
        return this.jdbcContext.getResultContext(new StatementStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
                PreparedStatement ps = c.prepareStatement("select count(*) from users");
                return ps;
            }
        });
    }

}


