//package springbook;
//
//import springbook.user.dao.ConnectionMaker;
//import springbook.user.dao.DConnectionMaker;
//import springbook.user.dao.UserDao;
//import springbook.user.domain.User;
//
//import java.sql.SQLException;
//
//public class Main {
//    //엔트리 포인트 main
//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        ConnectionMaker connectionMaker = new DConnectionMaker();
//        UserDao dao = new UserDao(connectionMaker);
//
//        User user = new User();
//        user.setName("경제목");
//        user.setId("rudnf1");
//        user.setPassword("gkdlfn12");
//
//        dao.add(user);
//
//        User findUser = dao.get("rudnf1");
//        System.out.println("findUser = " + findUser.getName());
//
//        int result = dao.delete("rudnf1");
//        System.out.println("result = " + result);
//    }
//}
//
