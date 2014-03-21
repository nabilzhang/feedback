package our.cainiao.app.feedback.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import our.cainiao.app.feedback.bo.User;
import our.cainiao.app.feedback.dao.UserDao;
import our.cainiao.app.feedback.service.UserMgr;
import our.cainiao.app.feedback.utils.MD5Util;

/**
 * 用户Service实现
 * 
 * @author zhangbi
 * @date 2014年3月15日下午5:04:59
 */
@Service
public class UserMgrImpl implements UserMgr {
    private static Logger LOG = LoggerFactory.getLogger(UserMgrImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public User createUser(User user) {
        String passwordMd5 = MD5Util.toMd5(user.getPassword());
        user.setPassword(passwordMd5);
        return userDao.save(user);
    }

    @Override
    public boolean login(User user) {
        User u = userDao.findByEmail(user.getEmail());
        String passwordMd5 = MD5Util.toMd5(user.getPassword());
        if (u != null && u.getPassword().equals(passwordMd5)) {
            LOG.info("user {} login success！", user.getEmail());
            return true;
        }
        LOG.info("user {} login failed！", user.getEmail());
        return false;
    }

    @Override
    public User getByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
