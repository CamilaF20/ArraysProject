package logic;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;


public class HandlingService {
    private Product[] products;
    private int productCount;
    private Bill[] bills;
    private int billCount;

    public HandlingService() {
        products = new Product[0];
        productCount = 0;
        bills = new Bill[0]; 
        billCount = 0;
    }

     private void ensureProductCapacity() {
        if (productCount >= products.length) {
            int newSize;
            if (products.length == 0) {
                newSize = 1;
            } else {
                newSize = products.length * 2; 
            }
            products = Arrays.copyOf(products, newSize);
        }
    }

    private void ensureBillCapacity() {
        if (billCount >= bills.length) {
            int newSize;
            if (bills.length == 0) {
                newSize = 1;
            } else {
                newSize = bills.length * 2; 
            }
            bills = Arrays.copyOf(bills, newSize);
        }
    }

  

    public boolean addProduct(Product product) {
        if (findProductById(product.getIdProduct()) != null) {
            return false; 
        } else {
            ensureProductCapacity();
            products[productCount] = product;
            productCount++;
            return true;
        }
    }

    public Product deleteProduct(String idProduct) {
        Product productToDelete = findProductById(idProduct);
        if (productToDelete != null) {
            for (int i = 0; i < productCount; i++) {
                if (products[i].getIdProduct().equals(idProduct)) {
                    System.arraycopy(products, i + 1, products, i, productCount - i - 1);
                    products[productCount - 1] = null; 
                    productCount--;
                    return productToDelete;
                }
            }
        }
        return null; 
    }

    public boolean updateProduct(Product updatedProduct) {
        Product existingProduct = findProductById(updatedProduct.getIdProduct());
        if (existingProduct != null) {
            for (int i = 0; i < productCount; i++) {
                if (products[i].getIdProduct().equals(updatedProduct.getIdProduct())) {
                    products[i] = updatedProduct;
                    return true;
                }
            }
        }
        return false; // No se encontró el producto
    }

    public Product findProductById(String idProduct) {
        for (int i = 0; i < productCount; i++) {
            if (products[i].getIdProduct().equals(idProduct)) {
                return products[i];
            }
        }
        return null; 
    }

    public Product[] getAllProducts() {
        return Arrays.copyOf(products, productCount); 
    }

    // Métodos para la gestión de facturas

    public Bill createBill(String number, LocalDate dateBill) {
        ensureBillCapacity();
        Bill bill = new Bill(number, dateBill);
        bills[billCount] = bill;
        billCount++;
        return bill;
    }

    public Bill findBillByNumber(String billNumber) {
        for (int i = 0; i < billCount; i++) {
            if (bills[i].getNumber().equals(billNumber)) {
                return bills[i];
            }
        }
        return null; 
    }

    public boolean addProductToBill(String billNumber, String productId, short quantity) {
        Bill bill = findBillByNumber(billNumber);
        Product product = findProductById(productId);
        if (bill != null && product != null) {
            return bill.addDetail(product, quantity);
        }
        return false; // No se pudo agregar el producto a la factura
    }

    public double calculateBillTotal(String billNumber) {
        Bill bill = findBillByNumber(billNumber);
        return (bill != null) ? bill.calcTotal() : 0.0;
    }

    public Product[] getBillDetails(String billNumber) {
    Bill bill = findBillByNumber(billNumber);
    if (bill != null) {
        ArrayList<Product> productArrayList = bill.getDetails();
        Product[] products = new Product[productArrayList.size()];
        return productArrayList.toArray(products);
    } else {
        return new Product[0];
    }
}
}