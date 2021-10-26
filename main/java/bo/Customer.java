package bo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

@Table(name = "Customer")
@Entity

public class Customer implements Serializable {

    public static final String MUA_LE = "Mua le";
    public static final String MUA_TAP_THE = "Mua tap the";
    public static final String MUA_QUA_MANG = "Mua qua mang";


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "customer_type", nullable = false)
    private String customer_type;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Purchases> purchases;

    public void setPurchases(List<Purchases> purchases) {
        this.purchases = purchases;
    }

    public List<Purchases> getPurchases() {
        return purchases;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCustomer_type() {
        return customer_type;
    }

    public void setCustomer_type(String customer_type) {
        this.customer_type = customer_type;
    }


    public Customer() {
    }

    public void input() {
        System.out.println("Nhập thông tin cho khách hàng mới: ");
        System.out.print("Nhập họ và tên: ");
        this.name = new Scanner(System.in).nextLine();
        System.out.print("Nhập sdt: ");
        this.phone = new Scanner(System.in).nextLine();
        System.out.print("Nhập loại kh: ");
        System.out.println("Nhập loại phòng: ");
        System.out.println("1. Mua lẻ");
        System.out.println("2. Mua qua mạng");
        System.out.println("3. Mua tập thể");
        System.out.print("Xin hãy lựa chọn: ");
        boolean check;
        do {
            int choice;
            try {
                choice = new Scanner(System.in).nextInt();
                check = true;
            } catch (Exception e) {
                System.out.print("Không được nhập ký tự khác ngoài số! Vui lòng thử lại: ");
                check = false;
                continue;
            }
            if (choice <= 0 || choice > 3) {
                System.out.print("Nhập số trong khoảng từ 1 đến 3! Vui lòng thử lại: ");
                check = false;
                continue;
            }

            switch (choice) {
                case 1:
                    this.customer_type = MUA_LE;
                    break;
                case 2:
                    this.customer_type = MUA_QUA_MANG;
                    break;
                case 3:
                    this.customer_type = MUA_TAP_THE;
                    break;
                default:
                    System.out.print("Loại kh vừa chọn không hợp lệ, vui lòng chọn lại: ");
                    check = false;
                    break;
            }
        } while (!check);

        System.out.print("Nhập địa chỉ kh: ");
        this.address = new Scanner(System.in).nextLine();
    }


    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", phone='" + phone + '\'' +
                ", customer_type='" + customer_type + '\'' +
                '}';
    }
}

