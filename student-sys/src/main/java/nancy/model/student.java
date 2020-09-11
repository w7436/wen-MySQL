package nancy.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @ClassName student
 * @Description TODO
 * @Author DELL
 * @Data 2020/7/1 11:15
 * @Version 1.0
 **/
@Setter
@Getter
@ToString
public class student {
    private int id;
    private String name;
    private String sex;
    private String password;
    private Date birthday;
    private String depart;
    private String phone;
    private String email;

}
