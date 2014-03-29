package our.cainiao.app.feedback.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.PriorityOrdered;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import our.cainiao.app.feedback.template.BaseTemplate;
import our.cainiao.app.feedback.utils.JsonUtil;

/**
 * 异常处理
 * <p>
 * 实现<code>PriorityOrdered</code> 接口，优先处理异常
 * 
 * @author zhangbi
 * @date 2014年3月29日下午4:53:40
 */
public class FeedBackHandlerExceptionaResolver extends
        AbstractHandlerExceptionResolver implements PriorityOrdered,
        ApplicationContextAware {

    private static final String FIELD_KEY = "field";
    private static final String FIELD_MESSAGE_KEY = "message";

    private ApplicationContext context;

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex) {
        // 数据绑定异常处理
        if (ex instanceof BindException) {
            BindException be = (BindException) ex;
            if (be.hasGlobalErrors()) {
                return handleGlobalError(response, be);
            }

            if (be.hasFieldErrors()) {
                return handleFieldErrors(response, be);
            }
        }
        return null;
    }

    /**
     * 处理fieldError
     * 
     * @param response
     * @param be
     * @return
     */
    private ModelAndView handleFieldErrors(HttpServletResponse response,
            BindException be) {

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (ObjectError oe : be.getFieldErrors()) {
            if (oe instanceof FieldError) {
                FieldError fe = (FieldError) oe;
                Map<String, Object> errorMap = new HashMap<String, Object>();
                errorMap.put(FIELD_KEY, fe.getField());
                errorMap.put(
                        FIELD_MESSAGE_KEY,
                        generateErrorMessage(fe.getCodes(),
                                fe.getDefaultMessage()));
                list.add(errorMap);
            }
        }
        return writeErrorMessage(response, list);
    }

    /**
     * 获取code对应Message并输出
     * 
     * @param codes
     * @param message
     * @return
     */
    private String generateErrorMessage(String[] codes, String message) {
        String finalMessage = message;
        for (String code : codes) {
            finalMessage = context.getMessage(code, null, message, null);
            if (finalMessage != null && !finalMessage.equals(message)
                    && !finalMessage.equals(code)) {
                return finalMessage;
            }
        }
        return message;
    }

    /**
     * 处理全局错误
     * 
     * @param response
     * @param be
     * @return
     */
    private ModelAndView handleGlobalError(HttpServletResponse response,
            BindException be) {
        String message = generateErrorMessage(be.getGlobalError().getCodes(),
                be.getGlobalError().getDefaultMessage());
        return writeErrorMessage(response, message);
    }

    /**
     * 写错误信息
     * 
     * @param response
     * @param message
     * @return
     */
    private ModelAndView writeErrorMessage(HttpServletResponse response,
            Object message) {
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json");
            response.getWriter().println(
                    JsonUtil.toJson(new BaseTemplate(false, message, null)));
            return new ModelAndView();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.context = applicationContext;
    }

}
