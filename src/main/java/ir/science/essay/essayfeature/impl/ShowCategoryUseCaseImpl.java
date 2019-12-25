package ir.science.essay.essayfeature.impl;

import ir.science.essay.core.configuration.hibernate.HibernateUtil;
import ir.science.essay.entities.Article;
import ir.science.essay.entities.Category;
import ir.science.essay.essayfeature.usecase.ShowCategoryUseCase;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ShowCategoryUseCaseImpl implements ShowCategoryUseCase {
    @Override
    public List<Category> get() {
        // open session:
        Session session= HibernateUtil.getSessionFactory().openSession();
        //begin transaction:
        session.beginTransaction();
        //-----------------------------------
        Query<Category> query =session.createQuery("from Category ");
        List<Category> categories=query.list();
        //-----------------------------------
        //commit transaction:
        session.getTransaction().commit();
        session.close();
        return categories;
    }
}
