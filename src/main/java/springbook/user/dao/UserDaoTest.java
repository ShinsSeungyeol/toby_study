package springbook.user.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springbook.user.domain.User;

public class UserDaoTest {

  @Test
  public void 등록_조회() throws SQLException {
    ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);

    UserDao dao = context.getBean("userDao", UserDao.class);

    dao.deleteAll();
    assertEquals(dao.getCount(),0);

    User user = new User();
    user.setId("whiteship");
    user.setName("백기선");
    user.setPassword("married");

    dao.add(user);
    assertEquals(dao.getCount(),1);

    User foundUser = dao.get(user.getId());

    assertEquals(user.getId(), foundUser.getId());
    assertEquals(user.getName(), foundUser.getName());
    assertEquals(user.getPassword(), foundUser.getPassword());
  }

}
