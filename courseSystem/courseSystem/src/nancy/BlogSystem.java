package nancy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * @ClassName BlogSystem
 * @Description TODO
 * @Author DELL
 * @Data 2020/6/18 9:16
 * @Version 1.0
 **/
public class BlogSystem {
    private static Connection connection;
    static {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/test",
                    "root",
                    "123456"
            );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        while(running){
            System.out.print("博客系统");
            int select = sc.nextInt();
            switch (select){
                case 1:registerUser();break;
                default:running=false;
            }

        }

    }

    private static void registerUser() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要注册的用户名");
        String username = sc.nextLine();
        Statement statement = connection.createStatement();
        String sql = String.format("insert into person(username) values('%s')",username);
        try {
            statement.executeUpdate(sql);
            System.out.println("注册成功");
        } catch (SQLException throwables) {
            System.out.println("用户名已被占用，请重新输入");
        }

        statement.close();

    }
}
