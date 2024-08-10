package springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SimpleConnectionMaker implements ConnectionMaker{

  public SimpleConnectionMaker() {
  }

  @Override
  public Connection makeConnection() throws ClassNotFoundException, SQLException {
    Class.forName("com.mysql.cj.jdbc.Driver");
    return DriverManager.getConnection("jdbc:mysql://localhost/springbook?allowPublicKeyRetrieval=true&useSSL=false", "seungyeol", "1234");
  }
}
