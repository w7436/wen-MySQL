package nancy.Dao;

import nancy.Util.DBUtil;
import nancy.exception.SystemException;
import nancy.model.teaching;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName teachingDao
 * @Description TODO
 * @Author DELL
 * @Data 2020/7/1 15:32
 * @Version 1.0
 **/
public class teachingDao {

    public static int addStudentCourse(Connection con, teaching teach) throws Exception {
        String sql = "insert into selecting values(?,?,null)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, teach.getC_Id());
        pstmt.setInt(2, teach.getT_Id());
        return pstmt.executeUpdate();
    }
    public static List<teaching> query(Connection c){
        PreparedStatement p =null;
        ResultSet r = null;
        String sql ="select * from teaching ";
        List<teaching> list = new ArrayList<teaching>();
        try {
            p = c.prepareStatement(sql);
            r = p.executeQuery();
            while(r.next()){
                teaching t = new teaching();
                t.setC_Id(r.getInt("c_Id"));
                t.setT_Id(r.getInt("t_Id"));
                t.setDate( r.getDate("date"));
                list.add(t);
            }
            return list;
        } catch (SQLException e) {
            throw new SystemException("查询出错");
        } finally {
            DBUtil.close(c,p,r);
        }

    }
 }
