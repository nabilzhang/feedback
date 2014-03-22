package our.cainiao.app.feedback.service;

import org.springframework.data.domain.Page;

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

    /**
     * 查找反馈列表
     * 
     * @param projectId
     * @return
     */
    public Page<Feedback> listFeedback(long projectId, int pageNo, int pageSize);

}
