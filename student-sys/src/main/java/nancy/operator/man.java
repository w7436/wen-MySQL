package nancy.operator;

import nancy.servlet.ManagerServlet;

import java.util.Scanner;

/**
 * @ClassName man
 * @Description TODO
 * @Author DELL
 * @Data 2020/7/1 18:50
 * @Version 1.0
 **/
public class man {
    public static void menu() {
        System.out.print("1、查看所有学生信息");
        System.out.print("2、查看所有课程信息");
    }

    public static void select() throws Exception {
        Scanner sc = new Scanner(System.in);
        //进行登录操作进行身份验证
        System.out.print("请输入名字：");
        String name = sc.nextLine();
        System.out.print("请输入密码：");
        String password = sc.nextLine();
        if (ManagerServlet.login(name, password)) {
            menu();
            System.out.println("请输入需要的业务：");
            int num = sc.nextInt();
            switch (num) {
                case 1:
                    break;
                case 2:
                    break;
                default:
                    return;
            }

        } else {
            return;
        }


    }
}
