import nancy.operator.*;

import java.util.Scanner;
/**
 * @ClassName Main
 * @Description TODO
 * @Author DELL
 * @Data 2020/7/1 12:07
 * @Version 1.0
 **/
public class Main {
    public static void menu(){
        System.out.println("1、学生登录");
        System.out.println("2、教师登录");
        System.out.println("3、管理员登录");
    }
    public static void main(String[] args) throws Exception {
        System.out.print("请选择你的身份：");
        menu();
        Scanner sc = new Scanner (System.in);
        int rank = sc.nextInt();
        switch (rank){
            case 1:
                stu.select();
                break;
            case 2:
                //教师信息身份验证
                tea.select();
                break;
            case 3:
                //管理员
                man.select();
                break;
        }
    }
}
