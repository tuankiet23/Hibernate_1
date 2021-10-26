package dao;

import bo.Customer;
import bo.Purchases;
import bo.Ticket;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public class PurchasesDAO {
    public List<Purchases> getAll() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            List<Purchases> purchases = session.createQuery(" from Purchases ").list();
            session.getTransaction().commit();
            return purchases;
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return null;
    }

    public static boolean sortByTotalTicket() {

String query="select  customer.id,customer.name, customer.address, customer.phone, customer.customer_type, sum(quantity) from Purchases, Customer \n" +
        "where purchases.customer_id=customer.id \n" +
        "group by customer.id, customer.name, customer.address, customer.phone, customer.customer_type  \n" +
        "order by sum(quantity) asc";
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.createSQLQuery(query).list().forEach(System.out::println);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return false;
    }



    public List<Purchases> sortName() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            List<Purchases> purchases = session.createQuery(" from Purchases ORDER BY customer.name ").list();
            session.getTransaction().commit();
            return purchases;
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return null;
    }

    public static boolean addNew(Purchases purchases) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(purchases);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return false;
    }
    public static int checkQauntityTypeTicket(int id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Query<Integer> query = session.createQuery("select count(ticket.id) from Purchases where customer.id=:p_id group by customer.id ");
            query.setParameter("p_id", id);
            int a = query.getSingleResult();
            session.getTransaction().commit();
            return a;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }
        return 0;
    }
}
