package ir.science.essay.essayfeature.impl;

import ir.science.essay.core.configuration.hibernate.HibernateUtil;
import ir.science.essay.core.share.AuthenticationService;
import ir.science.essay.entities.Article;
import ir.science.essay.entities.User;
import ir.science.essay.essayfeature.usecase.FindEssayByUserUseCase;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class FindEssayByUserUseCaseImpl implements FindEssayByUserUseCase {
    @Override
    public List<Article> get() {
        User loginUser = AuthenticationService.getInstance().getLoginUser();
        // open session:
        Session session= HibernateUtil.getSessionFactory().openSession();
        //begin transaction:
        session.beginTransaction();
        //-----------------------------------
        String sql="from Article A where A.user=:user";
        Query<Article> query =session.createQuery(sql);
        query.setParameter("user",loginUser);
        List<Article> articles=query.list();

        //-----------------------------------
        //commit transaction:
        session.getTransaction().commit();
        session.close();
        return  articles;

    }
    public Article get(int id) {
        User loginUser = AuthenticationService.getInstance().getLoginUser();
        // open session:
        Session session= HibernateUtil.getSessionFactory().openSession();
        //begin transaction:
        session.beginTransaction();
        //-----------------------------------
        String sql="from Article A where A.user=?1 and A.id=?2";
        Query<Article> query =session.createQuery(sql);
        query.setParameter(1,loginUser);
        query.setParameter(2,id);
        List<Article> articles=query.list();

        //-----------------------------------
        //commit transaction:
        session.getTransaction().commit();
        session.close();
        return  articles.get(0);

    }

}
