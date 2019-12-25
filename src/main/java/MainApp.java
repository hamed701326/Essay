import ir.science.essay.core.configuration.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class MainApp {
    public static void main(String[] args) {
        //create sessionFactory
        SessionFactory sessionFactory= HibernateUtil.getSessionFactory();
        //create session
        Session session=sessionFactory.openSession();
    }
}
