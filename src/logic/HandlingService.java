package logic;


import java.util.ArrayList;
import java.util.Arrays;

/**
 * The `HandlingService` class provides functionality for managing products and bills within a system.
 */
public class HandlingService {
    private Product[] products;
    private int position;
    private Bill[] bills;
    private int positionBill;

    /**
     * Constructor to create an instance of the `HandlingService` class.
     * Initializes product and bill arrays.
     */
    public HandlingService() {
        products = new Product[0];
        position = 0;
        bills = new Bill[0];
        positionBill = 0;
    }

    /**
     * Ensures that the `products` array has sufficient capacity to accommodate new elements.
     * If the current size is not enough, it resizes the array to double its current size.
     */
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

    /**
     * Ensures that the `bills` array has sufficient capacity to accommodate new elements.
     * If the current size is not enough, it resizes the array to double its current size.
     */
    private void ensureBillCapacity() {
        if (positionBill >= bills.length) {
            int newSize;
            if (bills.length == 0) {
                newSize = 1;
            } else {
                newSize = bills.length * 2;
            }
            bills = Arrays.copyOf(bills, newSize);
        }
    }

      /**
     * Add a new product to the system.
     *
     * @param product The product to be added.
     * @return true if the product was added successfully, false if the product with the same ID already exists.
     */
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
    /**
     * Delete a product from the system using its ID.
     *
     * @param idProduct The ID of the product to be deleted.
     * @return The deleted product if found, null otherwise.
     */
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
     /**
     * Update a product in the system with new information.
     *
     * @param update The updated product information.
     * @return The updated product if found and updated successfully, null otherwise.
     */
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
    /**
     * Find a product by its ID.
     *
     * @param idProduct The ID of the product to be found.
     * @return The found product if exists, null otherwise.
     */
    public Product findProductById(String idProduct) {
        for (int i = 0; i < position; i++) {
            if (products[i].getIdProduct().equals(idProduct)) {
                return products[i];
            }
        }
        return null;
    }
    /**
     * Get an array of all products in the system.
     *
     * @return An array containing all products.
     */
    public Product[] getAllProducts() {
        Product[] newArray = new Product[position];
        for (int index = 0; index < position; index++) {
            newArray[index] = products[index];
        }
        return newArray;
    }

    /**
     * Add a new bill to the system.
     *
     * @param bill The bill to be added.
     * @return true if the bill was added successfully, false if a bill with the same number already exists.
     */
    public boolean addBill(Bill bill) {

        if (findBillByNumber(bill.getNumber()) != null) {
            return false;
        } else {
            ensureBillCapacity();
            bills[positionBill] = bill;
            positionBill++;
            return true;
        }
    }
     /**
     * Find a bill by its number.
     *
     * @param billNumber The number of the bill to be found.
     * @return The found bill if exists, null otherwise.
     */
    public Bill findBillByNumber(String billNumber) {
        for (int i = 0; i < positionBill; i++) {
            if (bills[i].getNumber().equals(billNumber)) {
                return bills[i];
            }
        }
        return null;
    }
     /**
     * Add a product to a bill with a specified quantity.
     *
     * @param number    The number of the bill.
     * @param productId The ID of the product to be added.
     * @param cant      The quantity of the product to be added.
     * @return true if the product was added to the bill successfully, false otherwise.
     */
    public boolean addProductToBill(String number, String productId, short cant) {
        Bill bill = findBillByNumber(number);
        Product product = findProductById(productId);
        if (bill != null && product != null) {
            return bill.addDetail(product, cant);
        }
        return false; // No se pudo agregar el producto a la factura
    }
     /**
     * Calculate the total value of a bill.
     *
     * @param billNumber The number of the bill.
     * @return The total value of the bill.
     */
    public double calculateBillTotal(String billNumber) {
        Bill bill = findBillByNumber(billNumber);
        return (bill != null) ? bill.calcTotal() : 0.0;
    }
    /**
     * Check the details of a bill and return an array of products in the bill.
     *
     * @param number The number of the bill.
     * @return An array of products in the bill, or an empty array if the bill is not found.
     */
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
    /**
     * Update the stock quantity of a product.
     *
     * @param idProduct The ID of the product to update.
     * @param newStock  The new stock quantity for the product.
     * @return The updated product if found and updated successfully, null otherwise.
     */
    public Product updateStock(String idProduct, int newStock) {
        Product product = findProductById(idProduct);
        if (product != null && newStock >= 5) {
            product.setStock(newStock);
            return product;
        }
        return null;
    }
    
    /**
     * Get an array of all bills in the system.
     *
     * @return An array containing all bills.
     */
    public Bill[] getBills() {
        Bill[] newArray = new Bill[positionBill];
        for (int index = 0; index < positionBill; index++) {
            newArray[index] = bills[index];
        }
        return newArray;
    }
}