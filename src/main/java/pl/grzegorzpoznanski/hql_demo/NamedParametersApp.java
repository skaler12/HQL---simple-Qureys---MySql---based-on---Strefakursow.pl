package pl.grzegorzpoznanski.hql_demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.grzegorzpoznanski.hql_demo.entity.Employee;

import javax.persistence.Query;
import java.util.List;

public class NamedParametersApp {
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

       String employeeFirstName="Steven";
       String employeeLastName="King";

        session.beginTransaction();

        //nadawanie zmiennych dynamicznie

        String namedParametersString = "select e.firstName, e.lastName, e.salary from Employee e where e.firstName=:firstName and e.lastName=:lastName";
        Query namedParametersQuery = session.createQuery(namedParametersString);
        namedParametersQuery.setParameter("firstName",employeeFirstName);
        namedParametersQuery.setParameter("lastName",employeeLastName);



        List<Object[]> result = namedParametersQuery.getResultList();

        session.getTransaction().commit();

        for(Object[] values :result ){
            System.out.println("first name "+ values[0] + " last name "+ values[1]+ " Salary "+values[2]);
        }
    }
}
