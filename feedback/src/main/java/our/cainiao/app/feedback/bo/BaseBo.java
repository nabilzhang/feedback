package our.cainiao.app.feedback.bo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * bo基类
 * 
 * @author zhangbi
 * @date 2014年3月18日下午7:23:17
 */
@MappedSuperclass
public class BaseBo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime = new Date();

    /**
     * Returns the identifier of the entity.
     * 
     * @return the id
     */
    public Long getId() {
        return id;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (this.id == null || obj == null
                || !(this.getClass().equals(obj.getClass()))) {
            return false;
        }

        BaseBo that = (BaseBo) obj;

        return this.id.equals(that.getId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }
}
