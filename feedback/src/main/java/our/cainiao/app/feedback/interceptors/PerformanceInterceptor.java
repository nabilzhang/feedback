package our.cainiao.app.feedback.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 性能监控打日志
 * 
 * @author zhangbi
 * @date 2014年4月10日下午9:38:19
 */
public class PerformanceInterceptor extends HandlerInterceptorAdapter {
    private Logger LOG = LoggerFactory.getLogger(PerformanceInterceptor.class);

    // 用户记录开始时间,现成安全
    private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>(
            "Watch-StartTime");

    @Override
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object hanler, Exception ex)
            throws Exception {
        long startTime = startTimeThreadLocal.get();

        // 对于controller映射 这里打印controller类名和方法名称
        // 其余类型打印请求的url
        String trace = "";
        if (hanler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) hanler;
            trace = handlerMethod.getBeanType().getCanonicalName() + "."
                    + handlerMethod.getMethod().getName();
        } else {
            trace = request.getRequestURI();
        }

        dumpPerformanceData(startTime, trace);
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        startTimeThreadLocal.set(startTime);
        return true;
    }

    /**
     * 打印日志
     * 
     * @param startTime
     * @param trace
     */
    private void dumpPerformanceData(long startTime, String trace) {

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;

        LOG.info(String.format("TOTAL\t%d\t%s\t%d\t%d\t%d", startTime, trace,
                startTime, endTime, totalTime));
    }
}
