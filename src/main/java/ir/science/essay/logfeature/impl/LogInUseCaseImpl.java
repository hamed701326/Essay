package ir.science.essay.logfeature.impl;

import ir.science.essay.core.configuration.hibernate.HibernateUtil;
import ir.science.essay.entities.Category;
import ir.science.essay.entities.User;
import ir.science.essay.logfeature.usecase.LogInUseCase;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class LogInUseCaseImpl implements LogInUseCase {

    @Override
    public User login(String username, String password) {
        // open session:
        Session session= HibernateUtil.getSessionFactory().openSession();
        //begin transaction:
        session.beginTransaction();
        //-----------------------------------
        String sql="from User where userName = ?1 and password = ?2 ";
        Query<User> query =session.createQuery(sql);
        query.setParameter(1,username);
        query.setParameter(2,password);
        List<User> users=query.list();
        //-----------------------------------
        //commit transaction:
        session.getTransaction().commit();
        session.close();
        return users.get(0);
    }
}
