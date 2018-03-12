package db;

import models.Employee;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DBHelper {

    private static Transaction transaction;
    private static Session session;


    public static void save(Object object) {  //Object we can pass in an employee, a department or any other java object

        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(object);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static <T> List<T> getAll(String className){
        session = HibernateUtil.getSessionFactory().openSession();
        List<T> results = null;
        try {
            transaction = session.beginTransaction();
            String hql = "from " + className;
            results = session.createQuery(hql).list();
            transaction.commit();
        }catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;

    } //declaring generic method T for type, so this method can deal with any object



    public static List<Employee> getEmployees(int id){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Employee> employees = null;

        try {
            transaction = session.beginTransaction();
            String hql = "from Employee WHERE department_id = :id";  //:id is a placeholder, prevent sql injection
            Query query = session.createQuery(hql);
            query.setInteger("id", id);//the "id" is linked to the placeholder :id above, the other id is the value that you want to set, it is an argument in the method getEmployees above
            employees = query.list();
            transaction.commit();

        }catch (HibernateException e){
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employees;
    }

    public static void update(Object object){
        session = HibernateUtil.getSessionFactory().openSession();

        try{
            transaction = session.beginTransaction();
            session.update(object);
            transaction.commit();
        }catch(HibernateException ex){
            transaction.rollback();
            ex.printStackTrace();
        }finally{
            session.close();
        }
    }

    public static void delete(Object object){
        session = HibernateUtil.getSessionFactory().openSession();

        try{
            transaction = session.beginTransaction();
            session.delete(object);
            transaction.commit();
        }catch(HibernateException ex){
            transaction.rollback();
            ex.printStackTrace();
        }finally{
            session.close();
        }
    }

    public static void deleteById(int id, String className){
        session = HibernateUtil.getSessionFactory().openSession();
        try{
            transaction = session.beginTransaction();
            String hql = "delete from " + className + " where id = :id";
            Query query = session.createQuery(hql);
            query.setInteger("id", id);
            query.executeUpdate();
            transaction.commit();
        }catch(HibernateException ex){
            transaction.rollback();
            ex.printStackTrace();
        }finally{
            session.close();
        }

    }
}
