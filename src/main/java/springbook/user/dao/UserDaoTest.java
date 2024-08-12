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

  @Test
  public void count() throws SQLException {
    ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
    UserDao dao = context.getBean("userDao", UserDao.class);


    User user1 = new User("gyumee", "박성철", "springno1");
    User user2 = new User("leegw700", "이길원", "springno2");
    User user3 = new User("bumjin", "박범진", "springno3");

    dao.deleteAll();
    assertEquals(dao.getCount(),0);

    dao.add(user1);
    assertEquals(dao.getCount(),1);

    dao.add(user2);
    assertEquals(dao.getCount(),2);

    dao.add(user3);
    assertEquals(dao.getCount(),3);
  }
}
