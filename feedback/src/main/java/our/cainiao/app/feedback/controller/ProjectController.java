package our.cainiao.app.feedback.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import our.cainiao.app.feedback.bo.Project;
import our.cainiao.app.feedback.form.ListRequestForm;
import our.cainiao.app.feedback.service.ProjectMgr;
import our.cainiao.app.feedback.utils.ScriptUtil;

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
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public Object create(Project project, HttpServletRequest request) {
        LOG.info(">> create() > GOT PARAMS '{}','{}'", project, request);
        if (StringUtils.isEmpty(project.getName())) {
            return buildFailed("项目名称不能为空");
        }

        project.setCreatedBy(getUser(request).getId());
        project = projectMgr.create(project);
        return buildSuccess(project);
    }

    /**
     * 项目列表
     * 
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String list(ListRequestForm form,
            Model model, HttpServletRequest request) {
        Long userId = getUser(request).getId();
        Page<Project> projectPage = projectMgr.listByUser(userId,
                form.getPageNo(), form.getPageSize());
        model.addAttribute("projects", projectPage);
        model.addAttribute("success", request.getAttribute("success"));
        return "project_list";
    }

    @RequestMapping(value = "/{projectId}", method = RequestMethod.DELETE)
    @ResponseBody
    public Object delete(@PathVariable Long projectId) {
        if (projectId == null) {
            return buildFailed("参数错误");
        }
        projectMgr.delete(projectId);
        return buildSuccess(null);
    }
    
    @RequestMapping(value = "/{projectId}/script", method = RequestMethod.GET)
    @ResponseBody
    public Object getScript(@PathVariable Long projectId,
            HttpServletRequest request) {
        Project project = projectMgr.get(projectId);
        String path = request.getContextPath();
        String script = ScriptUtil.getScript(project.getToken(),
                request.getScheme() + "://" + request.getServerName() + ":"
                        + request.getServerPort() + path + "/");
        LOG.info(script);
        return buildSuccess(script);
    }

}
