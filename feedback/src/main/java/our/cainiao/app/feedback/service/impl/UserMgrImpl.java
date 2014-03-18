package our.cainiao.app.feedback.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import our.cainiao.app.feedback.bo.User;
import our.cainiao.app.feedback.dao.UserDao;
import our.cainiao.app.feedback.service.UserMgr;

/**
 * 用户Service实现
 * 
 * @author zhangbi
 * @date 2014年3月15日下午5:04:59
 */
@Service
public class UserMgrImpl implements UserMgr {
    @Autowired
    private UserDao userDao;

    @Override
    public User createUser(User user) {
        return userDao.save(user);
    }
}
