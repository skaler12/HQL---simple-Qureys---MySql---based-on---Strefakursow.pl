package pl.grzegorzpoznanski.hql_demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.grzegorzpoznanski.hql_demo.entity.Employee;

import javax.persistence.Query;
import java.util.List;

public class OrderByApp {
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

        /*
        pobieranie imion i nazwiskach  i sortowanie po  imieniu
         */

        String orderBy = "select e.firstName, e.lastName from Employee as e order by e.firstName";
        //sortowanie po imieniu malejaco
        String orderBy2 = "select e.firstName, e.lastName from Employee as e order by e.firstName desc";
        //sortowanie po wyplacie w kierunku rosnacym
        String orderBy3 = "select e.firstName, e.lastName , e.salary from Employee as e order by salary asc";
        Query query = session.createQuery(orderBy3);
        List<Object[]> result = query.getResultList();

        session.getTransaction().commit();

        for(Object[] values :result ){
            System.out.println("first name "+ values[0] + " last name "+ values[1]+ " Salary "+values[2]);
        }
    }
}