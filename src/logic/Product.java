package logic;

import java.time.LocalDate;

public class Product {
    private String idProduct;
    private String description;
    private double value;
    private int stock;
    public byte STOCK_MIN = 5;
    private LocalDate dateExpired;
    private ETypeProduct eTypeProduct;

    public Product(){

    }
    public Product(String idProduct, String description, double value, int stock, LocalDate dateExpired) {
        this.idProduct = idProduct;
        this.description = description;
        this.value = value;
        this.stock = stock;
        this.dateExpired = dateExpired;
    }

    public boolean isExpired(){
        return false;
    }
    public double calcIva(){
        return 0;
    }

    @Override
    public String toString() {
        return "Product{" +
                "idProduct='" + idProduct + '\'' +
                ", description='" + description + '\'' +
                ", value=" + value +
                ", stock=" + stock +
                ", STOCK_MIN=" + STOCK_MIN +
                ", dateExpired=" + dateExpired +
                '}';
    }
}
