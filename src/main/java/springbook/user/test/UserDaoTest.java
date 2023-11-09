package springbook.user.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import springbook.user.dao.UserDao;
import springbook.user.domain.User;

import java.sql.SQLException;

public class UserDaoTest {


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        UserDao dao = new DaoFactory().userDao(); // 제어의 역전이 적용 됨

//        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
//        UserDao dao = context.getBean("userDao", UserDao.class);
//      의존관계 검색

        ApplicationContext ac = new GenericXmlApplicationContext("applicationContext.xml");
        UserDao dao = ac.getBean(UserDao.class);
        User user = new User();
        user.setName("경제목");
        user.setId("rudnf1");
        user.setPassword("gkdlfn12");

        dao.add(user);
        User user2 = new User();
        user2.setName("경제목");
        user2.setId("rudnf1");
        user2.setPassword("gkdlfn12");
        dao.add(user2);

        User findUser = dao.get("rudnf1");
        System.out.println("findUser = " + findUser.getName());
        System.out.println("dao.getCount() = " + dao.getCount());

        dao.deleteAll();

    }
}
