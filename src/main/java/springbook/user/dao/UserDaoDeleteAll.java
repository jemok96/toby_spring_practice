//package springbook.user.dao;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class UserDaoDeleteAll extends UserDao{
//
//
//    public UserDaoDeleteAll(DataSource dataSource) {
//        super(dataSource);
//    }
//
//    @Override
//    PreparedStatement makeStatement(Connection c) throws SQLException {
//        PreparedStatement ps = c.prepareStatement("delete from users");
//        return ps;
//    }
//
//
//}
