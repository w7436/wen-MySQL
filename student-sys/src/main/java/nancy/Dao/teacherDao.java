package nancy.Dao;

import nancy.model.teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @ClassName teacherDao
 * @Description TODO
 * @Author DELL
 * @Data 2020/7/1 14:54
 * @Version 1.0
 **/
public class teacherDao {
    public static  boolean login(Connection con,String name,String password)throws Exception{

        String sql="select * from teacher where name=? and password=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, name);
        pstmt.setString(2, password);
        ResultSet rs=pstmt.executeQuery();
        if(!rs.next()) {
            System.out.println("登录失败");
            return false;
        }else{
            System.out.println("登录成功");
            return true;
        }

    }

//    //学生信息查询
//    public static List<teacher> queryList(Connection c){
//        PreparedStatement p =null;
//        ResultSet r = null;
//        String sql ="select * from teacher ";
//        List<teacher> list = new ArrayList<teacher>();
//        try {
//            p = c.prepareStatement(sql);
//            r = p.executeQuery();
//            while(r.next()){
//                teacher tea = new teacher();
//                tea.setName(r.getString("name"));
//                tea.setSex(r.getString("sex"));
//                tea.setTask(r.getString("task"));
//                tea.setPhone(r.getString("phone"));
//                tea.setPassword(r.getString("password"));
//                tea.setEmail(r.getString("email"));
//                list.add(tea);
//            }
//            return list;
//        } catch (SQLException e) {
//            throw new SystemException("查询出错");
//        } finally {
//            DBUtil.close(c,p,r);
//        }
//    }
    //学生添加
    public static int studentAdd(Connection con,teacher tea)throws Exception{
        String sql="insert into teacher values(null,?,?,?,?,?,?)";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, tea.getName());
        pstmt.setString(2, tea.getSex());
        pstmt.setString(3,tea.getTask());
        pstmt.setString(4,tea.getPhone());
        pstmt.setString(5,tea.getPassword());
        pstmt.setString(6,tea.getEmail());
        return pstmt.executeUpdate();
    }

    //更改学生信息
    public static int studentUpdate(Connection con,teacher tea)throws Exception{
        String sql="update teacher set name=?,sex=?,task=?,phone = ?,password=?,email = ?where Id=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, tea.getName());
        pstmt.setString(2, tea.getSex());
        pstmt.setString(3,tea.getTask());
        pstmt.setString(4,tea.getPhone());
        pstmt.setString(5,tea.getPassword());
        pstmt.setString(6,tea.getEmail());
        pstmt.setInt(7,tea.getId());
        return pstmt.executeUpdate();
    }

    /**
     * 学生删除
     * @param con
     * @param id
     * @return
     * @throws Exception
     */
    public static int studentDelete(Connection con,int id)throws Exception{
        String sql="delete from teacher where Id=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setInt(1, id);
        return pstmt.executeUpdate();
    }

}
