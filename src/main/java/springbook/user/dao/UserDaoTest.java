package springbook.user.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springbook.user.domain.User;

import java.sql.SQLException;

public class UserDaoTest {


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        UserDao dao = new DaoFactory().userDao(); // 제어의 역전이 적용 됨

        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao dao = context.getBean("userDao", UserDao.class);
        User user = new User();
        user.setName("경제목");
        user.setId("rudnf1");
        user.setPassword("gkdlfn12");

        dao.add(user);

        User findUser = dao.get("rudnf1");
        System.out.println("findUser = " + findUser.getName());

        int result = dao.delete("rudnf1");
        System.out.println("result = " + result);


    }
}
