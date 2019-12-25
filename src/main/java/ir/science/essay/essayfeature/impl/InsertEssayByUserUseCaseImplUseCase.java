package ir.science.essay.essayfeature.impl;

import ir.science.essay.core.configuration.hibernate.HibernateUtil;
import ir.science.essay.core.share.AuthenticationService;
import ir.science.essay.entities.Article;
import ir.science.essay.entities.Category;
import ir.science.essay.entities.User;
import ir.science.essay.essayfeature.usecase.InsertEssayByUserUseCase;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class InsertEssayByUserUseCaseImplUseCase implements InsertEssayByUserUseCase {
    private   User loginUser = AuthenticationService.getInstance().getLoginUser();
    private Session session;
    @Override
    public void set(Article article) {
        // open session:
        session= HibernateUtil.getSessionFactory().openSession();
        //begin transaction:
        session.beginTransaction();
        //-------------------------------
        User user=session.load(User.class,loginUser.getId());
        int id=(int) session.save(article);
        System.out.printf("Article with this %d inserted into table\n",id);

        //-----------------------------------
        //commit transaction:
        session.getTransaction().commit();
        session.close();
    }
}
