package our.cainiao.app.feedback.interceptors;

import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import our.cainiao.app.feedback.bo.User;
import our.cainiao.app.feedback.constants.Constants;
import our.cainiao.app.feedback.service.UserMgr;

public class AuthInterceptor extends HandlerInterceptorAdapter {

    private static Logger LOG = LoggerFactory
            .getLogger(AuthInterceptor.class);

    String[] NO_FILTER_URLS = new String[] { "/user/login", "/user/register",
            "/user/logon" };

    @Autowired
    UserMgr userMgr;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		String uri = request.getRequestURI();
        LOG.info(request.getMethod() + ":" + uri);
		boolean beFilter = true;
		for (String s : NO_FILTER_URLS) {
			if (uri.indexOf(s) != -1) {
				beFilter = false;
				break;
			}
		}
		
		if(!beFilter){
			return super.preHandle(request, response, handler);
		}
		
        // 看是否有记住我
        // 若是有记住我的cookie,同时当前登录Session为空的时候则重新设置session
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(Constants.REMEBER_ME_COOKIE_KEY)
                        && session.getAttribute(Constants.USER_SESSION) == null) {
                    String email = cookie.getValue();
                    User user = userMgr.getByEmail(email);
                    if (user != null) {
                        session.setAttribute(Constants.USER_SESSION, user);
                    }
                    break;
                }
            }
        }

        User u = (User) session.getAttribute(Constants.USER_SESSION);
		if (u == null) {
            if (request.getMethod().equals("GET")) {
                response.sendRedirect("/user/login");

            }
			response.setCharacterEncoding("UTF-8");
	        response.setContentType("application/json;charset=UTF-8");
			PrintWriter out = response.getWriter();
            StringBuilder builder = new StringBuilder();
            builder.append("{\"success\":false,\"errMsg\":\"用户未登录\",\"result\":null}");
            out.print(builder.toString());
            out.close();
			return false;
		}
		return super.preHandle(request, response, handler);
	}
}
