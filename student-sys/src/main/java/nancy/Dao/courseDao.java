package nancy.Dao;

import nancy.Util.DBUtil;
import nancy.exception.SystemException;
import nancy.model.course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName courseDao
 * @Description TODO
 * @Author DELL
 * @Data 2020/7/1 15:43
 * @Version 1.0
 **/
public class courseDao {

    //查询课程
    public static List<course> query_course(Connection c){

        PreparedStatement p =null;
        ResultSet r = null;
        String sql ="select id,name,credit " +
                "from course" ;
        List<course> list = new ArrayList<course>();
        try {
            p = c.prepareStatement(sql);
            r = p.executeQuery();
            while(r.next()){
                course cou = new course();
                cou.setId(r.getInt("Id"));
                cou.setName(r.getString("name"));
                cou.setCredit(r.getInt("credit"));
                list.add(cou);
            }
            return list;
        } catch (SQLException e) {
            throw new SystemException("查询出错");
        } finally {
            DBUtil.close(c,p,r);
        }
    }

//    添加课程信息
    public static int courseAdd(Connection con,course cou)throws Exception{
        String sql="insert into course values(null,?,?,?)";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, cou.getName());
        pstmt.setInt(2, cou.getCredit());
        return pstmt.executeUpdate();
    }

    //更新课程信息
    public static int courseUpdate(Connection con,course cou)throws Exception{
        String sql="update course set name=?,credit=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, cou.getName());
        pstmt.setInt(2, cou.getCredit());
        return pstmt.executeUpdate();
    }
    //删除课程信息
    public static int courseDelete(Connection con,String id)throws Exception{
        String sql="delete from course where id=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, id);
        return pstmt.executeUpdate();
    }



}
