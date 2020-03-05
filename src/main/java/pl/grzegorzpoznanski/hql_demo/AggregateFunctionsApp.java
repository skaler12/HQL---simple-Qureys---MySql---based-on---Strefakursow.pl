package pl.grzegorzpoznanski.hql_demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.grzegorzpoznanski.hql_demo.entity.Employee;

import javax.persistence.Query;
import java.util.List;

public class AggregateFunctionsApp {
    public static void main(String args[]) {

        //stworzenie obiektu Configuration
        Configuration conf = new Configuration();
        //wczytanie pliku konfiguracyjnego
        conf.configure("hibernate.cfg.xml");
        //wczytanie adnotacji
        conf.addAnnotatedClass(Employee.class);
        //stworzenie obektu session factory
        SessionFactory factory = conf.buildSessionFactory();
        //pobranie sesji
        Session session = factory.getCurrentSession();

        session.beginTransaction();

        //wyliczanie srednich zarobkow/sumy zarobkow/liczy encji

        String avg = "select avg(e.salary) from Employee as e";
        String sum = "select sum(e.salary) from Employee as e";
        String count = "select count(e) from Employee as e";


        Query query = session.createQuery(count);


        Long result = (Long) query.getSingleResult();


        session.getTransaction().commit();


        System.out.println("Wynik sumy " + result);
    }
}
