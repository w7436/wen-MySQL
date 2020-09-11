package nancy.Dao;

import nancy.Util.DBUtil;
import nancy.exception.SystemException;
import nancy.model.student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName studentDao
 * @Description TODO
 * @Author DELL
 * @Data 2020/7/1 12:55
 * @Version 1.0
 **/
public class studentDao {
    /**
     * 学生登录
     * @param user
     * @return
     * @throws Exception
     */
    public static boolean login(Connection con,String name,String password)throws Exception{

        String sql="select * from student where name=? and password=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,name);
        pstmt.setString(2,password);
        ResultSet rs=pstmt.executeQuery();
        if(!rs.next()){
            System.out.println("登录失败");
            return false;
        }else{
            System.out.println("登录成功");
            return true;
        }

    }



    //学生信息查询
    public static List<student> queryList(Connection c){
        PreparedStatement p =null;
        ResultSet r = null;
        String sql ="select * from student ";
        List<student> list = new ArrayList<student>();
        try {
            p = c.prepareStatement(sql);
            r = p.executeQuery();
            while(r.next()){
                student stu = new student();
                stu.setId(r.getInt("Id"));
                stu.setName(r.getString("name"));
                stu.setSex(r.getString("sex"));
                stu.setBirthday(r.getDate("bithday"));
                stu.setPassword(r.getString("password"));
                stu.setDepart(r.getString("depart"));
                stu.setPhone(r.getString("phone"));
                stu.setEmail(r.getString("email"));
                list.add(stu);
            }
            return list;
        } catch (SQLException e) {
            throw new SystemException("查询出错");
        } finally {
            DBUtil.close(c,p,r);
        }
    }
    //学生添加
    public static int studentAdd(Connection con,student stu)throws Exception{
        String sql="insert into student values(null,?,?,?,?,?,?,?)";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, stu.getName());
        pstmt.setString(2, stu.getSex());
        pstmt.setDate(3, (Date) stu.getBirthday());
        pstmt.setString(4,stu.getPassword());
        pstmt.setString(5, stu.getDepart());
        pstmt.setString(6, stu.getPhone());
        pstmt.setString(7,stu.getEmail());
        return pstmt.executeUpdate();
    }

    //更改学生信息
    public static int studentUpdate(Connection con,student stu)throws Exception{
        String sql="update student set name=?,sex=?,birthday=?,password=?,depart=?,phone = ?,email = ?where Id=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, stu.getName());
        pstmt.setString(2, stu.getSex());
        pstmt.setDate(3, (Date)stu.getBirthday());
        pstmt.setString(4, stu.getPassword());
        pstmt.setString(5, stu.getDepart());
        pstmt.setString(6, stu.getPhone());
        pstmt.setString(7, stu.getEmail());
        pstmt.setInt(8, stu.getId());
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
        String sql="delete from student where Id=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setInt(1, id);
        return pstmt.executeUpdate();
    }

    //根据姓名查询个人信息
    public static student qustudent(Connection c,String name)throws Exception{
        PreparedStatement p =null;
        ResultSet r = null;
        String sql ="select * from student where name = ? ";
        student stu =null;
        try {
            p = c.prepareStatement(sql);
            p.setString(1,name);
            r = p.executeQuery();
                stu.setId(r.getInt("id"));
                stu.setName(name);
                stu.setSex(r.getString("sex"));
                stu.setBirthday(r.getDate("birthday"));
                stu.setPassword(r.getString("password"));
                stu.setDepart(r.getString("depart"));
                stu.setPhone(r.getString("phone"));
                stu.setEmail(r.getString("email"));
                return stu;
        } catch (SQLException e) {
            throw new SystemException("查询出错");
        } finally {
            DBUtil.close(c,p,r);
        }
    }






}
