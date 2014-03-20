package our.cainiao.app.feedback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import our.cainiao.app.feedback.bo.Feedback;
import our.cainiao.app.feedback.service.FeedBackMgr;

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

    @RequestMapping(value = "/feedback", method = RequestMethod.POST)
    @ResponseBody
    public Object feedback(Feedback feedback) {
        feedBackMgr.create(feedback);
        return buildSuccess(null);
    }
}
