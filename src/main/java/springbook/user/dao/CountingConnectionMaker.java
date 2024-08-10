package springbook.user.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

public class CountingConnectionMaker implements ConnectionMaker{
  AtomicInteger counter = new AtomicInteger(0);
  private ConnectionMaker realConnectionMaker;

  public CountingConnectionMaker(ConnectionMaker connectionMaker) {
    this.realConnectionMaker = connectionMaker;
  }

  @Override
  public Connection makeConnection() throws ClassNotFoundException, SQLException {
    counter.incrementAndGet();
    return realConnectionMaker.makeConnection();
  }

  public int getCounter() {
    return this.counter.get();
  }

}
