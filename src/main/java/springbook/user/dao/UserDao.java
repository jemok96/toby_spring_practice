package springbook.user.dao;

import springbook.user.domain.User;

import javax.sql.DataSource;
import java.sql.*;

//DAO(Data Access Object)
// DB를 사용해 데이터를 조회하거나 조작하는 기능을 전담하도록 만든 오브젝트를 말한다.
public  class UserDao {
//    private ConnectionMaker connectionMaker; // 인터페이스에 의존한다.
    private DataSource dataSource;

    private Connection c;
    private User user;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void add(User user) throws ClassNotFoundException, SQLException{
        c = dataSource.getConnection();

        PreparedStatement ps = c.prepareStatement("insert into users(id,name,password) values (?,?,?)");
        ps.setString(1,user.getId());
        ps.setString(2,user.getName());
        ps.setString(3,user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException{
        c = dataSource.getConnection();


        PreparedStatement ps = c.prepareStatement("select * from users where id= ?");
        ps.setString(1,id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        this.user = new User();
        this.user.setId(rs.getString("id"));
        this.user.setName(rs.getString("name"));
        this.user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        c.close();
        return this.user;
    }
    public int delete(String id) throws ClassNotFoundException, SQLException{
        c = dataSource.getConnection();


        PreparedStatement ps = c.prepareStatement("delete from users where id=?");
        ps.setString(1,id);

        int i = ps.executeUpdate();

        ps.close();
        c.close();
        return i;
    }
}
