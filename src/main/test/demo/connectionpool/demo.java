package demo.connectionpool;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.experimental.theories.Theories;

import javax.sql.DataSource;
import java.sql.*;

public class demo {
    public static void main(String[] args) throws  Exception {
        Class.forName("com.mysql.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String password = "20020525";
        // 连接数据库
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();

        // 设置行锁
        statement.execute("LOCK TABLES users READ ");
        // 开启事务
        connection.setAutoCommit(false);
        //statement.executeUpdate("update users set username = 'wbf' where id = 23");
        ResultSet resultSet = statement.executeQuery("select * from users where id = 23 lock in share mode");// 获取读锁

        //while(resultSet.next()){
        //    System.out.println(resultSet.getInt("id")+":"+resultSet.getString("username"));
        //}
        // 提交事务
        connection.commit();
    }
}
