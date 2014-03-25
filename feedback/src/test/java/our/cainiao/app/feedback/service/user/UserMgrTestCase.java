package our.cainiao.app.feedback.service.user;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import our.cainiao.app.feedback.bo.User;
import our.cainiao.app.feedback.common.TestCaseBase;
import our.cainiao.app.feedback.service.UserMgr;

/**
 * 测试用户Service
 * 
 * @author zhangbi
 * @date 2014年3月15日下午5:14:18
 */
public class UserMgrTestCase extends TestCaseBase {
    @Autowired
    private UserMgr userMgr;

    @Test
    public void testCreate() {
        User user = mockUser();
        user = userMgr.createUser(user);
        Assert.assertNotNull(user);
        Assert.assertTrue(user.getId() > 0);
    }

    @Test
    public void testLogin() {
        // 创建mockUser
        User user = mockUser();
        user = userMgr.createUser(user);

        // 测试登录
        // Assert.assertTrue(userMgr.login(user));
    }

    private User mockUser() {
        User user = new User();
        user.setUsername("user1");
        user.setPassword("a");
        user.setEmail("test@test.com");
        return user;
    }
}
