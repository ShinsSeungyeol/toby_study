package springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import springbook.user.domain.User;

public class NUserDao extends User {

  protected Connection getConnection() throws ClassNotFoundException, SQLException {
    Class.forName("com.mysql.cj.jdbc.Driver");
    return DriverManager.getConnection("jdbc:mysql://localhost/springbook?allowPublicKeyRetrieval=true&useSSL=false", "seungyeol", "1234");
  }
}
