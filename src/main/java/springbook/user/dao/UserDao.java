package springbook.user.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import springbook.user.domain.User;

import javax.sql.DataSource;
import java.sql.*;

//DAO(Data Access Object)
// DB를 사용해 데이터를 조회하거나 조작하는 기능을 전담하도록 만든 오브젝트를 말한다.
public  class UserDao {
//    private ConnectionMaker connectionMaker; // 인터페이스에 의존한다.
    private DataSource dataSource;

    private Connection c;

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
        User user = null;

        if(rs.next()) {
            user = new User();
            user.setId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
        }
        System.out.println("user = " + user);
        if(user ==null){throw new EmptyResultDataAccessException(1);
        }
        rs.close();
        ps.close();
        c.close();
        return user;
    }
    public void delete(String id) throws ClassNotFoundException, SQLException{
        c = null;
        PreparedStatement ps = null;

        try{
            c = dataSource.getConnection();
            ps = c.prepareStatement("delete from users");
            ps.executeUpdate();
        }
        catch(SQLException e) {
            throw e;
        }finally {
            if(ps !=null){
                try{
                    ps.close();
                }
                catch(SQLException e) {
                    throw e;
                }
            }
            if(ps !=null){
                try{
                    ps.close();
                }
                catch(SQLException e) {
                    throw e;
                }
            }
        }
    }
    public void deleteAll() throws ClassNotFoundException, SQLException{
        c = null;
        PreparedStatement ps = null;

        try{
            c = dataSource.getConnection();
            ps = c.prepareStatement("delete from users");
            ps.executeUpdate();
        }
        catch(SQLException e) {
            throw e;
        }
        finally {
            if(ps !=null){
                try{
                    ps.close();
                }
                catch(SQLException e) {
                    throw e;
                }
            }
            if(ps !=null){
                try{
                    ps.close();
                }
                catch(SQLException e) {
                    throw e;
                }
            }
        }
    }

    public int getCount()throws ClassNotFoundException,SQLException{
        c = null;
        PreparedStatement ps = null;
        ResultSet rs =null;

        try{
            c = dataSource.getConnection();
            ps = c.prepareStatement("select count(*) from users");
            rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
            }
        catch(SQLException e) {
            throw e;
        }
        finally {
            if(rs !=null){
                try{
                    rs.close();
                }
                catch(SQLException e) {
                }
            }
            if(ps !=null){
                try{
                    ps.close();
                }
                catch(SQLException e) {
                }
            }
            if(c !=null){
                try{
                    c.close();
                }
                catch(SQLException e) {
                }
            }
        }
    }
}
