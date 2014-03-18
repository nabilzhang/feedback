package our.cainiao.app.feedback.service.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import our.cainiao.app.feedback.bo.User;
import our.cainiao.app.feedback.service.UserMgr;

/**
 * 测试用户Service
 * 
 * @author zhangbi
 * @date 2014年3月15日下午5:14:18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserMgrTestCase extends
        AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private UserMgr userMgr;

    @Test
    public void testCreate() {
        userMgr.createUser(new User());
    }
}
