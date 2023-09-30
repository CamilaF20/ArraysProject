package logic;

import java.util.Arrays;

/**
 *
 */
public class HandlingService {
    private Product products[];
    private int position;

    public HandlingService() {
        products = new Product[0];
        position = 0;
    }

    /**
     * Metodo que permite la gestion del producto
     * @return
     */
    public int manageProducts() {

        return 0;
    }

    private void ensureCapacity(){
        int newSize = products.length + 1;
        Product[] newArray = new Product [newSize];
        newArray = Arrays.copyOf( products, newSize );
        products = newArray;
    }

    /**
     * Metodo que permite adicionar productos
     * @return retorna si el producto se pudo adicionar o no.
     */
    //Create
    public boolean addProduct(Product product) {
        if (findId(product.getIdProduct()) != null) {
            return false;
        }else {
            ensureCapacity();
            if( position < products.length ){
                products[position] = product;
            }
            position++;
            return true;
        }
    }

    /**
     * Metodo que permite eliminar un producto
     * @param Id recibe el Id del producto que desea eliminar
     * @return retorna el producto que fue eliminado
     */
    //Delete
    public Product delete(String Id) {
        if (findId(Id) != null) {
            int deletePosition = 0;
            for (int i = 0; i < products.length; i++) {
                if (products[i].getIdProduct().equals(Id)) {
                    deletePosition = i;
                }
            }
            Product deleteProduct = products[deletePosition];
            this.products[deletePosition] = null;

            Product productsAux[] = new Product[products.length - 1];
            int positionAux = 0;

            for (int i = 0 ; i < products.length ; i++){
                if (products[i] != null){
                    productsAux[positionAux] = products[i];
                    positionAux++;
                }
            }
            products = productsAux;
            position = position - 1;
            return deleteProduct;
        }
        return null;
    }

    /**
     * Metodo que permite actualizar un producto
     * @param Id Recibe el Id del producto que desea actualizar
     * @return retorna el producto actualizado
     */
    //Update
    public Product update (String Id) {
        return null;
    }

    /**
     * Metodo que permite buscar un producto
     * @param Id recibe el Id del producto que desea buscar
     * @return retorna el producto encontrado, en caso de no encontarlo retorna nulo.
     */
    //read
    public Product findId(String Id){
        for( int index = 0 ; index < products.length ; index++ ){
            if ( Id.equals( products[index].getIdProduct() ) ){
                Product aux = products[index];
                return aux;
            }
        }
        return null;
    }

    /**
     * Metodo que almacena el arreglo de los productos exitentes
     * @return retorna el arreglo de los productos
     */
    //Read
    //Retorna el arreglo de los productos
    public Product[] getProduct(){
        return null;
    }

    /**
     * Metodo que permite agregar facturas al sistema.
     * @return retorna la factura agregada
     */
    public Bill addBill() {
        return null;
    }

    /**
     * Metodo que permite adicionar detalles a una factura.
     * @Param number recibe el numero de la factura a la cual desea añadirle detalles
     * @return retorna la factura con los mnuevos detalles
     */
    public Bill addDetails(String number){
        return null;
    }

    /**
     *  Metodo que permite actualizar las existencias de los productos
     * @param product recide los detalles de los productos para actualizar las exiastemncias
     * @return retorna los detalles de los peroductos actualizados
     */
    public Detail updateStock(Detail product){
        return null;
    }

    /**
     * Metodo que permite consultar los detalles de una factura.
     * @param number recibe el numero de la factura a consultar
     * @return retorna la factura consultada
     */
    public Bill checkBill(String number){
        return null;
    }
}
