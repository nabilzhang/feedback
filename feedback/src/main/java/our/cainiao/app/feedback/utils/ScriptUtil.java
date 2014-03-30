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
                + "\n<script src=\"" + host + "js/fabric.min.js\"></script>"
                + "\n<script src=\"" + host + "js/html2canvas.js\"></script>"
                + "\n<script src=\"" + host + "js/feedback.js\"></script>"
                + "\n<script type=\"text/javascript\">\n" + "    Feedback({\n"
                + "        url: '" + host + "/api/feedback',\n"
                + "        apikey: '" + token + "'\n" + "    });"
                + "\n</script>";
        return scriptTemplate;
    }
}
