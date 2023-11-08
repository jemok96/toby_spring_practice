package springbook.user.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import springbook.user.context.JdbcContext;
import springbook.user.domain.User;
import springbook.user.starategy.AddStatement;
import springbook.user.starategy.DeleteAllStatement;
import springbook.user.starategy.DeleteStatement;
import springbook.user.starategy.StatementStrategy;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

//DAO(Data Access Object)
// DB를 사용해 데이터를 조회하거나 조작하는 기능을 전담하도록 만든 오브젝트를 말한다.
public class UserDao {
    //    private ConnectionMaker connectionMaker; // 인터페이스에 의존한다.
    private JdbcTemplate jdbcTemplate;
    private RowMapper<User> userMapper =new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            return user;
        }
    };

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void add(User user) throws ClassNotFoundException, SQLException {

        jdbcTemplate.update("insert INTO users(id,name,password) values (?,?,?)"
                , user.getId(), user.getName(), user.getPassword());
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        return this.jdbcTemplate.queryForObject("select * from users where id = ?",
                new Object[] {id},
                userMapper);
    }
    public List<User> getAll(){
        return this.jdbcTemplate.query("select * from users order by id",userMapper
                );
    }




    public void deleteAll() throws ClassNotFoundException, SQLException {
        this.jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                return con.prepareStatement("delete from users");
            }
        });
    }


    public int getCount() throws ClassNotFoundException, SQLException {
        return this.jdbcTemplate.queryForObject("select count(*) from users", Integer.class);

    }

}


