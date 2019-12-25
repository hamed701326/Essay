package ir.science.essay.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="article_id")
    private int id;
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String brief;
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Date createDate;
    @Column
    private Date lastUpdateDate;
    @Column()
    private Date publishDate;
    @Column(nullable = false,columnDefinition = "boolean default false")
    private boolean isPublished;

    @ManyToOne
    private User user;
    public Article() {
    }

    public Article(String title, String brief,
                   String content, Date createDate,
                   Date lastUpdateDate, Date publishDate,
                   boolean isPublished) {
        this.title = title;
        this.brief = brief;
        this.content = content;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.publishDate = publishDate;
        this.isPublished = isPublished;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }
}
