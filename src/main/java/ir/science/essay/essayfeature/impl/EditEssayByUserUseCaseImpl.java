package ir.science.essay.essayfeature.impl;

import ir.science.essay.core.configuration.hibernate.HibernateUtil;
import ir.science.essay.entities.Article;
import ir.science.essay.essayfeature.usecase.EditEssayByUserUseCase;
import org.hibernate.Session;

import java.util.Date;
import java.util.Scanner;

public class EditEssayByUserUseCaseImpl implements EditEssayByUserUseCase {
    @Override
    public void editInformation(Article article,int id) {
        // open session:
        Session session= HibernateUtil.getSessionFactory().openSession();
        //begin transaction:
        session.beginTransaction();
        //-----------------------------------
        Article article1=session.load(Article.class,id);

            article1.setTitle(article.getTitle());
            article1.setBrief(article.getBrief());
            article1.setContent(article.getContent());
            article1.setCategory(article.getCategory());
            article1.setUser(article.getUser());
            article1.setLastUpdateDate(new Date());

            session.update(article1);
            System.out.printf("Article with id=%d is updated \n", id);

        //-----------------------------------
        //commit transaction:
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void editPublish(int articleId) {
        // open session:
        Session session= HibernateUtil.getSessionFactory().openSession();
        //begin transaction:
        session.beginTransaction();
        //-----------------------------------
            Article article=session.load(Article.class,articleId);
            if(article!=null) {
                article.setPublished(true);
                article.setLastUpdateDate(new Date());
                article.setPublishDate(new Date());
                session.update(article);
            }else{
                System.out.print("there isn't any article with this id\n" +
                        "\n\tEnter another Id:");
                editPublish(new Scanner(System.in).nextInt());
            }
        //-----------------------------------
        //commit transaction:
        session.getTransaction().commit();
        session.close();
    }
}
