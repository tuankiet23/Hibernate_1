package dao;

import bo.Customer;
import bo.Ticket;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class TicketDAO implements ITask {
    public List<Ticket> getAll() {
        Session session1 = HibernateUtil.getSessionFactory().openSession();
        try {
            session1.beginTransaction();
            List<Ticket> tickets = session1.createQuery(" from Ticket ").list();
            session1.getTransaction().commit();
            return tickets;
        } catch (Exception e) {
        } finally {
            session1.close();
        }
        return null;
    }

    public boolean addNew() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Ticket ticket=new Ticket();
        ticket.input();
        try {
            session.beginTransaction();
            session.save(ticket);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return false;
    }

    public Ticket searchOneById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Query<Ticket> query = session.createQuery("select c from Ticket c where c.id = :p_id");
            query.setParameter("p_id", id);
            Ticket ticket = query.getSingleResult();
            session.getTransaction().commit();
            return ticket;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }
        return null;
    }


}
