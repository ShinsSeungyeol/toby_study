package springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import springbook.user.domain.User;

public class UserDao {
  private DataSource dataSource;

  /*
   * 생성자
   */
  public UserDao(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  /**
   * 유저 등록 함수
   * @param user
   * @throws ClassNotFoundException
   * @throws SQLException
   */
  public void add(User user) throws SQLException {
    Connection c = dataSource.getConnection();

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
  public User get(String id) throws SQLException {
    Connection c = dataSource.getConnection();

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
   * 전체 유저를 삭제하는 함수
   * @throws SQLException
   */
  public void deleteAll() throws SQLException {
    Connection c = dataSource.getConnection();

    PreparedStatement ps = c.prepareStatement("delete from users");

    ps.executeUpdate();

    ps.close();
    c.close();
  }

  /**
   *  사용자 총 수를 리턴하는 함수
   * @return
   * @throws SQLException
   */
  public int getCount() throws SQLException{
    Connection c = dataSource.getConnection();

    PreparedStatement ps = c.prepareStatement("select count(*) from users");
    ResultSet rs = ps.executeQuery();
    rs.next();
    int count  = rs.getInt(1);
    rs.close();
    ps.close();
    c.close();

    return count;
  }

}
