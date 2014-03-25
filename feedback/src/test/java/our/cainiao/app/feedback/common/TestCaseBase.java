package our.cainiao.app.feedback.common;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * 测试基类
 * 
 * @author zhangbi
 * @date 2014年3月18日下午7:21:55
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public abstract class TestCaseBase extends
        AbstractTransactionalJUnit4SpringContextTests {

}
