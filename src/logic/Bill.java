package logic;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The `Bill` class represents an invoice containing product details and associated information.
 */
public class Bill {

    // Attributes of the Bill class
    private ArrayList<Detail> details;
    private String number;
    private LocalDate dateBill;

    /**
     * Constructor to create an instance of the `Bill` class.
     *
     * @param number    The invoice number.
     * @param dateBill  The invoice date.
     */
    public Bill(String number, LocalDate dateBill) {
        this.number = number;
        this.dateBill = dateBill;
        this.details = new ArrayList<Detail>();
    }

    /**
     * Get the invoice number.
     *
     * @return The invoice number.
     */
    public String getNumber() {
        return number;
    }

    /**
     * Set the invoice number.
     *
     * @param number The new invoice number.
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Get the invoice date.
     *
     * @return The invoice date.
     */
    public LocalDate getDateBill() {
        return dateBill;
    }

    /**
     * Set the invoice date.
     *
     * @param dateBill The new invoice date.
     */
    public void setDateBill(LocalDate dateBill) {
        this.dateBill = dateBill;
    }

    /**
     * Calculate the total value of the invoice by summing the subtotals of all details.
     *
     * @return The total value of the invoice.
     */
    public double calcTotal() {
        double totalValue = 0;
        for (Detail detail : details) {
            totalValue += detail.calcSubtotal();
        }
        return totalValue;
    }

    /**
     * Add a new detail to the invoice.
     *
     * @param product The product to add as a detail.
     * @param cant    The quantity of products.
     * @return true if the detail was added successfully, false otherwise.
     */
    public boolean addDetail(Product product, short cant) {
        Detail newDetail = new Detail(product, cant);
        return details.add(newDetail);
    }

    /**
     * Get a list of products that are in the invoice.
     *
     * @return A list of products in the invoice.
     */
    public ArrayList<Product> getDetails() {
        ArrayList<Product> products = new ArrayList<>();
        for (Detail detail : details) {
            products.add(detail.getProduct());
        }
        return products;
    }

}
