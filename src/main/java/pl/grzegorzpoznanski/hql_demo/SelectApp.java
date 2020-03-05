package pl.grzegorzpoznanski.hql_demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.grzegorzpoznanski.hql_demo.entity.Employee;

import javax.persistence.Query;
import java.util.List;

public class SelectApp {
    public  static  void main(String args[]){

        //stworzenie obiektu Configuration
        Configuration conf = new Configuration();
        //wczytanie pliku konfiguracyjnego
        conf.configure("hibernate.cfg.xml");
        //wczytanie adnotacji
        conf.addAnnotatedClass(Employee.class);
        //stworzenie obektu session factory
        SessionFactory factory=conf.buildSessionFactory();
        //pobranie sesji
        Session session = factory.getCurrentSession();

        session.beginTransaction();

        /*
        Sposob zapisu do 1 encji
         */
        String select ="select firstName, lastName from Employee";
        /*
        W wypadku gdy odwolujemy sie nie tylko do 1 encji, slowo kluczowe ,,as'', slowo ,,as'' jest opcjonalne intellij domysli sie ze chodzi o as jezeli nawet go nie bedzie ;)
         */
        String select2 = "select e.firstName, e.lastName from Employee as e";

        Query query=session.createQuery(select2);
        List<Object[]> result= query.getResultList();

        session.getTransaction().commit();

        for(Object[] values : result){
            System.out.println("first name " + values[0] + " last name "+values[1]);
        }

        //zamkniecie obiektu session factory
        factory.close();
    }
}
