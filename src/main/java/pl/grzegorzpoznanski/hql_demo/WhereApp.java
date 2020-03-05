package pl.grzegorzpoznanski.hql_demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.grzegorzpoznanski.hql_demo.entity.Employee;

import javax.persistence.Query;
import java.util.List;

public class WhereApp {
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

        String where = "from Employee where firstName='Tadeusz'";
        String where2 = "from Employee where salary>12000";
        String where3 = "from Employee where salary is null";
        //Slowo kluczowe  "in" oznacza ze szukamy emcji po wzorcah w tym wypadku Pukaluk i Hutton
        String where4 = "from Employee where lastName in ('Hutton','Pukaluk')";

        Query query = session.createQuery(where4);

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
