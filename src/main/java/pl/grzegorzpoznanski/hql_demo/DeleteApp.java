package pl.grzegorzpoznanski.hql_demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.grzegorzpoznanski.hql_demo.entity.Employee;

import javax.persistence.Query;

public class DeleteApp {
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


        session.beginTransaction();

        String delete = "delete Employee e  where e.idEmployee=:idEmployee";
        Query query = session.createQuery(delete);
        query.setParameter("idEmployee",idEmployee);

        //za pomoca przypsiania inta mozemy sprawdzic ile rekordow zostalo zmienionych
        int count = query.executeUpdate();

        System.out.println("Ilosc zmienionych rekordow " + count);

        session.getTransaction().commit();

    }
}
