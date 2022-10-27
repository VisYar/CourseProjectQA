package ru.netology.data;

import lombok.*;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class DBHelper {
    private static QueryRunner runner;
    private static Connection conn;

    @SneakyThrows
    public static void setup() {
        runner = new QueryRunner();
        conn = DriverManager.getConnection(System.getProperty("dbUrl"), "app", "pass");
    }

    @SneakyThrows
    public static void clear() {
        setup();
        val sqlPayment = "DELETE FROM payment_entity;";
        val sqlCredit = "DELETE FROM credit_request_entity;";
        val sqlOrder = "DELETE FROM order_entity;";
        runner.update(conn, sqlPayment);
        runner.update(conn, sqlCredit);
        runner.update(conn, sqlOrder);
    }

    @Value
    public static class Payment {
        String id;
        String status;
        String transaction_id;
    }

    @SneakyThrows
    public static String getPaymentStatus() {
        setup();
        val status = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1;";
        return runner.query(conn, status, new ScalarHandler<>());
    }

    @SneakyThrows
    public static long getPaymentCount() {
        setup();
        val countId = "SELECT COUNT(id) as count FROM payment_entity;";
        return runner.query(conn, countId, new ScalarHandler<>());
    }

    @SneakyThrows
    public static List<Payment> getPayments() {
        setup();
        val sqlQuery = "SELECT * FROM payment_entity ORDER BY created DESC;";
        return runner.query(conn, sqlQuery, new ScalarHandler<>());
    }

    @Value
    public static class Credit {
        String id;
        String status;
    }

    @SneakyThrows
    public static String getCreditStatus() {
        val status = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1;";
        return runner.query(conn, status, new ScalarHandler<>());
    }

    @SneakyThrows
    public static String getCreditCount() {
        val countId = "SELECT COUNT(id) as count FROM credit_request_entity;";
        return runner.query(conn, countId, new ScalarHandler<>());
    }

    @SneakyThrows
    public static List<Credit> getCreditsRequest() {
        setup();
        val sqlQuery = "SELECT * FROM credit_request_entity ORDER BY created DESC;";
        return runner.query(conn, sqlQuery, new ScalarHandler<>());
    }

    @Value
    public static class Order {
        String id;
        String credit_id;
        String payment_id;
    }

    @SneakyThrows
    public static long getOrderCount() {
        setup();
        val countId = "SELECT COUNT(id) as count FROM order_entity;";
        return runner.query(conn, countId, new ScalarHandler<>());
    }

    @SneakyThrows
    public static List<Order> getOrders() {
        setup();
        val sqlQuery = "SELECT * FROM order_entity ORDER BY created DESC;";
        return runner.query(conn, sqlQuery, new ScalarHandler<>());
    }
}
