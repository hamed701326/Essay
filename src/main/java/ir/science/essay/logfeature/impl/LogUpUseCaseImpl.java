package ir.science.essay.logfeature.impl;

import ir.science.essay.core.configuration.hibernate.HibernateUtil;
import ir.science.essay.entities.Category;
import ir.science.essay.entities.User;
import ir.science.essay.logfeature.usecase.LogUpUseCase;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class LogUpUseCaseImpl implements LogUpUseCase {
    @Override
    public void createAccount(User user) {
        // open session:
        Session session= HibernateUtil.getSessionFactory().openSession();
        //begin transaction:
        session.beginTransaction();
        //-----------------------------------
        session.save(user);
        //-----------------------------------
        //commit transaction:
        session.getTransaction().commit();
        session.close();
    }
}
