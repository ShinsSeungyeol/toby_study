package springbook.user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * DAO 의 객체 생성 방법을 결정하고, 오브젝트를 반환하는 팩토리 객체
 * 오브젝트의 관계를 정의하는 책임을 맡고 있음
 */

@Configuration
public class DaoFactory {

  @Bean
  public UserDao userDao() {
    UserDao userDao = new UserDao(connectionMaker());
    return userDao;
  }

  @Bean
  public ConnectionMaker connectionMaker() {
    return new SimpleConnectionMaker();
  }
}
