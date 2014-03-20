package our.cainiao.app.feedback.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import our.cainiao.app.feedback.bo.User;
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
    public Object register(User user) {
        LOG.info(">> register() > Got Param : '{}'", user);
        user = userMgr.createUser(user);
        return buildSuccess(user);
    }

    /**
     * 用户登录
     * 
     * @param user
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public Object login(User user, HttpServletRequest request) {
        LOG.info(">> login() > Got Param : '{}'", user);
        boolean isLogin = userMgr.login(user);
        if(isLogin){
            return buildSuccess(null);
        } else {
            return buildFailed(null);
        }
    }
}
