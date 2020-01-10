package com.bit.main;

import com.bit.db.DBOperator;

import com.com.bit.data.Student;
import com.com.bit.data.Class;
import com.com.bit.data.Course;


/**
 * @ClassName courseSystem
 * @Description TODO
 * @Author DELL
 * @Data 2020/1/6 19:16
 * @Version 1.0
 **/
public class courseSystem {
    public static void main(String[] args) {
 //       Student stu = new Student();
//        stu.setSn(202001);
//        stu.setName("小红");
//        stu.setClassid(1);


//        DBOperator.insertStudent(stu);
//        Student stu1=DBOperator.selectStudent(202001);
//        System.out.println(stu1);


//
//        Class cl=new Class();
//        cl.setId(3);
//        cl.setName("computer3");
//        DBOperator.insertClass(cl);
//        Class ca=DBOperator.selectClass(2);
//        System.out.println(ca);

//        Course course=new Course();
//        course.setId(6);
//        course.setName("大学物理");
//        course.setCredit(2);
//        DBOperator.insertCourse(course);
//
        Course course1=DBOperator.selectCourse("大学英语");
        System.out.println(course1);


    }
}
