package ru.netology.web.data;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DataBaseHelper {
  private static String jdbcUrl;
  private static String user;
  private static String password;

  public static void getJdbcProperties() {
    FileInputStream fis;
    Properties property = new Properties();
    try {
      fis = new FileInputStream("./application.properties");
      property.load(fis);
      jdbcUrl = property.getProperty("spring.datasource.url");
      user = property.getProperty("spring.datasource.username");
      password = property.getProperty("spring.datasource.password");
    } catch (IOException e) {
      System.err.println("ОШИБКА: application.properties отсуствует!");
    }
  }

  public static String checkStatusInPayment() {
    getJdbcProperties();
    String queryStatusInDB;
    if (jdbcUrl.equals("jdbc:mysql://localhost:3306/app")) {
      queryStatusInDB = "SELECT status FROM payment_entity WHERE created >= DATE_SUB(NOW() , INTERVAL 1 SECOND);";
    } else {
      queryStatusInDB = "SELECT status FROM payment_entity WHERE created >= (current_timestamp - INTERVAL '1 second');";
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
    getJdbcProperties();
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