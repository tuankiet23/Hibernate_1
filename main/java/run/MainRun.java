package run;

import bo.Customer;
import bo.Purchases;
import bo.Ticket;
import dao.CustomerDAO;
import dao.PurchasesDAO;
import dao.TicketDAO;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Scanner;


public class MainRun {

    public static CustomerDAO customerDAO = new CustomerDAO();
    public static TicketDAO ticketDAO = new TicketDAO();
    public static PurchasesDAO purchasesDAO = new PurchasesDAO();

    private static void displayCustomer() {
        List<Customer> customers = customerDAO.getAll();
        if (!(customers == null || customers.isEmpty())) {
            System.out.println("Danh sách khách hàng: ");
            customers.forEach(System.out::println);
        } else {
            System.out.println("Không có bản ghi nào trong CSDL");
        }
    }

    private static void displayTicket() {
        TicketDAO ticketDao = new TicketDAO();
        List<Ticket> tickets = ticketDao.getAll();

        if (!(tickets == null || tickets.isEmpty())) {
            System.out.println("Danh sách vé: ");
            tickets.forEach(System.out::println);
        } else {
            System.out.println("Không có bản ghi nào trong CSDL");
        }
    }

    private static void displayPurchases() {
        List<Purchases> purchases = purchasesDAO.getAll();

        if (!(purchases == null || purchases.isEmpty())) {
            System.out.println("Danh sách vé: ");
            purchases.forEach(System.out::println);
        } else {
            System.out.println("Không có bản ghi nào trong CSDL");
        }
    }

    public static void sortname() {
        List<Purchases> purchases = purchasesDAO.sortName();

        if (!(purchases == null || purchases.isEmpty())) {
            System.out.println("Danh sách mua vé sx: ");
            purchases.forEach(System.out::println);
        } else {
            System.out.println("Không có bản ghi nào trong CSDL");
        }
    }

    public static void bill() {
        List<Customer> customers = customerDAO.getAll();
        for (Customer customer : customers) {
            double total = customer.getPurchases().stream()
                    .mapToDouble(pc -> pc.getTicket().getPrice() * pc.getQuantity())
                    .sum();
            System.out.println("customer: " + customer.getId() + "  " + customer.getName() + "  " + total);
        }
    }

    public static void creater() {
        Scanner sc = new Scanner(System.in);

        Customer customer = null;
        Ticket ticket=null;
        System.out.println("Nhap id khách hàng:");
        int idc = 0;
        int idt;
        boolean check = true;
        boolean check1 = false;

        do {
            try {
                String i = sc.next();
                idc= Integer.parseInt(i);
                customer = customerDAO.searchOneById(idc);
                int a = PurchasesDAO.checkQauntityTypeTicket(idc);
                if (customer == null || a >= 4) {
                    System.out.println("nhap lại id:");
                    check = false;
                } else
                    check = true;
            } catch (Exception e) {
                System.out.println("Không được có ký tự khác ngoài số! Nhập lại: ");
                check = false;
                continue;
            }

        } while (!check);
        System.out.println("Nhap id vé:");
        do {
            try {
                String i = sc.next();
                idt= Integer.parseInt(i);
                ticket = ticketDAO.searchOneById(idt);
                if (ticket == null ) {
                    System.out.println("nhap lại id:");
                    check1 = false;
                } else
                    check1 = true;
            } catch (Exception e) {
                System.out.println("Không được có ký tự khác ngoài số! Nhập lại: ");
                check1 = false;
                continue;
            }
        } while (!check1);

//        boolean flag = false;
//        do {
//            idt = sc.nextInt();
//            ticket = ticketDAO.searchOneById(idt);
//            if (ticket == null) {
//                System.out.println("nhap lại id:");
//                check = true;
//            } else
//                check = false;
//        } while (check);

        System.out.println("Nhập số lượng vé");
        int quantity = 0;

        do {
            try {
                quantity = sc.nextInt();
                check = true;
            } catch (Exception e) {
                System.out.println("Không được có ký tự khác ngoài số! Nhập lại: ");
                check = false;
                continue;
            }
            if (quantity < 0 || quantity > 20) {
                System.out.print("Số lượt lái phải lớn hơn 0và nhỏ hơn 20! Nhập lại: ");
                check = false;
                continue;
            }
        } while (!check);

        boolean kt = PurchasesDAO.addNew(new Purchases(customer, ticket, quantity));
        if (kt == true) {
            System.out.println("Thêm mói thành công");
        } else {
            System.out.println("thêm mới thất bại ");
        }
    }


    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        do {
            int functionChoice = functionChoice();
            switch (functionChoice) {
                case 1:
                    displayCustomer();
                    break;
                case 2:
                    displayTicket();
                    break;
                case 3:
                    displayPurchases();
                    break;
                case 4:
                    customerDAO.addNew();
                    break;
                case 5:
                    customerDAO.addNew();
                    break;
                case 6:
                    creater();
                    break;
                case 7:
                    purchasesDAO.sortByTotalTicket();
                    break;
                case 8:
                    sortname();
                    break;
                case 9:
                    bill();
                    break;
                case 10:
                    System.exit(0);
            }
        } while (true);
    }

    public static int functionChoice() {
        System.out.println("PHẦN MỀM QUẢN BÁN VÉ\n");
        System.out.println("1. Hiển thị danh sách khách hàng.");
        System.out.println("2. Hiển thị thông tin vé.");
        System.out.println("3. Hiển thị danh sách mua vé");
        System.out.println("4. Thêm mới một kh.");
        System.out.println("5. Thêm mới vé.");
        System.out.println("6. Thêm dánh sách mua vé.");
        System.out.println("7. Sx theo so luong.");
        System.out.println("8. Sx theo tên.");
        System.out.println("9. Hóa đơn.");
        System.out.println("10. Thoát.");
        System.out.println("---------------------------------");
        System.out.print("Xin mời chọn chức năng: ");
        int functionChoice = 0;
        boolean checkFunction = true;
        do {
            try {
                functionChoice = new Scanner(System.in).nextInt();
                checkFunction = true;
            } catch (Exception e) {
                System.out.println("Không được nhập ký tự khác ngoài số! Nhập lại: ");
                continue;
            }
            if (functionChoice <= 0 || functionChoice > 11) {
                System.out.print("Nhập số trong khoảng từ 1 đến 6! Nhập lại: ");
                checkFunction = false;
            } else break;
        } while (!checkFunction);
        return functionChoice;
    }

}
