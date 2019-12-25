package ir.science.essay.essayfeature.impl;

import ir.science.essay.core.configuration.hibernate.HibernateUtil;
import ir.science.essay.entities.Article;
import ir.science.essay.entities.Category;
import ir.science.essay.essayfeature.usecase.FindCategoryByUserUseCase;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class FindCategoryByUserUseCaseImpl implements FindCategoryByUserUseCase {
    @Override
    public Category get(int categoryId) {
        // open session:
        Session session= HibernateUtil.getSessionFactory().openSession();
        //begin transaction:
        session.beginTransaction();
        //---------------------------------
        Category category=session.load(Category.class,categoryId);

        //-----------------------------------
        //commit transaction:
        session.getTransaction().commit();
        session.close();
        return category;

    }
}
