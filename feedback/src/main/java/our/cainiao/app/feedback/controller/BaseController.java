package our.cainiao.app.feedback.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import our.cainiao.app.feedback.bo.User;
import our.cainiao.app.feedback.template.BaseTemplate;

public class BaseController {
	private Logger logger = null;

	protected Logger getLogger() {
		if (null == logger) {
            logger = LoggerFactory.getLogger(this.getClass());
		}
		return logger;
	}

	/**
	 * 获取当前访问用户
	 * 
	 * @param request
	 * @return
	 */
	protected User getUser(HttpServletRequest request){
		User user = (User)request.getSession().getAttribute("user");
		return user;
		
	}
	
    /**
     * 成功的结果
     * 
     * @param result
     * @return
     */
    protected BaseTemplate buildSuccess(Object result) {
        BaseTemplate baseTemplate = new BaseTemplate();
        baseTemplate.setSuccess(true);
        baseTemplate.setResult(result);
        return baseTemplate;
    }

    /**
     * 成功的结果
     * 
     * @param result
     * @return
     */
    protected BaseTemplate buildFailed(Object errMsg) {
        BaseTemplate baseTemplate = new BaseTemplate();
        baseTemplate.setSuccess(false);
        baseTemplate.setErrMsg(errMsg);
        return baseTemplate;
    }
}
