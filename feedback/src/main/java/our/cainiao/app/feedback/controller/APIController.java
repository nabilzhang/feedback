package our.cainiao.app.feedback.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import our.cainiao.app.feedback.bo.Feedback;
import our.cainiao.app.feedback.bo.Project;
import our.cainiao.app.feedback.form.api.FeedBackAPIForm;
import our.cainiao.app.feedback.service.FeedBackMgr;
import our.cainiao.app.feedback.service.ProjectMgr;

/**
 * API
 * 
 * @author zhangbi
 * @date 2014年3月20日下午9:02:59
 */
@Controller
@RequestMapping(value = "/api")
public class APIController extends BaseController {

    @Autowired
    private FeedBackMgr feedBackMgr;

    @Autowired
    private ProjectMgr projectMgr;

    @RequestMapping(value = "/feedback", method = RequestMethod.POST)
    @ResponseBody
    public Object feedback(FeedBackAPIForm feedBackAPIForm,
            HttpServletResponse response) {
        // 跨域
        accessControll(response);

        // 校验
        // token
        if (StringUtils.isEmpty(feedBackAPIForm.getToken())) {
            return buildFailed("Token错误");
        }
        Project project = projectMgr.getByToken(feedBackAPIForm.getToken());
        if (null == project) {
            return buildFailed("Token错误");
        }
        // 标题
        if (StringUtils.isEmpty(feedBackAPIForm.getTitle())) {
            return buildFailed("标题不能为空");
        }

        // 构造对象
        Feedback feedback = new Feedback();
        feedback.setContent(feedBackAPIForm.getContent());
        feedback.setTitle(feedBackAPIForm.getTitle());
        feedback.setImage(feedBackAPIForm.getImage());
        feedback.setProjectId(project.getId());

        feedBackMgr.create(feedback);

        return buildSuccess(null);
    }

    /**
     * 支持跨域
     * 
     * @param response
     */
    private void accessControll(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Allow", "POST");
    }
}
