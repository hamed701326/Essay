import ir.science.essay.core.configuration.hibernate.HibernateUtil;
import ir.science.essay.entities.Article;
import ir.science.essay.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Date;

public class MainApp {
    public static void main(String[] args) {
        //create sessionFactory
        SessionFactory sessionFactory= HibernateUtil.getSessionFactory();
        //create session
        Session session=sessionFactory.openSession();
        // start transaction
        session.beginTransaction();
        //----------------------------------
        //save
        /*User user=new User("asgharmohammadi","607",setDate(1996L,5L,2L),"607");
        int id=(int) session.save(user);
        System.out.printf("User with this %d inserted into table\n",id);
        */
       /* Article article=new Article("Global warming", "exploring increasing of temperature of earth",
                "CO2 effect",new Date(),new Date(),null,false);
        int id=(int) session.save(article);
        System.out.printf("Article with this %d inserted into table\n",id);
        */


        //----------------------
        //commit transaction
        session.getTransaction().commit();
        session.close();
    }
    public static Date setDate(Long years,Long month,Long days){
        long timeStamp=((years-1970)*365+(month-1)*30+(days-1))*24*3600*1000;
        return new Date(timeStamp);
    }
}
