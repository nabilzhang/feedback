package our.cainiao.app.feedback.interceptors;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import our.cainiao.app.feedback.bo.User;

public class AuthInterceptor extends HandlerInterceptorAdapter {

    String[] NO_FILTER_URLS = new String[] { "/user/login", "/user/register" };

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		String uri = request.getRequestURI();
		System.out.println(uri);
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
		
		User u = (User) session.getAttribute("user");
		if (u == null) {
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
