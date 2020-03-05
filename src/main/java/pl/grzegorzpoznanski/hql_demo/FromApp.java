package pl.grzegorzpoznanski.hql_demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.grzegorzpoznanski.hql_demo.entity.Employee;

import javax.persistence.Query;
import java.util.List;

public class FromApp {
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

        String from =  "FROM Employee";

        Query query = session.createQuery(from);

        List<Employee> list = query.getResultList();

        session.getTransaction().commit();

        //wyswietlenie listy

        for(Employee employee : list){
            System.out.println(employee);
        }

        //zamkniecie obiektu session factory
        factory.close();
    }
}
