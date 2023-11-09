package springbook.user.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import springbook.user.domain.User;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {
    ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
    UserDao dao;

    @BeforeEach
    void userDaoInit() throws SQLException, ClassNotFoundException {
        dao = context.getBean("userDao", UserDao.class);
    }
    @Test
    public void getAll() throws Exception{
        dao.deleteAll();

        User user1 = new User("rudnf9605","JM","gkdl");
        dao.add(user1);
        List<User> all1 = dao.getAll();
        Assertions.assertThat(all1.size()==1);

        User user2 = new User("rudnf550","JM1","gg");
        dao.add(user2);
        List<User> all2 = dao.getAll();
        Assertions.assertThat(all2.size()==2);

        User user3 = new User("rudnf99","JM2","gff");
        dao.add(user3);
        List<User> all3 = dao.getAll();
        Assertions.assertThat(all3.size()==3);

    }

}