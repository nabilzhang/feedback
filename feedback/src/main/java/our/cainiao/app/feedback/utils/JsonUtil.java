package our.cainiao.app.feedback.utils;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * JSON生成
 * 
 * @author zhangbi
 * @date 2014年3月29日下午5:52:16
 */
public class JsonUtil {
    public static String toJson(Object o) {
        
        ObjectMapper objectMapper = new ObjectMapper();

        String jsonString = "";
        try {
            jsonString = objectMapper.writeValueAsString(o);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonString;

    }
}
