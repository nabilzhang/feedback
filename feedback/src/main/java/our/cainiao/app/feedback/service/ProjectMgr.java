package our.cainiao.app.feedback.service;

import org.springframework.data.domain.Page;

import our.cainiao.app.feedback.bo.Project;

/**
 * 
 * 项目业务逻辑
 * 
 * @author zhangbi
 * @date 2014年3月20日下午8:01:44
 */
public interface ProjectMgr {
    /**
     * 创建项目
     * 
     * @param project
     * @return
     */
    public Project create(Project project);

    /**
     * 读取项目
     * 
     * @param id
     * @return
     */
    public Project get(Long id);

    /**
     * 分页列表
     * 
     * @param userId
     * @return
     */
    public Page<Project> listByUser(Long userId, int page, int pageSize);

}
