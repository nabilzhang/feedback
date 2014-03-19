package our.cainiao.app.feedback.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/register")
    public Object register(User user) {
        LOG.info(">> register() > Got Param : '{}'", user);
        return userMgr.createUser(user);
    }
}
