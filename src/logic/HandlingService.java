package logic;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;


public class HandlingService {
    private Product[] products;
    private int position;
    private Bill[] bills;

    public HandlingService() {
        products = new Product[0];
        position = 0;
        bills = new Bill[0];
    }

    private void ensureProductCapacity() {
        if (position >= products.length) {
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
        if (position >= bills.length) {
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
            products[position] = product;
            position++;
            return true;
        }
    }

    public Product deleteProduct(String idProduct) {
        Product productToDelete = findProductById(idProduct);
        if (productToDelete != null) {
            for (int i = 0; i < position; i++) {
                if (products[i].getIdProduct().equals(idProduct)) {
                    System.arraycopy(products, i + 1, products, i, position - i - 1);
                    products[position - 1] = null;
                    position--;
                    return productToDelete;
                }
            }
        }
        return null;
    }

    public Product updateProduct(Product update) {
        if (findProductById(update.getIdProduct()) != null) {
            int updatePosition = 0;
            for (int i = 0; i < products.length; i++) {
                if (products[i].getIdProduct().equals(update.getIdProduct())) {
                    updatePosition = i;
                }
            }

            Product productsAux[] = products;

            productsAux[updatePosition] = update;
            products = productsAux;

            return products[updatePosition];
        }

        return null;
    }

    public Product findProductById(String idProduct) {
        for (int i = 0; i < position; i++) {
            if (products[i].getIdProduct().equals(idProduct)) {
                return products[i];
            }
        }
        return null;
    }

    public Product[] getAllProducts() {
        Product[] newArray = new Product[position];
        for (int index = 0; index < position; index++) {
            newArray[index] = products[index];
        }
        return newArray;
    }

    // Métodos para la gestión de facturas

    public Bill addBill(Bill bill) {
        ensureBillCapacity();
        Bill bill1 = new Bill(bill.getNumber(), bill.getDateBill());
        bills[position] = bill;
        position++;
        return bill;
    }

    public Bill findBillByNumber(String billNumber) {
        for (int i = 0; i < position; i++) {
            if (bills[i].getNumber().equals(billNumber)) {
                return bills[i];
            }
        }
        return null;
    }

    public boolean addProductToBill(String number, String productId, short cant) {
        Bill bill = findBillByNumber(number);
        Product product = findProductById(productId);
        if (bill != null && product != null) {
            return bill.addDetail(product, cant);
        }
        return false; // No se pudo agregar el producto a la factura
    }

    public double calculateBillTotal(String billNumber) {
        Bill bill = findBillByNumber(billNumber);
        return (bill != null) ? bill.calcTotal() : 0.0;
    }

    public Product[] checkBill(String number) {
        Bill bill = findBillByNumber(number);
        if (bill != null) {
            ArrayList<Product> productArrayList = bill.getDetails();
            Product[] products = new Product[productArrayList.size()];
            return productArrayList.toArray(products);
        } else {
            return new Product[0];
        }
    }

    public Product updateStock(String idProduct, int newStock) {
        Product product = findProductById(idProduct);
        if (product != null && newStock >= 5) {
            product.setStock(newStock);
            return product;
        }
        return null;
    }
}