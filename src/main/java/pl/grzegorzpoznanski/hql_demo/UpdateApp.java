package pl.grzegorzpoznanski.hql_demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.grzegorzpoznanski.hql_demo.entity.Employee;

import javax.persistence.Query;
import java.util.List;

public class UpdateApp {
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

        /*
        Update uzytkownika z id = 100 , zarobki na 16 000
         */
      int idEmployee=100;
      int salary=16000;

      session.beginTransaction();

      String update = "update Employee e set e.salary=:salary where e.idEmployee=:idEmployee";
      Query query = session.createQuery(update);
      query.setParameter("salary",salary);
      query.setParameter("idEmployee",idEmployee);
      query.executeUpdate();


        session.getTransaction().commit();

    }
}
