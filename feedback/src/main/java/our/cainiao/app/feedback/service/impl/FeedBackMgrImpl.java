package our.cainiao.app.feedback.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import our.cainiao.app.feedback.bo.Feedback;
import our.cainiao.app.feedback.dao.FeedbackDao;
import our.cainiao.app.feedback.service.FeedBackMgr;

/**
 * 反馈业务
 * 
 * @author zhangbi
 * @date 2014年3月20日下午9:08:48
 */
@Service
public class FeedBackMgrImpl implements FeedBackMgr {

    @Autowired
    private FeedbackDao feedbackDao;

    @Override
    public void create(Feedback feedback) {
        feedbackDao.save(feedback);
    }

    @Override
    public Page<Feedback> listFeedback(long projectId, int pageNo, int pageSize) {
        PageRequest pageRequest = new PageRequest(pageNo, pageSize);
        return feedbackDao.findByProjectId(projectId, pageRequest);
    }

}
