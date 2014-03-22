package our.cainiao.app.feedback.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 反馈
 * 
 * @author zhangbi
 * @date 2014年3月20日下午7:50:31
 */
@Entity
@Table(name = "feedback")
public class Feedback extends BaseBo {

    /** 标题 */
    @Column(name = "title")
    private String title;

    /** 所属项目ID */
    @Column(name = "project_id")
    private long projectId;

    /** 反馈内容 */
    @Column(name = "content")
    private String content;

    /** 图片 */
    @Column(name = "image")
    private String image;

    /** 昵称 */
    @Column(name = "nick")
    private String nick;

    /** 昵称 */
    @Column(name = "email")
    private String email;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    

}
