package logic;

public class Detail {
    private Product product;
    private short cant;

    public Detail(Product product, short cant) {
        this.product = product;
        this.cant = cant;
    }

    public double calcSubtotal(){
        return 0;
    }
}
