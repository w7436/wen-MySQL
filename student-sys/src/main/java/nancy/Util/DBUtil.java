package nancy.Util;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @ClassName DButil
 * @Description TODO
 * @Author DELL
 * @Data 2020/7/1 11:21
 * @Version 1.0
 **/
public class DBUtil {
    private static volatile DataSource DS;

    private static final String URL="jdbc:mysql://localhost:3306/stu_sys";
    private static final String USERNAME="root";
    private static final String PASSWORD ="123456";


    //单例模式：双重校验锁
    private static DataSource getDataSource(){
        if(DS==null){
            synchronized (DBUtil.class){
                if(DS==null){
                    DS = new MysqlDataSource();
                    ((MysqlDataSource) DS).setUrl(URL);
                    ((MysqlDataSource) DS).setUser(USERNAME);
                    ((MysqlDataSource) DS).setPassword(PASSWORD);
                }
            }
        }
        return DS;
    }

    public static Connection getConnection()  {
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            throw new nancy.exception.SystemException(e,"数据库连接");
        }

    }

    //释放资源操作：数据库服务请求/响应也是基于网络数据传输的，也就是网络IO
    //插入/删除/修改不需要释放ResultSet
    public static void close(Connection c, Statement s){
        close(c,s,null);
    }

    public static void close(Connection c, Statement s, ResultSet r){
        try {
            if(c != null){
                c.close();
            }
            if( s!= null){
                s.close();
            }
            if(r != null){
                r.close();
            }
        } catch (SQLException e) {
            throw new nancy.exception.SystemException(e,"释放数据库资源失败");

        }
    }
}
