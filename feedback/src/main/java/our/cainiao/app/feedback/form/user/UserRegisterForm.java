package our.cainiao.app.feedback.form.user;

import org.hibernate.validator.constraints.NotBlank;

/**
 *
 *
 * @author zhangbi
 * @email zhangbi@baidu.com
 * @date 2014年3月26日下午8:29:29
 */
public class UserRegisterForm {
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String rePassword;


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

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    @Override
    public String toString() {
        return "UserRegisterForm [email=" + email + ", password=" + password
                + ", rePassword=" + rePassword + "]";
    }

}
