package our.cainiao.app.feedback.template;


/**
 * Json模板
 * 
 * @author zhangbi
 * @date 2014年3月20日下午8:30:03
 */
public class BaseTemplate {
    protected final static String SUCCESS = "success";
    protected final static String ERRORMSG = "errorMsg";
    protected final static String RESULT = "result";

    public BaseTemplate(Boolean success, Object errorMsg, Object result) {
        this.success = success;
        this.errMsg = errorMsg;
        this.result = result;
    }

    public BaseTemplate() {

    }

    private Object success;
    private Object errMsg;
    private Object result;

    public Object getSuccess() {
        return success;
    }

    public void setSuccess(Object success) {
        this.success = success;
    }

    public Object getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(Object errMsg) {
        this.errMsg = errMsg;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
