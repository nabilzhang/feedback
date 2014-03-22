package our.cainiao.app.feedback.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import our.cainiao.app.feedback.bo.Project;
import our.cainiao.app.feedback.dao.ProjectDao;
import our.cainiao.app.feedback.service.ProjectMgr;
import our.cainiao.app.feedback.utils.MD5Util;

/**
 * 
 * 项目业务实现
 * 
 * @author zhangbi
 * @date 2014年3月20日下午8:56:45
 */
@Service
public class ProjectMgrImpl implements ProjectMgr {

    @Autowired
    private ProjectDao projectDao;

    @Override
    public Project create(Project project) {
        project = projectDao.save(project);
        // 生成token
        String token = MD5Util.toMd5(project.getName() + project.getId());
        project.setToken(token);
        project = projectDao.save(project);
        return project;
    }

    @Override
    public Project get(Long id) {
        return projectDao.getOne(id);
    }

    @Override
    public Page<Project> listByUser(Long userId, int page, int pageSize) {
        PageRequest pageRequest = new PageRequest(page, pageSize);

        return projectDao.findByCreatedBy(userId, pageRequest);
    }

    @Override
    public Project getByToken(String token) {
        return projectDao.findByToken(token);
    }

    @Override
    public void delete(Long projectId) {
        projectDao.delete(projectId);
    }

}
