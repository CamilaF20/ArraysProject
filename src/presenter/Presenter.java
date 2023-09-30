package presenter;

import logic.ETypeProduct;
import logic.HandlingService;
import logic.Product;

import java.time.LocalDate;

public class Presenter {
    private HandlingService handlingService;

    public Presenter() {
        this.handlingService = new HandlingService();
    }

    public String[] findId(String number){

        Product product = handlingService.findId(number);

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

        Product product = handlingService.delete(Id);

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
    public int manageProducts(){
        return 0;
    }
    //- El sistema debe permitir agregar facturas al sistema
    public int addInvoice() {
        return 0;
    }

    //- El sistema debe permitir adicionar detalles a una factura
    public String addDetails() {
        return null;
    }

    //- El sistema debe permitir actualizar las existencias de los productos
    public int updateStockProduct(){
        return 0;
    }

    //- El sistema debe permitir consultar los detalles de una factura
    public String checkInvoice(){
        return null;
    }
}
