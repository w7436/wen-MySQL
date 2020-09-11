package nancy.exception;

/**
 * @ClassName SystemException
 * @Description TODO
 * @Author DELL
 * @Data 2020/6/17 9:54
 * @Version 1.0
 **/
public class BaseException extends RuntimeException {//运行时异常也就是非受查异常

    public BaseException(Throwable cause, String message) {
        super(message);
    }
}
