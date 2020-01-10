package com.bit.db;

import com.com.bit.data.Course;
import com.com.bit.data.Student;
import com.com.bit.data.Class;


import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.sun.org.apache.regexp.internal.RE;

import javax.sql.DataSource;
import java.sql.*;
import java.lang.*;


/**
 * @ClassName DBOperator
 * @Description 数据操作模块
 * @Author DELL
 * @Data 2020/1/6 18:51
 * @Version 1.0
 **/
public class DBOperator{
    private static String URL="jdbc:mysql://localhost/course_system";
    private static String USER="root";
    private static String PASSWORD="123456";
    public static DataSource getDatasource(){
        MysqlDataSource dataSource=new MysqlDataSource();
        dataSource.setURL(URL);
        dataSource.setUser(USER);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }

    //     1、插入学生表
    //     2、查询学生表
    public static void insertStudent(Student stu){
        //获取数据源
        Connection connection=null;
        PreparedStatement preparedStatement=null;

        try {
            connection =getDatasource().getConnection();
            String sql="insert into student values(?,?,?)";

            preparedStatement=connection.prepareStatement(sql);

            preparedStatement.setInt(1,stu.getSn());
            preparedStatement.setString(2,stu.getName());
            preparedStatement.setInt(3,stu.getClassid());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement!=null){
                    preparedStatement.close();
                }
                if (connection!=null){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static Student selectStudent(int sn){
        //获取数据源
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        Student student=new Student();
        try {
            connection=getDatasource().getConnection();
            String sql="select * from student where sn=?";
            preparedStatement=connection.prepareStatement(sql);

            preparedStatement.setInt(1,sn);

            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                student.setSn(resultSet.getInt("sn"));
                student.setName(resultSet.getString("name"));
                student.setClassid(resultSet.getInt("classid"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet!=null){
                    resultSet.close();
                }
                if (preparedStatement!=null){
                    preparedStatement.close();
                }
                if (connection!=null){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return student;
    }


    //      3、插入班级表
    //      4、查询班级表
    public static void insertClass(Class cl){
        Connection connection=null;
        PreparedStatement preparedStatement=null;

        try {
            connection=getDatasource().getConnection();
            String sql="insert into class values(?,?)";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,3);
            preparedStatement.setString(2,"computer3");
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement!=null){
                    preparedStatement.close();
                }
                if (connection!=null){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Class selectClass(int id){
        //获取数据源
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        Class aClass=new Class();
        try {
            connection=getDatasource().getConnection();
            String sql="select * from class where id=?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                aClass.setId(resultSet.getInt("id"));
                aClass.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet!=null){
                    resultSet.close();
                }
                if (preparedStatement!=null){
                    preparedStatement.close();
                }
                if (connection!=null){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return aClass;
    }

    //   5、插入课程表
    public static void insertCourse(Course course){
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            connection=getDatasource().getConnection();
            String sql="insert into course values(?,?,?)";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,course.getId());
            preparedStatement.setString(2,course.getName());
            preparedStatement.setInt(3,course.getCredit());

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement!=null){
                    preparedStatement.close();
                }
                if (connection!=null){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //    6、查询课程表
    public static Course selectCourse(String name){
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        Course course=new Course();
        try {
            connection=getDatasource().getConnection();
            String sql="select * from course where name=?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                course.setId(resultSet.getInt("id"));
                course.setName(resultSet.getString("name"));
                course.setCredit(resultSet.getInt("credit"));
                System.out.println(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet!=null){
                    resultSet.close();
                }
                if (preparedStatement!=null){
                    preparedStatement.close();
                }
                if (connection!=null){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return course;
    }


    //      7、插入教室表
    //      8、查询教师表


     /* 9、插入take
     * 10、查询take

     * 11、插入teach
     * 12、查询teach
     * */


}
