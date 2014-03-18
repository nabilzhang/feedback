package our.cainiao.app.feedback.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户BO
 * 
 * @author zhangbi
 * @date 2014年3月15日下午4:54:23
 */
@Entity
@Table(name = "user")
public class User extends BaseBo {
    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User [username=" + username + ", password=" + password
                + ", email=" + email + ",]";
    }

}
