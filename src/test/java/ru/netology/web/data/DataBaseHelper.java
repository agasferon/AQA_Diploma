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
    String queryStatusInDB = "SELECT status FROM payment_entity;";
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
    String queryStatusInDB = "SELECT status FROM credit_request_entity;";
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

  public static void cleanTables() {
    String queryDeleteOrderTable = "delete from order_entity;";
    String queryDeletePaymentTable = "delete from payment_entity;";
    String queryDeleteCreditTable = "delete from credit_request_entity;";
    val runner = new QueryRunner();
    try (
            val conn = DriverManager.getConnection(jdbcUrl, user, password)
    ) {
      runner.update(conn, queryDeleteOrderTable);
      runner.update(conn, queryDeletePaymentTable);
      runner.update(conn, queryDeleteCreditTable);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

}