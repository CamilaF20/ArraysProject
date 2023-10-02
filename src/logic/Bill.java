package logic;

import java.time.LocalDate;
import java.util.ArrayList;

public class Bill {

  
    private ArrayList<Detail> details;
    private String number;
    private LocalDate dateBill;

    public Bill(String number, LocalDate dateBill) {
        this.number = number;
        this.dateBill = dateBill;
        this.details = new ArrayList<Detail>();

    }
    
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getDateBill() {
        return dateBill;
    }

    public void setDateBill(LocalDate dateBill) {
        this.dateBill = dateBill;
    }


    public double calcTotal() {
        double totalValue = 0;
        for (Detail detail : details) {
            totalValue += detail.calcSubtotal();
        }
        return totalValue;
    }
    
    public boolean addDetail(Product product, short cant) {
        Detail newDetail = new Detail(product, cant);
        return details.add(newDetail);
    }
 
    public ArrayList<Product> getDetails() {
        ArrayList<Product> products = new ArrayList<>();
        for (Detail detail : details) {
            products.add(detail.getProduct());
        }
        return products;
    }

    @Override
    public String toString() {
        return "Bill{" +
                ", number='" + number + '\'' +
                ", dateBill=" + dateBill +
                '}';
    }
}
