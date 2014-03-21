package our.cainiao.app.feedback.form;

/**
 * list请求
 * 
 * @author zhangbi
 * @date 2014年3月21日下午11:22:33
 */
public class ListRequestForm {
    private int pageNo = 1;

    private int pageSize = 20;

    public int getPageNo() {
        return pageNo - 1;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
