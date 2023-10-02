package presenter;

import logic.*;

import java.time.LocalDate;

public class Presenter {
    private HandlingService handlingService;

    public Presenter() {
        this.handlingService = new HandlingService();
    }

    public String[] findId(String number){

        Product product = handlingService.findProductById(number);

        if (product != null){
            String [] array = new String[6];

            array[0] = product.getIdProduct();
            array[1] = product.getDescription();
            array[2] = Double.valueOf(product.getValue()).toString();
            array[3] = Integer.valueOf(product.getStock()).toString();
            array[4] = (product.getDateExpired()).toString();
            array[5] = (product.getTypeProduct()).toString();

            return array;

        }else {
            return null;
        }
    }

    public boolean addProduct(String[] array){

        String Id = array[0];
        String description = array[1];
        double value = Double.parseDouble(array[2]);
        int stock = Integer.parseInt(array[3]);
        LocalDate dateExpired = LocalDate.parse(array[4]);
        ETypeProduct typeProduct = ETypeProduct.valueOf(array[5]);

        Product product = new Product(Id, description, value, stock,dateExpired, typeProduct);

        return handlingService.addProduct(product);
    }

    public String[] delete (String Id) {

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
    public String[] update(String[] array) {

        String Id = array[0];
        String description = array[1];
        double value = Double.parseDouble(array[2]);
        int stock = Integer.parseInt(array[3]);
        LocalDate dateExpired = LocalDate.parse(array[4]);
        ETypeProduct typeProduct = ETypeProduct.valueOf(array[5]);

        Product product = new Product(Id,description,value,stock,dateExpired,typeProduct);

        Product updateProduct = handlingService.updateProduct(product);

        if (updateProduct != null){

            String [] arrays = new String[6];

            array[0] = product.getIdProduct();
            array[1] = product.getDescription();
            array[2] = Double.valueOf(product.getValue()).toString();
            array[3] = Integer.valueOf(product.getStock()).toString();
            array[4] = (product.getDateExpired()).toString();
            array[5] = (product.getTypeProduct()).toString();

            return array;
        }else {
            return null;
        }
    }
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
    //- El sistema debe permitir agregar facturas al sistema
    public boolean addBill(String[] bill) {
        String number = bill[0];
        LocalDate dateBill = LocalDate.parse(bill[1]);
        Bill bill1 = new Bill(number, dateBill);
        return handlingService.addBill(bill1);
    }

    //- El sistema debe permitir adicionar detalles a una factura
    public boolean addDetails(Bill number, Product Id, Detail cant) {
        return handlingService.addProductToBill(number.getNumber(), Id.getIdProduct(), cant.getCant());
    }

    public double calculateBillTotal(Bill bill){
        return handlingService.calculateBillTotal(bill.getNumber());
    }

    //- El sistema debe permitir consultar los detalles de una factura

    public String[] checkBill(Bill bill){
        Product[] products = handlingService.checkBill(bill.getNumber());
        if (products != null){
            String[] productArray = new String[products.length];
            for (int i = 0 ; i < products.length ; i++){
                productArray[i] = getProductsString(products[i]);
            }
            return productArray;
        }else {
            return null;
        }
    }
    private String getProductsString(Product product) {
        return String.join(",", product.getIdProduct(), product.getDescription(),
                Double.toString(product.getValue()), Integer.toString(product.getStock()),
                product.getDateExpired().toString(), product.getTypeProduct().toString());
    }

    //- El sistema debe permitir actualizar las existencias de los productos
    public int updateStock(Product product, int newStock) {
        Product updateProduct = handlingService.updateStock(product.getIdProduct(), product.getStock());
        int stockActual = product.getStock() - newStock;
        return stockActual;
    }
    public String [][] getBill(){

        if (handlingService.getBills().length > 0){

            String [][] array = new String[handlingService.getBills().length][2];

            for (int j = 0; j < handlingService.getBills().length; j++) {

                array[j][0] = handlingService.getBills()[j].getNumber();
                array[j][1] = (handlingService.getBills()[j].getDateBill()).toString();

            }

            return array;
        }else {
            return null;
        }
    }
    //- El sistema debe permiti
}
