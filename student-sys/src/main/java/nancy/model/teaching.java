package nancy.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @ClassName teaching
 * @Description TODO
 * @Author DELL
 * @Data 2020/7/1 15:30
 * @Version 1.0
 **/

@Setter
@Getter
@ToString
public class teaching {
    private int c_Id;
    private int t_Id;
    private Date date;
}
