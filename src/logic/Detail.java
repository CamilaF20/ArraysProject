package logic;

public class Detail {
    private Product product;
    private short cant;

    public Detail(Product product, short cant) {
        this.product = product;
        this.cant = cant;
    }

    public double calcSubtotal() {

        double valueTotal = ((product.getValue() + product.calcIva()) * cant);
        return valueTotal;
    }
}
