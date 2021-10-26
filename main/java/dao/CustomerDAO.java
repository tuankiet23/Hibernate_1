package dao;

import bo.Customer;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;
public class CustomerDAO implements ITask {

    public List<Customer> getAll() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            List<Customer> customers = session.createQuery(" from Customer").list();
            session.getTransaction().commit();
            return customers;
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public  boolean addNew() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Customer customer=new Customer();
        customer.input();
        try {
            session.beginTransaction();
            session.save(customer);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return false;
    }

    public Customer searchOneById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Query<Customer> query = session.createQuery("select c from Customer c where c.id = :p_id");
            query.setParameter("p_id", id);
            Customer customer = query.getSingleResult();
            session.getTransaction().commit();
            return customer;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }
        return null;
    }
}
