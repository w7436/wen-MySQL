package nancy.servlet;

import nancy.Dao.studentDao;
import nancy.Dao.teacherDao;
import nancy.Util.DBUtil;

import java.sql.Connection;

/**
 * @ClassName teeacherServlet
 * @Description TODO
 * @Author DELL
 * @Data 2020/7/1 15:46
 * @Version 1.0
 **/
public class teacherServlet {
    public  static boolean login(String name,String password) throws Exception {
        Connection c = DBUtil.getConnection();
        if(teacherDao.login(c,name,password)){
            return true;
        }else{
            return false;
        }
    }
    //查看个人信息
    public static void qureySelf(String name) throws Exception {
        Connection c = DBUtil.getConnection();
        System.out.println(studentDao.qustudent(c,name));
    }
    //修改密码
    public static void update(String name){
        Connection c = DBUtil.getConnection();
    }
    //查看自己所带课程的学生信息
    public static void infosudent(){

    }
    //自己所带学生的成绩
    public static void scoreSelf(){

    }

}
