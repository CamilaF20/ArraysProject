package logic;

import java.time.LocalDate;
import java.util.ArrayList;

public class SalesService {
    private ArrayList<Product> products;
    private ArrayList<Bill> bills;

    public SalesService() {
        products = new ArrayList<>();
        bills = new ArrayList<>();
    }

   
    public boolean addProduct(String idProduct, String description, double value, int stock, LocalDate dateExpired, ETypeProduct typeProduct) {
        Product product = new Product(idProduct, description, value, stock, dateExpired, typeProduct);
        return products.add(product);
    }

   
    public boolean deleteProduct(String idProduct) {
        Product product = findProductById(idProduct);
        if (product != null) {
            return products.remove(product);
        }
        return false; 
    }


    public boolean updateProduct(String idProduct, String description, double value, int stock, LocalDate dateExpired, ETypeProduct typeProduct) {
        Product product = findProductById(idProduct);
        if (product != null) {
            product.setDescription(description);
            product.setValue(value);
            product.setStock(stock);
            product.setDateExpired(dateExpired);
            product.setTypeProduct(typeProduct);
            return true; 
        }
        return false;
    }

    public Bill createBill(String number, LocalDate dateBill) {
        Bill bill = new Bill(number, dateBill);
        return bills.add(bill) ? bill : null;
    }


    public boolean addDetailToBill(String billNumber, String productId, short quantity) {
        Bill bill = findBillByNumber(billNumber);
        if (bill != null) {
            Product product = findProductById(productId);
            if (product != null) {
                return bill.addDetail(product, quantity);
            }
        }
        return false; 
    }

    public double calculateBillTotal(String billNumber) {
        Bill bill = findBillByNumber(billNumber);
        return bill != null ? bill.calcTotal() : 0.0;
    }


    public ArrayList<Product> getBillDetails(String billNumber) {
        Bill bill = findBillByNumber(billNumber);
        return bill != null ? bill.getDetails() : new ArrayList<>();
    }

    public Product findProductById(String idProduct) {
        for (Product product : products) {
            if (product.getIdProduct().equals(idProduct)) {
                return product;
            }
        }
        return null;
    }

   
    public Bill findBillByNumber(String billNumber) {
        for (Bill bill : bills) {
            if (bill.getNumber().equals(billNumber)) {
                return bill;
            }
        }
        return null; 
    }
}

