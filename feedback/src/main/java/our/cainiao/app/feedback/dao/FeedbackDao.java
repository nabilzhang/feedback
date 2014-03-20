package our.cainiao.app.feedback.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import our.cainiao.app.feedback.bo.Feedback;

/**
 * 反馈Dao
 * 
 * @author zhangbi
 * @date 2014年3月20日下午7:59:14
 */
@Repository
public interface FeedbackDao extends JpaRepository<Feedback, Long> {

}
