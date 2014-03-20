package our.cainiao.app.feedback.service;

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

}
