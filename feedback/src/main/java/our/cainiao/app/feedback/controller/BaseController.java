package our.cainiao.app.feedback.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;

import our.cainiao.app.feedback.bo.User;

public class BaseController {
	private Logger logger = null;

	protected Logger getLogger() {
		if (null == logger) {
			logger = org.slf4j.LoggerFactory.getLogger(this.getClass());
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
}
