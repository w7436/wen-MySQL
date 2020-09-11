package nancy.Dao;

import nancy.model.selecting;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @ClassName selectingDao
 * @Description TODO
 * @Author DELL
 * @Data 2020/7/1 15:32
 * @Version 1.0
 **/
public class selectingDao {

    //选课信息
    public static int addStudentCourse(Connection con, selecting sc) throws Exception {
        String sql = "insert into selecting values(?,?,null)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, sc.getC_Id());
        pstmt.setInt(2, sc.getS_Id());
        return pstmt.executeUpdate();
    }
//    select student.name,student.sex,student.password,student.depart,student.phone,
//    course.name,course.credit,teacher.name
//    from student,course,teacher,teaching,selecting
//    where selecting.s_Id = student.Id and selecting.c_Id = course.Id
//    and teaching.c_Id = course.Id and teaching.t_Id = teacher.Id;
    //查看自己选课信息



}



