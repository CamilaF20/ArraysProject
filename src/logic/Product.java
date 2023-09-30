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

    public Product() {
    }

    public Product(String idProduct, String description, double value, int stock, LocalDate dateExpired,
            ETypeProduct typeProduct) {
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

    public ETypeProduct getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(ETypeProduct eTypeProduct) {
        this.typeProduct = eTypeProduct;
    }

    public boolean isExpired() {

        LocalDate actualDate = LocalDate.now();
        int actualYear = actualDate.getYear();
        int actualMonth = actualDate.getMonthValue();
        int actualDay = actualDate.getDayOfMonth();

        int expirationYear = dateExpired.getYear();
        int expirationMonth = dateExpired.getMonthValue();
        int expirationDay = dateExpired.getDayOfMonth();

        if (expirationYear < actualYear) {
            return true;
        } else if (expirationYear == actualYear) {
            if (expirationMonth < actualMonth) {
                return true;
            } else if (expirationMonth == actualMonth && expirationDay < actualDay) {
                return true;
            }
        }

        return false;
    }

    public double calcIva() {

     return switch (this.typeProduct) {
                case ASEO -> this.value * 0.14;
                case MEDICINAS -> this.value* 0.04;
                case LICORES -> this.value * 0.19;
                case VIVERES -> this.value * 0.08;
         default -> 0.0; }; 
    }

    @Override
    public String toString() {
        return "Product{ " +
                "Id Product= " + idProduct + '\'' +
                ", description= " + description + '\'' +
                ", value= " + value +
                ", stock= " + stock +
                ", STOCK_MIN= " + STOCK_MIN +
                ", dateExpired= " + dateExpired +
                ", typeProduct= " + typeProduct +
                '}';
    }
}
