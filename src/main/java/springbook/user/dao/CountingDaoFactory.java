package springbook.user.dao;

import com.mysql.cj.jdbc.Driver;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Configuration
public class CountingDaoFactory {
  @Bean
  public UserDao userDao() throws InstantiationException, IllegalAccessException {
    return new UserDao(dataSource());
  }

//  @Bean
//  public ConnectionMaker connectionMaker() {
//    return new CountingConnectionMaker(simpleConnectionMaker());
//  }
//
//  @Bean
//  public ConnectionMaker simpleConnectionMaker() {
//    return new SimpleConnectionMaker();
//  }

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
