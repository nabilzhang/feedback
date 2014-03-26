package our.cainiao.app.feedback.form.feedback;

import our.cainiao.app.feedback.form.common.ListRequestForm;

/**
 * 反馈列表
 * 
 * @author zhangbi
 * @date 2014年3月22日上午10:46:42
 */
public class FeedbackListForm extends ListRequestForm {
    private Long projectId;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

}
