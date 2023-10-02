package logic;

/**
 * The `Detail` class represents a detailed line item within a bill, including a product and quantity.
 */
public class Detail {
    Product product;
    private short cant;

    /**
     * Constructor to create an instance of the `Detail` class.
     *
     * @param product The product associated with this detail.
     * @param cant    The quantity of the product.
     */
    public Detail(Product product, short cant) {
        this.product = product;
        this.cant = cant;
    }

    /**
     * Calculate the subtotal for this detail, which is the product's value plus its calculated IVA (tax) multiplied by the quantity.
     *
     * @return The subtotal value for this detail.
     */
    public double calcSubtotal() {
        double valueTotal = ((product.getValue() + product.calcIva()) * cant);
        return valueTotal;
    }

    /**
     * Get the product associated with this detail.
     *
     * @return The product in this detail.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Set the product associated with this detail.
     *
     * @param product The new product for this detail.
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Get the quantity of the product in this detail.
     *
     * @return The quantity of the product.
     */
    public short getCant() {
        return cant;
    }

    /**
     * Set the quantity of the product in this detail.
     *
     * @param cant The new quantity of the product.
     */
    public void setCant(short cant) {
        this.cant = cant;
    }
}

