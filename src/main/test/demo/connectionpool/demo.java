package demo.connectionpool;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class demo {
    public static void main(String[] args) throws SQLException {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        config.setUsername("root");
        config.setPassword("20020525");
        config.addDataSourceProperty("connectionTimeout", "1000");// 连接超时：1秒
        config.addDataSourceProperty("idleTimeout", "1000");// 空闲超时：1秒
        config.addDataSourceProperty("maximumPoolSize", "1");// 最大连接数：1

        HikariDataSource ds = new HikariDataSource(config);

        String sql = "select * from users";
        String sql2= "select * from orders";
        Connection connection = ds.getConnection();
        Statement statement1 = connection.createStatement();
        ResultSet resultSet = statement1.executeQuery(sql);
        while (resultSet.next()){
            System.out.println(resultSet.getString("username"));
        }
        System.out.println("TotalConnections:"+ds.getHikariPoolMXBean().getTotalConnections());
        System.out.println("ActiveConnections:"+ds.getHikariPoolMXBean().getActiveConnections());

        Connection connection2 = ds.getConnection();
        Statement statement2 = connection2.createStatement();
        ResultSet resultSet2 = statement2.executeQuery(sql2);
        while (resultSet2.next()){
            System.out.println(resultSet2.getString("order_number"));
        }


        System.out.println("TotalConnections:"+ds.getHikariPoolMXBean().getTotalConnections());
        System.out.println("ActiveConnections:"+ds.getHikariPoolMXBean().getActiveConnections());
        Connection connection3 = ds.getConnection();
        Statement statement3 = connection3.createStatement();
        ResultSet resultSet3 = statement3.executeQuery(sql2);
        while (resultSet3.next()){
            System.out.println(resultSet3.getString("order_number"));
        }
        System.out.println(connection.getMetaData().getDatabaseProductName());
        System.out.println("TotalConnections:"+ds.getHikariPoolMXBean().getTotalConnections());
        System.out.println("ActiveConnections:"+ds.getHikariPoolMXBean().getActiveConnections());

    }
}
