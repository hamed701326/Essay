package ir.science.essay.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_name",nullable = false)
    private String userName;

    @Column(name = "national_id",nullable = false)
    private String nationalCode;

    @Column(name = "birthday",nullable = false)
    private Date birthday;

    @Column(name = "password",nullable = false)
    private String password;

    public User() {
    }

    public User(String userName, String nationalCode, Date birthday, String password) {
        this.userName = userName;
        this.nationalCode = nationalCode;
        this.birthday = birthday;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToMany(mappedBy = "user")
    private Collection<Article> article;

    public Collection<Article> getArticle() {
        return article;
    }

    public void setArticle(Collection<Article> article) {
        this.article = article;
    }
}
