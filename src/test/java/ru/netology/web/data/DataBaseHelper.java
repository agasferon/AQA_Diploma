package ru.netology.web.data;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseHelper {
  static String jdbcUrl = System.getProperty("db.url");;
  static String user = System.getProperty("db.user");
  static String password = System.getProperty("db.password");

  public static String checkStatusInPayment() {
    String queryStatusInDB;
    if (jdbcUrl.equals("jdbc:mysql://localhost:3306/app")) {
      queryStatusInDB = "SELECT status FROM payment_entity WHERE created >= DATE_SUB(NOW() , INTERVAL 1 SECOND);";
    } else {
      queryStatusInDB = "SELECT status FROM payment_entity WHERE created >= (NOW() - INTERVAL '1 second');";
    }
    val runner = new QueryRunner();
    try (
            val conn = DriverManager.getConnection(jdbcUrl, user, password)
    ) {
      val status = runner.query(conn, queryStatusInDB, new ScalarHandler<>());
      return (String) status;
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return null;
  }

  public static String checkStatusInCredit() {
    String queryStatusInDB;
    if (jdbcUrl.equals("jdbc:mysql://localhost:3306/app")) {
      queryStatusInDB = "SELECT status FROM credit_request_entity WHERE created >= DATE_SUB(NOW() , INTERVAL 1 SECOND);";
    } else {
      queryStatusInDB = "SELECT status FROM credit_request_entity WHERE created >= (current_timestamp - INTERVAL '1 second');";
    }
    val runner = new QueryRunner();
    try (
            val conn = DriverManager.getConnection(jdbcUrl, user, password)
    ) {
      val status = runner.query(conn, queryStatusInDB, new ScalarHandler<>());
      return (String) status;
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return null;
  }

}