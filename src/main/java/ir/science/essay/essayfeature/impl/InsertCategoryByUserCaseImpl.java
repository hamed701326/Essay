package ir.science.essay.essayfeature.impl;

import ir.science.essay.core.configuration.hibernate.HibernateUtil;
import ir.science.essay.entities.Category;
import ir.science.essay.essayfeature.usecase.InsertCategoryByUserUseCase;
import org.hibernate.Session;

public class InsertCategoryByUserCaseImpl implements InsertCategoryByUserUseCase {
    @Override
    public int set(Category category) {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        //--------------------
        int id=(int)session.save(category);
        //-----------------------
        session.getTransaction().commit();
        session.close();
        return id;
    }
}
