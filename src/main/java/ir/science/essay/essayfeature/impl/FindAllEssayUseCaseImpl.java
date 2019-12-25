package ir.science.essay.essayfeature.impl;

import ir.science.essay.core.configuration.hibernate.HibernateUtil;
import ir.science.essay.entities.Article;
import ir.science.essay.essayfeature.usecase.FindAllEssayUseCase;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class FindAllEssayUseCaseImpl implements FindAllEssayUseCase {
    @Override
    public List<Article> get() {
        // open session:
        Session session= HibernateUtil.getSessionFactory().openSession();
        //begin transaction:
        session.beginTransaction();
        //-----------------------------------
        Query<Article> query =session.createQuery("from Article");
        List<Article> articles=query.list();
        //-----------------------------------
        //commit transaction:
        session.getTransaction().commit();
        session.close();
        return articles;
    }
}
