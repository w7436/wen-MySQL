package nancy.servlet;

import nancy.Dao.courseDao;
import nancy.Dao.studentDao;
import nancy.Dao.teachingDao;
import nancy.Util.DBUtil;
import nancy.model.course;
import nancy.model.teaching;

import java.sql.Connection;
import java.util.List;

/**
 * @ClassName stuervlet
 * @Description TODO
 * @Author DELL
 * @Data 2020/7/1 15:45
 * @Version 1.0
 **/
public class studentServlet {


    public  static boolean login(String name,String password) throws Exception {
        Connection c = DBUtil.getConnection();
        if(studentDao.login(c,name,password)){
            return true;
        }else{
            return false;
        }
    }

    //查看课程信息
    public  static void courseList(){
        Connection c = DBUtil.getConnection();
        List<course> l = courseDao.query_course(c);
        for(int i = 0;i < l.size();i++){
            System.out.println(l.get(i));
        }
    }
    //查看授课信息
    public static void teachList(){
        Connection c = DBUtil.getConnection();
        List<teaching> l = teachingDao.query(c);
        for(int i = 0;i < l.size();i++){
            System.out.println(l.get(i));
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








}
