package our.cainiao.app.feedback.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import our.cainiao.app.feedback.bo.User;
import our.cainiao.app.feedback.form.UserForm;
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
    public Object register(UserForm userForm) {
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
    public Object login(User user, HttpServletRequest request) {
        LOG.info(">> logon() > Got Param : '{}'", user);
        boolean isLogin = userMgr.login(user);
        if(isLogin){
            return buildSuccess(null);
        } else {
            return buildFailed("用户名或密码错误");
        }
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }
}
