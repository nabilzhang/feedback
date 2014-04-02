package our.cainiao.app.feedback.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Index
 * 
 * @author zhangbi
 * @date 2014年3月21日下午7:41:25
 */
@RequestMapping("/")
@Controller
public class IndexController {
    @RequestMapping(value = "")
    public String root(HttpServletRequest request, HttpServletResponse response) {
        return "index";
    }

    @RequestMapping(value = "index")
    public String index(HttpServletRequest request,
            HttpServletResponse response) {
        return "redirect:project";
    }
}
