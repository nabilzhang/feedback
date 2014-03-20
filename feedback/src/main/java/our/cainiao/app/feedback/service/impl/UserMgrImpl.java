package our.cainiao.app.feedback.service.impl;

import java.security.MessageDigest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import our.cainiao.app.feedback.bo.User;
import our.cainiao.app.feedback.dao.UserDao;
import our.cainiao.app.feedback.service.UserMgr;

/**
 * 用户Service实现
 * 
 * @author zhangbi
 * @date 2014年3月15日下午5:04:59
 */
@Service
public class UserMgrImpl implements UserMgr {
    @Autowired
    private UserDao userDao;

    @Override
    public User createUser(User user) {
        String passwordMd5 = toMd5(user.getPassword());
        user.setPassword(passwordMd5);
        return userDao.save(user);
    }

    @Override
    public boolean login(User user) {
        User u = userDao.findByEmail(user.getEmail());
        String passwordMd5 = toMd5(user.getPassword());
        if (u.getPassword().equals(passwordMd5)) {
            return true;
        }
        return false;
    }

    /**
     * 密码加密
     * 
     * @param plainText
     * @return
     */
    private String toMd5(String plainText) {
        StringBuffer buf = new StringBuffer("");
        try {
            // 生成实现指定摘要算法的 MessageDigest 对象。
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 使用指定的字节数组更新摘要。
            md.update(plainText.getBytes());
            // 通过执行诸如填充之类的最终操作完成哈希计算。
            byte b[] = md.digest();
            // 生成具体的md5密码到buf数组
            int i;

            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buf.toString();
    }

    @Override
    public User getByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
