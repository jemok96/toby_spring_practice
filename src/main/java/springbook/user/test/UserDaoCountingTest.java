package springbook.user.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springbook.user.dao.CountingConnectionMaker;
import springbook.factory.DaoFactory;
import springbook.user.dao.UserDao;
import springbook.user.domain.User;

import java.sql.SQLException;

public class UserDaoCountingTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao dao = ac.getBean("userDao", UserDao.class);
        User user = new User();
        user.setName("경제목");
        user.setId("rudnf1");
        user.setPassword("gkdlfn12");

        dao.add(user);

        User findUser = dao.get("rudnf1");
        System.out.println("findUser = " + findUser.getName());

        int result = dao.delete("rudnf1");
        System.out.println("result = " + result);

        CountingConnectionMaker ccm = ac.getBean("connectionMaker", CountingConnectionMaker.class);
        System.out.println("ccm = " + ccm.getCounter());
    }

}
