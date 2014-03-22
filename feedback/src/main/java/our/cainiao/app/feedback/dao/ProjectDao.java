package our.cainiao.app.feedback.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import our.cainiao.app.feedback.bo.Project;

/**
 * 项目Dao
 * 
 * @author zhangbi
 * @date 2014年3月20日下午7:58:03
 */
@Repository
public interface ProjectDao extends JpaRepository<Project, Long> {

    /**
     * 根据用户分页查找
     * 
     * @param createdBy
     * @param pageable
     * @return
     */
    public Page<Project> findByCreatedBy(Long createdBy, Pageable pageable);

    /**
     * 根据token查找
     * 
     * @param token
     * @return
     */
    public Project findByToken(String token);

}
