package presenter;

import logic.*;

import java.time.LocalDate;
/**
 * The `Presenter` class serves as the intermediary between the user interface and the handling service.
 */
public class Presenter {
    private HandlingService handlingService;

    /**
     * Default constructor for creating an instance of the `Presenter` class.
     */
    public Presenter() {
        this.handlingService = new HandlingService();
    }

    /**
     * Find a product by its ID and return its information as an array of strings.
     *
     * @param number The unique identifier of the product to find.
     * @return An array containing product information or null if the product is not found.
     */
public String[] findById(String number) {
    Product product = handlingService.findProductById(number);
    if (product != null) {
        String[] array = new String[6];
        array[0] = product.getIdProduct();
        array[1] = product.getDescription();
        array[2] = Double.toString(product.getValue());
        array[3] = Integer.toString(product.getStock());
        array[4] = product.getDateExpired().toString();
        array[5] = product.getTypeProduct().toString();
        return array;
    } else {
        return null;
    }
}
    
     /**
     * Find a product by its ID and return the product object.
     *
     * @param number The unique identifier of the product to find.
     * @return The product object or null if the product is not found.
     */
    public Product findId(String number) {
        return handlingService.findProductById(number);
    }

     /**
     * Add a new product using information provided in an array.
     *
     * @param array An array containing product information to be added.
     * @return true if the product is successfully added, false otherwise.
     */

     public boolean addProduct(String[] array) {

         String Id = array[0];
         String description = array[1];
         double value = Double.parseDouble(array[2]);
         int stock = Integer.parseInt(array[3]);
         LocalDate dateExpired = LocalDate.parse(array[4]);
         ETypeProduct typeProduct = ETypeProduct.valueOf(array[5]);

         Product product = new Product(Id, description, value, stock, dateExpired, typeProduct);

         return handlingService.addProduct(product);
     }

    /**
     * Delete a product by its ID and return its information as an array of strings.
     *
     * @param Id The unique identifier of the product to delete.
     * @return An array containing deleted product information or null if the product is not found.
     */
    public String[] delete(String Id) {

        Product product = handlingService.deleteProduct(Id);

        if (product != null) {

            String[] array = new String[6];

            array[0] = product.getIdProduct();
            array[1] = product.getDescription();
            array[2] = Double.valueOf(product.getValue()).toString();
            array[3] = Integer.valueOf(product.getStock()).toString();
            array[4] = (product.getDateExpired()).toString();
            array[5] = (product.getTypeProduct()).toString();

            return array;

        } else {
            return null;
        }
    }
    
    /**
     * Update a product using information provided in an array and return the updated information.
     *
     * @param array An array containing updated product information.
     * @return An array containing the updated product information or null if the product is not found.
     */
    public String[] update(String[] array) {

        String Id = array[0];
        String description = array[1];
        double value = Double.parseDouble(array[2]);
        int stock = Integer.parseInt(array[3]);
        LocalDate dateExpired = LocalDate.parse(array[4]);
        ETypeProduct typeProduct = ETypeProduct.valueOf(array[5]);

        Product product = new Product(Id, description, value, stock, dateExpired, typeProduct);

        Product updateProduct = handlingService.updateProduct(product);

        if (updateProduct != null) {

            array[0] = product.getIdProduct();
            array[1] = product.getDescription();
            array[2] = Double.valueOf(product.getValue()).toString();
            array[3] = Integer.valueOf(product.getStock()).toString();
            array[4] = (product.getDateExpired()).toString();
            array[5] = (product.getTypeProduct()).toString();

            return array;
        } else {
            return null;
        }
    }

    /**
     * Get a two-dimensional array containing information about all products.
     *
     * @return A two-dimensional array with product information or null if no products are found.
     */
    public String [][] getProduct(){

        if (handlingService.getAllProducts().length > 0){

            String [][] array = new String[handlingService.getAllProducts().length][6];

            for (int j = 0; j < handlingService.getAllProducts().length; j++) {

                array[j][0] = handlingService.getAllProducts()[j].getIdProduct();
                array[j][1] = handlingService.getAllProducts()[j].getDescription();
                array[j][2] = Double.valueOf(handlingService.getAllProducts()[j].getValue()).toString();
                array[j][3] = Integer.valueOf(handlingService.getAllProducts()[j].getStock()).toString();
                array[j][4] = (handlingService.getAllProducts()[j].getDateExpired()).toString();
                array[j][5] = (handlingService.getAllProducts()[j].getTypeProduct()).toString();
            }

            return array;
        }else {
            return null;
        }
    }
    /**
     * Add a new bill to the system.
     *
     * @param bill An array containing bill information, including the bill number and date.
     * @return true if the bill is successfully added, false otherwise.
     */
    public boolean addBill(String[] bill) {
        String number = bill[0];
        LocalDate dateBill = LocalDate.parse(bill[1]);
        Bill bill1 = new Bill(number, dateBill);
        return handlingService.addBill(bill1);
    }

    /**
     * Add details to a bill by specifying the bill number, product ID, and quantity.
     *
     * @param number The bill number to which details will be added.
     * @param Id     The product ID to be added as a detail.
     * @param cant   The quantity of the product to be added as a detail.
     * @return true if the details are successfully added to the bill, false otherwise.
     */
    public boolean addDetails(Bill number, Product Id, Detail cant) {
        return handlingService.addProductToBill(number.getNumber(), Id.getIdProduct(), cant.getCant());
    }

     /**
     * Calculate the total value of a bill.
     *
     * @param bill The bill for which the total value will be calculated.
     * @return The total value of the bill.
     */
    public double calculateBillTotal(Bill bill){
        return handlingService.calculateBillTotal(bill.getNumber());
    }

     /**
     * Get product details from a bill and return them as an array of strings.
     *
     * @param bill The bill from which product details will be retrieved.
     * @return An array of strings containing product details or null if no details are found.
     */
     public String[] checkBill(Bill bill) {
         Product[] products = handlingService.checkBill(bill.getNumber());
         if (products != null) {
             String[] productArray = new String[products.length];
             for (int i = 0; i < products.length; i++) {
                 productArray[i] = getProductsString(products[i]);
             }
             return productArray;
         } else {
             return null;
         }
     }
    

    private String getProductsString(Product product) {
        return String.join(",", product.getIdProduct(), product.getDescription(),
                Double.toString(product.getValue()), Integer.toString(product.getStock()),
                product.getDateExpired().toString(), product.getTypeProduct().toString());
    }

    /**
     * Update the stock of a product and return the new stock quantity.
     *
     * @param product   The product whose stock will be updated.
     * @param newStock  The new stock quantity to set.
     * @return The updated stock quantity or -1 if the update is not successful.
     */
    public int updateStock(Product product, int newStock) {
        Product updateProduct = handlingService.updateStock(product.getIdProduct(), product.getStock());
        int stockActual = product.getStock() - newStock;
        return stockActual;
    }

    
    /**
     * Get a two-dimensional array containing information about all bills.
     *
     * @return A two-dimensional array with bill information or null if no bills are found.
     */
    public String[][] getBill() {

        if (handlingService.getBills().length > 0) {

            String[][] array = new String[handlingService.getBills().length][2];

            for (int j = 0; j < handlingService.getBills().length; j++) {

                array[j][0] = handlingService.getBills()[j].getNumber();
                array[j][1] = (handlingService.getBills()[j].getDateBill()).toString();

            }

            return array;
        } else {
            return null;
        }
    }
    /**
     * Find a bill by its number and return the bill object.
     *
     * @param number The unique identifier of the bill to find.
     * @return The bill object or null if the bill is not found.
     */
    public Bill findBill(String number) {
        return handlingService.findBillByNumber(number);
    }
    
}
