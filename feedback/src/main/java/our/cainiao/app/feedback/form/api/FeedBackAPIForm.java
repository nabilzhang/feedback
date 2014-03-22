package our.cainiao.app.feedback.form.api;

/**
 * 接收API请求
 * 
 * @author zhangbi
 * @date 2014年3月22日上午9:18:25
 */
public class FeedBackAPIForm {
    private String title;// 标题

    private String content;// 评论反馈内容

    private String image;// 图片

    private String token;// token

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "FeedBackAPIForm [title=" + title + ", content=" + content
                + ", image=" + image + ", token=" + token + "]";
    }

}
