package springbook.user.dao;

import com.mysql.cj.jdbc.Driver;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

/**
 * DAO 의 객체 생성 방법을 결정하고, 오브젝트를 반환하는 팩토리 객체
 * 오브젝트의 관계를 정의하는 책임을 맡고 있음
 */

@Configuration
public class DaoFactory {

  @Bean
  public UserDao userDao(DataSource dataSource)
      throws InstantiationException, IllegalAccessException {
    UserDao userDao = new UserDao(dataSource());
    return userDao;
  }

  @Bean
  public DataSource dataSource() throws InstantiationException, IllegalAccessException {
    SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

    dataSource.setDriver(Driver.class.newInstance());
    dataSource.setUrl(
        "jdbc:mysql://localhost:3306/springbook?allowPublicKeyRetrieval=true&useSSL=false");
    dataSource.setUsername("seungyeol");
    dataSource.setPassword("1234");

    return dataSource;
  }
}
