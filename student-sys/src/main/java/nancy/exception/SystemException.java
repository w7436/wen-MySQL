package nancy.exception;

/**
 * @ClassName SystemException
 * @Description TODO
 * @Author DELL
 * @Data 2020/6/17 9:56
 * @Version 1.0
 **/
public class SystemException extends BaseException{
    public SystemException(String message) {
        this(null,message);
    }
    public SystemException(Throwable cause,String message) {
        super(cause,"系统异常"+message);
    }
}
