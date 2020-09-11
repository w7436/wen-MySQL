package nancy.Dao;

import nancy.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @ClassName ManagerDao
 * @Description TODO
 * @Author DELL
 * @Data 2020/7/1 14:53
 * @Version 1.0
 **/
public class ManagerDao {
    public static boolean login(Connection con,String name,String password)throws Exception{
        User resultUser=null;
        String sql="select * from manager where userName=? and password=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,name);
        pstmt.setString(2, password);
        ResultSet rs=pstmt.executeQuery();
        if(!rs.next()){
            System.out.println("登录失败");
            return false;
        }else{
            System.out.println("登陆成功");
            return true;
        }

    }
}
