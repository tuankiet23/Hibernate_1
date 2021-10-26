package bo;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "Purchases")
@Entity

public class Purchases implements Serializable {

//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
    @Id
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @Id
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "ticket_id", referencedColumnName = "id")
    private Ticket ticket;

    @Column(name = "quantity", nullable = false)
    private int quantity;

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Purchases( Customer customer, Ticket ticket, int quantity) {
//        this.id = id;
        this.customer = customer;
        this.ticket = ticket;
        this.quantity = quantity;
    }

    public Purchases() {
    }

    @Override
    public String toString() {
        return "Purchases{" +
                ", customer=" + customer +
                ", ticket=" + ticket +
                ", quantity=" + quantity +
                '}';
    }
}
