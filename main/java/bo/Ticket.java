package bo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

@Table(name = "Ticket")
@Entity


public class Ticket implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ticket_type", nullable = false)
    private String ticket_type;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Purchases> purchases;

    @Column(name = "price", nullable = false)
    private int price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTicket_type() {
        return ticket_type;
    }

    public void setTicket_type(String ticket_type) {
        this.ticket_type = ticket_type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public List<Purchases> getPurchases() {
        return purchases;
    }


    public Ticket() {
    }

    public void input() {
        System.out.println("Nhập thông tin vé: ");
        System.out.print("Nhập loại vé: ");
        this.ticket_type = new Scanner(System.in).nextLine();
        System.out.print("Nhập giá: ");
        this.price = new Scanner(System.in).nextInt();
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", ticket_type='" + ticket_type + '\'' +
                ", price=" + price +
                '}';
    }
}
