package our.cainiao.app.feedback.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * 项目
 * 
 * @author zhangbi
 * @date 2014年3月20日下午7:46:41
 */
@Entity
@Table(name = "project")
public class Project extends BaseBo {
    /** 项目名称 */
    @Column(name = "name")
    private String name;

    /** 项目描述 */
    @Column(name = "abstract_content")
    private String abstractContent;

    /** 唯一识别token */
    @Column(name = "token")
    private String token;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbstractContent() {
        return abstractContent;
    }

    public void setAbstractContent(String abstractContent) {
        this.abstractContent = abstractContent;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Project [name=" + name + ", abstractContent=" + abstractContent
                + ", token=" + token + "]";
    }

}
