package logic;

import java.time.LocalDate;


/**
 * The `Product` class represents a product with various attributes such as ID, description, value, stock, expiration date, and product type.
 */
public class Product {
    private String idProduct;
    private String description;
    private double value;
    private int stock;
    public byte STOCK_MIN = 5;
    private LocalDate dateExpired;
    private ETypeProduct typeProduct;

    /**
     * Default constructor for creating an instance of the `Product` class.
     */
    public Product() {
    }

    /**
     * Constructor to create an instance of the `Product` class with specified attributes.
     *
     * @param idProduct     The unique identifier of the product.
     * @param description   The description or name of the product.
     * @param value         The price or value of the product.
     * @param stock         The current stock quantity of the product.
     * @param dateExpired   The expiration date of the product.
     * @param typeProduct   The type or category of the product.
     */
    public Product(String idProduct, String description, double value, int stock, LocalDate dateExpired,
                   ETypeProduct typeProduct) {
        this.idProduct = idProduct;
        this.description = description;
        this.value = value;
        this.stock = stock;
        this.dateExpired = dateExpired;
        this.typeProduct = typeProduct;
    }

    /**
     * Get the unique identifier of the product.
     *
     * @return The product's ID.
     */
    public String getIdProduct() {
        return idProduct;
    }

    /**
     * Set the unique identifier of the product.
     *
     * @param idProduct The new ID for the product.
     */
    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    /**
     * Get the description or name of the product.
     *
     * @return The product's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description or name of the product.
     *
     * @param description The new description for the product.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the price or value of the product.
     *
     * @return The product's value.
     */
    public double getValue() {
        return value;
    }

    /**
     * Set the price or value of the product.
     *
     * @param value The new value for the product.
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Get the current stock quantity of the product.
     *
     * @return The product's stock quantity.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Set the current stock quantity of the product.
     *
     * @param stock The new stock quantity for the product.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Get the minimum stock threshold for the product.
     *
     * @return The minimum stock threshold.
     */
    public byte getSTOCK_MIN() {
        return STOCK_MIN;
    }

    /**
     * Get the expiration date of the product.
     *
     * @return The product's expiration date.
     */
    public LocalDate getDateExpired() {
        return dateExpired;
    }

    /**
     * Set the expiration date of the product.
     *
     * @param dateExpired The new expiration date for the product.
     */
    public void setDateExpired(LocalDate dateExpired) {
        this.dateExpired = dateExpired;
    }

    /**
     * Get the type or category of the product.
     *
     * @return The product's type or category.
     */
    public ETypeProduct getTypeProduct() {
        return typeProduct;
    }

    /**
     * Set the type or category of the product.
     *
     * @param eTypeProduct The new type or category for the product.
     */
    public void setTypeProduct(ETypeProduct eTypeProduct) {
        this.typeProduct = eTypeProduct;
    }

    /**
     * Check if the product has expired based on its expiration date.
     *
     * @return true if the product has expired, false otherwise.
     */
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

    /**
     * Calculate the value of the IVA (tax) for the product based on its type.
     *
     * @return The calculated IVA value.
     */
    public double calcIva() {
        return switch (this.typeProduct) {
            case ASEO -> this.value * 0.14;
            case MEDICINAS -> this.value * 0.04;
            case LICORES -> this.value * 0.19;
            case VIVERES -> this.value * 0.08;
        };
    }
}
