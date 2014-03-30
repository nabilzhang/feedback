package our.cainiao.app.feedback.form.user;

import org.hibernate.validator.constraints.NotBlank;

import our.cainiao.app.feedback.bo.User;

/**
 * 登录form
 * 
 * @author zhangbi
 * @date 2014年3月22日上午12:16:44
 */
public class UserLoginForm {
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    private String rememberMe;// 登录的记住我

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(String rememberMe) {
        this.rememberMe = rememberMe;
    }

    /**
     * 根据form生成User对象
     * 
     * @return
     */
    public User getUser() {
        User user = new User();
        user.setEmail(this.getEmail());
        user.setPassword(this.password);
        return user;
    }

    @Override
    public String toString() {
        return "UserLoginForm [email=" + email + ", password=" + password
                + ", rememberMe=" + rememberMe + "]";
    }



}
