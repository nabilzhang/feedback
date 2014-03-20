package our.cainiao.app.feedback.service;

import our.cainiao.app.feedback.bo.User;

/**
 * 
 * 用户service
 * 
 * @author zhangbi
 * @date 2014年3月15日下午5:04:14
 */
public interface UserMgr {
    /**
     * 创建一个User
     * 
     * @param user
     *            BO对象
     * @return
     */
    User createUser(User user);

    /**
     * 用户登录校验
     * 
     * @param user
     * @return
     */
    boolean login(User user);

    /**
     * 根据email获取用户
     * 
     * @param email
     * @return
     */
    User getByEmail(String email);
}
