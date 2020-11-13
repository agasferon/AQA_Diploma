package ru.netology.web.data;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import javax.sql.DataSource;
import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DataBaseHelper {
  static String jdbcUrl = System.getProperty("db.url");
  static String user = System.getProperty("db.user");
  static String password = System.getProperty("db.password");

  public static void getJdbcProperties() {
    jdbcUrl = System.getProperty("db.url");
    user = System.getProperty("db.user");
    password = System.getProperty("db.password");
//    FileInputStream fis;
//    Properties property = new Properties();
//    try {
//      fis = new FileInputStream("./application.properties");
//      property.load(fis);
//      jdbcUrl = property.getProperty("spring.datasource.url");
//      user = property.getProperty("spring.datasource.username");
//      password = property.getProperty("spring.datasource.password");
//    } catch (IOException e) {
//      System.err.println("ОШИБКА: application.properties отсуствует!");
//    }
  }

  public static String checkStatusInPayment() {
    getJdbcProperties();
    String queryStatusInDB;
    String queryStatusInDB2; //for debug
    String queryStatusInDB3; //for debug
    String queryStatusInDB4; //for debug
    String queryStatusInDB5; //for debug
    System.out.println(jdbcUrl);
    System.out.println(user);
    System.out.println(password);
    if (jdbcUrl.equals("jdbc:mysql://localhost:3306/app")) {
      System.out.println("TRUE! MYSQL!"); //for debug
      queryStatusInDB = "SELECT status FROM payment_entity WHERE created >= DATE_SUB(NOW() , INTERVAL 1 SECOND);";
      queryStatusInDB2 = "SELECT id FROM payment_entity WHERE created >= DATE_SUB(NOW() , INTERVAL 1 SECOND);"; //for debug
      queryStatusInDB3 = "SELECT NOW();"; //for debug
      queryStatusInDB4 = "SELECT DATE_SUB(NOW() , INTERVAL 1 SECOND);"; //for debug
      queryStatusInDB5 = "SELECT NOW() >= DATE_SUB(NOW() , INTERVAL 1 SECOND);"; //for debug
    } else {
      System.out.println("FALSE! POSTGRES!"); //for debug
      queryStatusInDB = "SELECT status FROM payment_entity WHERE created >= (NOW() - INTERVAL '1 second');";
      queryStatusInDB2 = "SELECT id FROM payment_entity WHERE created >= (NOW() - INTERVAL '1 second');"; //for debug
      queryStatusInDB3 = "SELECT (NOW());"; //for debug
      queryStatusInDB4 = "SELECT (NOW() - INTERVAL '1 second');"; //for debug
      queryStatusInDB5 = "SELECT NOW() >= (NOW() - INTERVAL '1 second');"; //for debug
    }
    val runner = new QueryRunner();
    try (
            val conn = DriverManager.getConnection(jdbcUrl, user, password)
    ) {
      val status = runner.query(conn, queryStatusInDB, new ScalarHandler<>());
      val statusId = runner.query(conn, queryStatusInDB2, new ScalarHandler<>()); //for debug
      val time = runner.query(conn, queryStatusInDB3, new ScalarHandler<>()); //for debug
      val diffTime = runner.query(conn, queryStatusInDB4, new ScalarHandler<>()); //for debug
      val diffTimeB = runner.query(conn, queryStatusInDB5, new ScalarHandler<>()); //for debug
      System.out.println(queryStatusInDB); //for debug
      System.out.println(queryStatusInDB2); //for debug
      System.out.println("status = " + status); //for debug
      System.out.println("id = " + statusId); //for debug
      System.out.println("time = " + time); //for debug
      System.out.println("time = " + diffTime); //for debug
      System.out.println("result = " + diffTimeB); //for debug
      return (String) status;
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return null;
  }

  public static String checkStatusInCredit() {
    getJdbcProperties();
    String queryStatusInDB;
    String queryStatusInDB2; //for debug
    String queryStatusInDB3; //for debug
    String queryStatusInDB4; //for debug
    String queryStatusInDB5; //for debug
    System.out.println(jdbcUrl);
    System.out.println(user);
    System.out.println(password);
    if (jdbcUrl.equals("jdbc:mysql://localhost:3306/app")) {
      System.out.println("TRUE! MYSQL!"); //for debug
      queryStatusInDB = "SELECT status FROM credit_request_entity WHERE created >= DATE_SUB(NOW() , INTERVAL 1 SECOND);";
      queryStatusInDB2 = "SELECT id FROM credit_request_entity WHERE created >= DATE_SUB(NOW() , INTERVAL 1 SECOND);"; //for debug
      queryStatusInDB3 = "SELECT NOW(), ' minus interval = ', DATE_SUB(NOW() , INTERVAL 1 SECOND);"; //for debug
      queryStatusInDB4 = "SELECT DATE_SUB(NOW() , INTERVAL 1 SECOND);"; //for debug
      queryStatusInDB5 = "SELECT NOW() >= DATE_SUB(NOW() , INTERVAL 1 SECOND);"; //for debug
    } else {
      System.out.println("FALSE! POSTGRES!"); //for debug
      queryStatusInDB = "SELECT status FROM credit_request_entity WHERE created >= (current_timestamp - INTERVAL '1 second');";
      queryStatusInDB2 = "SELECT id FROM credit_request_entity WHERE created >= (current_timestamp - INTERVAL '1 second');"; //for debug
      queryStatusInDB3 = "SELECT (current_timestamp), ' minus interval = ', (current_timestamp - INTERVAL '1 second');"; //for debug
      queryStatusInDB4 = "SELECT (current_timestamp - INTERVAL '1 second');"; //for debug
      queryStatusInDB5 = "SELECT NOW() >= (NOW() - INTERVAL '1 second');"; //for debug
    }
    val runner = new QueryRunner();
    try (
            val conn = DriverManager.getConnection(jdbcUrl, user, password)
    ) {
      val status = runner.query(conn, queryStatusInDB, new ScalarHandler<>());
      val statusId = runner.query(conn, queryStatusInDB2, new ScalarHandler<>()); //for debug
      val time = runner.query(conn, queryStatusInDB3, new ScalarHandler<>()); //for debug
      val diffTime = runner.query(conn, queryStatusInDB4, new ScalarHandler<>()); //for debug
      val diffTimeB = runner.query(conn, queryStatusInDB5, new ScalarHandler<>()); //for debug
      System.out.println(queryStatusInDB); //for debug
      System.out.println(queryStatusInDB2); //for debug
      System.out.println("status = " + status); //for debug
      System.out.println("id = " + statusId); //for debug
      System.out.println("time = " + time); //for debug
      System.out.println("time = " + diffTime); //for debug
      System.out.println("result = " + diffTimeB); //for debug
      return (String) status;
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return null;
  }

}