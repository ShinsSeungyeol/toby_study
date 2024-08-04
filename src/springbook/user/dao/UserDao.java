package springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import springbook.user.domain.User;

public abstract class UserDao {

  /**
   * 유저 등록 함수
   * @param user
   * @throws ClassNotFoundException
   * @throws SQLException
   */
  public void add(User user) throws ClassNotFoundException, SQLException {
    Connection c = getConnection();

    PreparedStatement ps = c.prepareStatement(
        "insert into users(id, name, password) values(?, ?, ?)");

    ps.setString(1, user.getId());
    ps.setString(2, user.getName());
    ps.setString(3, user.getPassword());

    ps.executeUpdate();
    c.close();
  }

  /**
   * 유저 조회 함수
   * @param id
   * @return
   * @throws ClassNotFoundException
   * @throws SQLException
   */
  public User get(String id) throws ClassNotFoundException, SQLException {
    Connection c = getConnection();

    PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
    ps.setString(1, id);

    ResultSet rs = ps.executeQuery();
    rs.next();

    User user = new User();
    user.setId(rs.getString("id"));
    user.setName(rs.getString("name"));
    user.setPassword(rs.getString("password"));

    rs.close();
    ps.close();
    c.close();

    return user;
  }

  /**
   * DB Connect에 관련된 관심 사항을 서브 클래스에서 담당하도록 한다.
   * @return
   * @throws ClassNotFoundException
   * @throws SQLException
   */
  protected abstract Connection getConnection() throws  ClassNotFoundException, SQLException;

  /**
   * JDBC Connection 객체 리턴
   * @return
   * @throws ClassNotFoundException
   * @throws SQLException
   */
//  private Connection getConnection() throws ClassNotFoundException, SQLException {
//    Class.forName("com.mysql.cj.jdbc.Driver");
//    return DriverManager.getConnection("jdbc:mysql://localhost/springbook?allowPublicKeyRetrieval=true&useSSL=false", "seungyeol", "1234");
//  }

//  public static void main(String[] args) throws ClassNotFoundException, SQLException {
//    UserDao dao = new UserDao();
//
//    User user = new User();
//    user.setId("whiteship");
//    user.setName("백기선");
//    user.setPassword("married");
//
//    dao.add(user);
//
//    System.out.println(STR."\{user.getId()} 등록 성공");
//
//    User user2 = dao.get(user.getId());
//    System.out.println(user2.getName());
//    System.out.println(user2.getPassword());
//
//    System.out.println(STR."\{user.getId()} 조회 성공");
//  }

}
