package our.cainiao.app.feedback.service;

import our.cainiao.app.feedback.bo.Feedback;

/**
 * 反馈业务逻辑
 * 
 * @author zhangbi
 * @date 2014年3月20日下午8:01:58
 */
public interface FeedBackMgr {
    /**
     * 创建反馈
     * 
     * @param feedback
     */
    public void create(Feedback feedback);

}
