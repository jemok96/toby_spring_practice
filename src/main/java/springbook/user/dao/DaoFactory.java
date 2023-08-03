package springbook.user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {
    @Bean
    public UserDao userDao(){
        return new UserDao(dConnectionMaker());
    }
    @Bean
    public AccountDao accountDao(){
        return new AccountDao(dConnectionMaker());
    }
    @Bean
    public DConnectionMaker dConnectionMaker(){
        return new DConnectionMaker();
    }

}
