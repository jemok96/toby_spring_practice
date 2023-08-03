package springbook.user.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DaoFactoryTest {
    ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
    @Test
    void UserDaoEqualsCheck() throws Exception{
        DaoFactory factory = new DaoFactory();
        UserDao dao1 = factory.userDao();
        UserDao dao2 = factory.userDao();
        System.out.println("dao1 = " + dao1);
        System.out.println("dao2 = " + dao2);
        Assertions.assertNotSame(dao1,dao2);

        dao1 = context.getBean("userDao",UserDao.class);
        dao2 = context.getBean("userDao",UserDao.class);
        System.out.println("dao1 = " + dao1);
        System.out.println("dao2 = " + dao2);
        Assertions.assertSame(dao1,dao2);
    }

}