package logic;

public class Detail {
    Product product;
    private short cant;

    public Detail(Product product, short cant) {
        this.product = product;
        this.cant = cant;
    }

    public double calcSubtotal() {

        double valueTotal = ((product.getValue() + product.calcIva()) * cant);
        return valueTotal;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public short getCant() {
        return cant;
    }

    public void setCant(short cant) {
        this.cant = cant;
    }
}
