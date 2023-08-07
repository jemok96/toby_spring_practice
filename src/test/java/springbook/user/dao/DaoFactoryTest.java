package springbook.user.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import springbook.factory.DaoFactory;
import springbook.user.domain.User;

import java.sql.SQLException;

@SpringBootTest
class DaoFactoryTest {
    ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
    UserDao userDao;

    @BeforeEach
    void userDaoInit() throws SQLException, ClassNotFoundException {
         userDao = context.getBean("userDao", UserDao.class);
    }
    @Test
    void UserDaoEqualsCheck() throws Exception{
        DaoFactory factory = new DaoFactory();
        UserDao dao1 = factory.userDao();
        UserDao dao2 = factory.userDao();
        System.out.println("dao1 = " + dao1);
        System.out.println("dao2 = " + dao2);
        Assertions.assertNotSame(dao1,dao2); //Asserts that expected and actual do not refer to the same object.

        dao1 = context.getBean("userDao",UserDao.class);
        dao2 = context.getBean("userDao",UserDao.class);
        System.out.println("dao1 = " + dao1);
        System.out.println("dao2 = " + dao2);
        Assertions.assertSame(dao1,dao2); // Asserts that expected and actual refer to the same object.
    }

    @Test
    public void addAndGet() throws Exception{



        User user = new User();
        user.setName("경제목");
        user.setPassword("gkdlfn12");
        user.setId("rudnf9605");

        userDao.add(user);
        Assertions.assertEquals(userDao.getCount(),1);

        User findUser = userDao.get("rudnf9605");
        Assertions.assertEquals(findUser.getName(),user.getName());
        Assertions.assertEquals(findUser.getPassword(),user.getPassword()); //Asserts that expected and actual are equal.

        userDao.deleteAll();
        Assertions.assertEquals(userDao.getCount(),0);
    }
    @Test
    public void count() throws Exception{
        User user1 = new User("gyumee", "박성철", "springno1");
        User user2 = new User("JM", "제목", "springno2");
        User user3 = new User("KJM", "경제목", "springno3");

        userDao.add(user1);
        Assertions.assertEquals(userDao.getCount(),1);
        Assertions.assertEquals(userDao.get("gyumee").getId(),user1.getId());
        userDao.add(user2);
        Assertions.assertEquals(userDao.getCount(),2);
        userDao.add(user3);
        Assertions.assertEquals(userDao.getCount(),3);

        userDao.deleteAll();
        Assertions.assertEquals(userDao.getCount(),0);

    }
    @Test()
    public void getUserFailure() throws Exception{
        //given
        userDao.deleteAll();
        Assertions.assertEquals(userDao.getCount(),0);
        Assertions.assertThrows(EmptyResultDataAccessException.class,() ->{
            userDao.get("unknown_id");        //when
        });        //then



    }

}