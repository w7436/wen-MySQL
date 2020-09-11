package nancy;

import java.sql.*;

/**
 * @ClassName hello
 * @Description TODO
 * @Author DELL
 * @Data 2020/6/18 8:16
 * @Version 1.0
 **/
public class hello {


    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1、注册Driver
        Class.forName("com.mysql.jdbc.Driver");
        //2、获取连接
        String URL ="jdbc:mysql://127.0.0.1:3306/test?useSSL=false";
        String username = "root";
        String password="123456";
        Connection connection = DriverManager.getConnection(
                URL,username,password
        );
        System.out.println(connection);
        //3、获取语句Statement
        Statement statement = connection.createStatement();

        //4、执行一条语句
        String sql = "select database()";
        ResultSet resultSet = statement.executeQuery(sql);
        //事先知道结果只有一行+ 一列
        resultSet.next();
        String name = resultSet.getString(1);//第一列是从1开始的
        System.out.println(name);

        //关闭结果集
        resultSet.close();
        //关闭语句
        statement.close();
        //关闭连接
        connection.close();

    }

}
