package our.cainiao.app.feedback.utils;

import java.util.Date;

/**
 *
 *
 * @author zhangbi
 * @email zhangbi@baidu.com
 * @date 2014年4月9日下午4:24:15
 */
public abstract class TokenUtil {

    public static final String getTokenId(int length) {
        if (0 == length) {
            return "";
        }

        Date tt = new Date();
        String mTimeStr = "" + tt.getTime();
        int realLength = mTimeStr.length();
        if (realLength >= length) {
            return mTimeStr.substring(0, length);
        }

        String tempString = "";
        String randomString = mTimeStr;

        while (realLength < length) {
            tempString = "" + Math.random();
            randomString += tempString.substring(2);
            realLength = randomString.length();
        }

        return randomString.substring(0, length);
    }

    /**
     * 获取UUID
     * 
     * @return
     */
    public static final String generateToken() {
        return java.util.UUID.randomUUID().toString();
    }
}