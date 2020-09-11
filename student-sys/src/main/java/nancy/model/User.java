package nancy.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName User
 * @Description TODO
 * @Author DELL
 * @Data 2020/7/1 12:56
 * @Version 1.0
 **/
@Setter
@Getter
@ToString
public class User {
    private Integer userId;  // 用户ID
    private String userName;  // 用户名
    private String password; // 密码
    private String userType; // 用户类型

}
