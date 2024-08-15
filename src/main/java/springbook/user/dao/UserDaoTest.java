package springbook.user.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import springbook.user.domain.User;

public class UserDaoTest {
  private UserDao dao;

  @BeforeEach
  public void setUp() throws SQLException {
    ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
    dao = context.getBean("userDao", UserDao.class);
    dao.deleteAll();

  }

  @Test
  public void 등록_조회() throws SQLException {
    //given
    User user1 = new User();
    user1.setId("whiteship");
    user1.setName("백기선");
    user1.setPassword("married");

    User user2 = new User();
    user2.setId("leegw700");
    user2.setName("이길원");
    user2.setPassword("springno2");

    //when
    dao.add(user1);
    dao.add(user2);

    //then
    assertEquals(dao.getCount(), 2);

    User foundUser1 = dao.get(user1.getId());

    assertEquals(user1.getId(), foundUser1.getId());
    assertEquals(user1.getName(), foundUser1.getName());
    assertEquals(user1.getPassword(), foundUser1.getPassword());

    User foundUser2 = dao.get(user2.getId());

    assertEquals(user2.getId(), foundUser2.getId());
    assertEquals(user2.getName(), foundUser2.getName());
    assertEquals(user2.getPassword(), foundUser2.getPassword());
  }

  @Test
  public void count() throws SQLException {
    User user1 = new User("gyumee", "박성철", "springno1");
    User user2 = new User("leegw700", "이길원", "springno2");
    User user3 = new User("bumjin", "박범진", "springno3");

    dao.add(user1);
    assertEquals(dao.getCount(),1);

    dao.add(user2);
    assertEquals(dao.getCount(),2);

    dao.add(user3);
    assertEquals(dao.getCount(),3);
  }

  @Test
  public void 사용자_아이디_없는_경우_예외() throws SQLException {
    assertThrows(EmptyResultDataAccessException.class, () ->
      dao.get("unknown_id")
    );
  }

}
