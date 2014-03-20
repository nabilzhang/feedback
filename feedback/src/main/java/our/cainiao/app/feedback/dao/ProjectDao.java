package our.cainiao.app.feedback.dao;

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

}
