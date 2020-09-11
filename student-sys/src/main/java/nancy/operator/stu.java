package nancy.operator;

import nancy.servlet.studentServlet;

import java.util.Scanner;

/**
 * @ClassName stu
 * @Description TODO
 * @Author DELL
 * @Data 2020/7/1 18:50
 * @Version 1.0
 **/
public class stu {
    public static void menu(){

        System.out.print("1、查看个人信息");
        System.out.print("2、修改密码");
        System.out.print("3、选课");
        System.out.print("4、查看课程");
        System.out.println("5、查看代课老师");
    }
    public static void select() throws Exception {
        Scanner sc = new Scanner(System.in);
        //进行登录操作进行身份验证
        System.out.print("请输入名字：");
        String name = sc.nextLine();
        System.out.print("请输入密码：");
        String password = sc.nextLine();
        if(studentServlet.login(name,password)){
            menu();
            System.out.println("请输入需要的业务：");
            int num = sc.nextInt();
            switch(num){
                case 1:
                    studentServlet.qureySelf(name);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                default:
                    return;
            }

        }else{
            return;
        }





    }
}
