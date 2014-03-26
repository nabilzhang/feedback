package our.cainiao.app.feedback.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import our.cainiao.app.feedback.bo.User;
import our.cainiao.app.feedback.constants.Constants;
import our.cainiao.app.feedback.form.user.UserLoginForm;
import our.cainiao.app.feedback.form.user.UserRegisterForm;
import our.cainiao.app.feedback.service.UserMgr;

/**
 * 用户Controller
 * 
 * @author zhangbi
 * @date 2014年3月18日下午7:59:30
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    private Logger LOG = getLogger();

    @Autowired
    private UserMgr userMgr;

    /**
     * 注册用户
     * 
     * @param user
     * @return
     */
    @RequestMapping("/register")
    @ResponseBody
    public Object register(UserRegisterForm userForm) {
        LOG.info(">> register() > Got Param : '{}'", userForm);
        // 校验
        if (StringUtils.isEmpty(userForm.getEmail())
                || StringUtils.isEmpty(userForm.getPassword())
                || StringUtils.isEmpty(userForm.getRePassword())) {
            return buildFailed("用户名或密码不能为空");
        }
        
        if (!userForm.getPassword().equals(userForm.getRePassword())) {
            return buildFailed("两遍密码不一致，请重新输入");
        }

        // 校验通过
        User user = new User();
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setUsername(userForm.getEmail());
        user = userMgr.createUser(user);
        return buildSuccess(user);
    }

    /**
     * 用户登录
     * 
     * @param user
     * @return
     */
    @RequestMapping("/logon")
    @ResponseBody
    public Object login(UserLoginForm userForm,
            HttpServletRequest request,
            HttpServletResponse response) {
        LOG.info(">> logon() > Got Param : '{}'", userForm);
        User user = userForm.getUser();
        boolean isLogin = userMgr.login(user);
        if(isLogin){
            user = userMgr.getByEmail(userForm.getEmail());
            request.getSession().setAttribute(Constants.USER_SESSION, user);
            // 记住我
            rememberMe(userForm, response, user);
            return buildSuccess(null);
        } else {
            return buildFailed("用户名或密码错误");
        }
    }

    /**
     * 记住我功能
     * 
     * @param userForm
     * @param response
     * @param user
     */
    private void rememberMe(UserLoginForm userForm,
            HttpServletResponse response,
            User user) {
        // 选了记住我则需要保留
        if (userForm.getRememberMe() != null) {
            Cookie cookie = new Cookie(Constants.REMEBER_ME_COOKIE_KEY,
                    user.getEmail());
            cookie.setMaxAge(60 * 60 * 24 * 30);// 保留30天
            cookie.setPath("/");
            response.addCookie(cookie);
        } else {
            Cookie cookie = new Cookie(Constants.REMEBER_ME_COOKIE_KEY,
                    null);
            cookie.setMaxAge(0);// 保留30天
            cookie.setPath("/");
            response.addCookie(cookie);
        }
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }
}
