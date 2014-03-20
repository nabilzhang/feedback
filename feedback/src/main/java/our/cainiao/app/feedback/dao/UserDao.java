package our.cainiao.app.feedback.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import our.cainiao.app.feedback.bo.User;

/**
 * 
 * 用户DAO
 * 
 * @author zhangbi
 * @date 2014年3月15日下午4:59:17
 */
@Repository
public interface UserDao extends JpaRepository<User, Long> {
    /**
     * 根据email查询
     * 
     * @param email
     * @return
     */
    public User findByEmail(String email);
}
