package our.cainiao.app.feedback.form;
/**
 *
 *
 * @author zhangbi
 * @date 2014年3月22日上午12:16:44
 */
public class UserForm {
    private String email;
    private String password;
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
        return "UserForm [email=" + email + ", password=" + password
                + ", rePassword=" + rePassword + "]";
    }

}
