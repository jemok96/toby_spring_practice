//package springbook.factory;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.SimpleDriverDataSource;
//import springbook.user.dao.ConnectionMaker;
//import springbook.user.dao.CountingConnectionMaker;
//import springbook.user.dao.DConnectionMaker;
//import springbook.user.dao.UserDao;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//
//@Configuration // == @Beans
//public class DaoFactory {
//    @Bean  // == @Bean
//    public UserDao userDao(){ // method명 == id
//        UserDao userDao = new UserDao(dataSource());
//        return userDao;
//        /*UserDao dao = new UserDao();
//        dao.setConnectionMaker(connectionMaker());
//        return dao;*/
//
////        return new UserDao(connectionMaker()); // new UserDao  == class="userDao의  Package명.class명"
//        //생성자 매개변수 == constructor-arg  ref="(참조값일경우)"
//        //set으로 주입경우 <property 태그사용>
//    }
//
//    @Bean
//    public DataSource dataSource(){
//        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
//        dataSource.setDriverClass(com.mysql.cj.jdbc.Driver.class);
//        dataSource.setUrl("jdbc:mysql://localhost:3306/springbasic");
//        dataSource.setUsername("root");
//        dataSource.setPassword("root");
//        return dataSource;
//    }
//    @Bean
//    public ConnectionMaker connectionMaker(){
//        return new DConnectionMaker();
//    }
//
//}
