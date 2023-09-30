package logic;

import java.time.LocalDate;

public class Product {
    private String idProduct;
    private String description;
    private double value;
    private int stock;
    public byte STOCK_MIN = 5;
    private LocalDate dateExpired;
    private ETypeProduct typeProduct;

    public Product(){

    }
    public Product(String idProduct, String description, double value, int stock,LocalDate dateExpired, ETypeProduct typeProduct) {
        this.idProduct = idProduct;
        this.description = description;
        this.value = value;
        this.stock = stock;
        this.dateExpired = dateExpired;
        this.typeProduct = typeProduct;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public byte getSTOCK_MIN() {
        return STOCK_MIN;
    }

    public void setSTOCK_MIN(byte STOCK_MIN) {
        this.STOCK_MIN = STOCK_MIN;
    }

    public LocalDate getDateExpired() {
        return dateExpired;
    }

    public void setDateExpired(LocalDate dateExpired) {
        this.dateExpired = dateExpired;
    }

    public ETypeProduct geteTypeProduct() {
        return typeProduct;
    }

    public void seteTypeProduct(ETypeProduct eTypeProduct) {
        this.typeProduct = eTypeProduct;
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
                ", typeProduct= " + typeProduct +
                '}';
    }
}
