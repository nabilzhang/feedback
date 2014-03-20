package our.cainiao.app.feedback.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import our.cainiao.app.feedback.bo.Project;
import our.cainiao.app.feedback.service.ProjectMgr;

/**
 * 项目Controller
 * 
 * @author zhangbi
 * 
 */
@Controller
@RequestMapping(value = "/project")
public class ProjectController extends BaseController {

    @Autowired
    private ProjectMgr projectMgr;

    private Logger LOG = getLogger();
    
    /**
     * 创建项目
     * 
     * @param project
     * @param request
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Object create(Project project, HttpServletRequest request) {
        LOG.info(">> create() > GOT PARAMS '{}','{}'", project, request);
        project.setCreatedBy(getUser(request).getId());
        project = projectMgr.create(project);
        return buildSuccess(project);
    }

}
