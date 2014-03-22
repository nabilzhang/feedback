package our.cainiao.app.feedback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import our.cainiao.app.feedback.bo.Feedback;
import our.cainiao.app.feedback.form.ListRequestForm;
import our.cainiao.app.feedback.service.FeedBackMgr;

/**
 * 反馈
 * 
 * @author zhangbi
 * @date 2014年3月22日上午10:06:50
 */
@Controller
@RequestMapping(value = "/feedbacks")
public class FeedBackController extends BaseController {

    @Autowired
    private FeedBackMgr feedBackMgr;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String list(@PathVariable long projectId, ListRequestForm form,
            Model model) {
        Page<Feedback> feedbackPage = feedBackMgr.listFeedback(projectId,
                form.getPageNo(), form.getPageSize());
        model.addAttribute("page", feedbackPage);
        return "feedback_list";
    }
}
