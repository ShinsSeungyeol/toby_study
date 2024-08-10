package springbook.user.dao;

/**
 * DAO 의 객체 생성 방법을 결정하고, 오브젝트를 반환하는 팩토리 객체
 */
public class DaoFactory {
  public UserDao userDao() {
    ConnectionMaker connectionMaker = new SimpleConnectionMaker();
    UserDao userDao = new UserDao(connectionMaker);
    return userDao;
  }
}
