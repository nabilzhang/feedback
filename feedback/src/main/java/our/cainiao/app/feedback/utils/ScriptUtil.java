package our.cainiao.app.feedback.utils;
/**
 *
 *
 * @author zhangbi
 * @date 2014年3月22日下午4:00:24
 */
public class ScriptUtil {
    

    public static String getScript(String token, String host) {
        String scriptTemplate = "<link href=\"" + host
                + "css/feedback.css\" rel=\"stylesheet\" type=\"text/css\" />"
                + "<script src=\"" + host + "js/fabric.min.js\"></script>"
                + "<script src=\"" + host + "js/html2canvas.js\"></script>"
                + "<script src=\"" + host + "js/feedback.js\"></script>"
                + "<script type=\"text/javascript\">"
                + "    Feedback({"
                + "        url: '" + host + "/api/feedback',"
                + "        apikey: '" + token + "'"
                + "    });" + "</script>";
        return scriptTemplate;
    }
}
