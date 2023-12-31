package view;
import logic.Bill;
import logic.Detail;
import logic.Product;
import presenter.Presenter;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

   
    /**
    * The `Runner` class represents the main user interface for interacting with the system.
    */
    public class Runner {
        Scanner sc = new Scanner(System.in);
        private static Presenter presenter = new Presenter();
        static Runner runner = new Runner();
    
        public static void main(String[] args) {
            runner.menu();
        }
    
        public void menu() {
            int option;
            do {
                System.out.println("\n ************* MENU *********** \n" +
                        "1. Manage products \n" +
                        "2. Add Bill\n" +
                        "3. Add Details Bill\n" +
                        "4. Update stock products\n" +
                        "5. Check details Bill\n" +
                        "6. Exit");
    
                try {
                    option = sc.nextInt();
                    switch (option) {
                        case 1:
                            manageProductsMenu();
                            break;
                        case 2:
                            runner.addBill();
                            runner.getBill();
                            break;
                        case 3:
                            runner.addDetails();
                            break;
                        case 4:
                            runner.updateStock();
                            break;
                        case 5:
                            runner.checkBill();
                            break;
                        case 6:
                            break;
                        default:
                            System.err.println("Invalid Option");
                    }
                } catch (InputMismatchException e) {
                    System.err.println("Invalid input. Please enter a valid option.");
                    sc.nextLine(); 
                    option = 0; 
                }
            } while (option != 6);
        }
    
    
        public void manageProductsMenu() {
            int optionMenu;
            do {
                System.out.println("\n******** Menu ********\n" +
                        "1. Add Product \n" +
                        "2. Search Product \n" +
                        "3. Delete Product \n" +
                        "4. Update Product \n" +
                        "5. Exit");
    
                try {
                    optionMenu = sc.nextInt();
                    switch (optionMenu) {
                        case 1:
                            runner.addProduct();
                            break;
                        case 2:
                            runner.findProduct();
                            break;
                        case 3:
                            runner.deleteProduct();
                            break;
                        case 4:
                            runner.updateProduct();
                            break;
                        case 5:
                            System.out.println("Completed Execution");
                            break;
                        default:
                            System.err.println("Invalid Option");
                    }
                    runner.getProduct();
                } catch (InputMismatchException e) {
                    System.err.println("Invalid input. Please enter a valid option.");
                    sc.nextLine(); 
                    optionMenu = 0; 
                }
            } while (optionMenu != 5);
        }
    

        public void addProduct() {
            int addP = 0;
            do {
                System.out.println("Enter the Id");
                String Id = sc.next();
        
                // Check if the product ID already exists
                if (presenter.findId(Id) != null) {
                    System.err.println("Product with ID " + Id + " already exists. Please enter a unique ID.");
                    continue; // Continue the loop to allow the user to enter a different ID
                }
        
                System.out.println("Enter the description");
                String description = sc.next();
                System.out.println("Enter the value");
                double value = sc.nextDouble();
        
                int stock;
                do {
                    System.out.println("Enter the stock (minimum 5)");
                    stock = sc.nextInt();
                    if (stock < 5) {
                        System.err.println("Stock cannot be less than 5. Please enter a valid stock.");
                    }
                } while (stock < 5);
        
                LocalDate expirationDate = null;
                boolean validDate = false;
        
                while (!validDate) {
                    System.out.println("Enter the date expired (yyyy-MM-dd)");
                    String date = sc.next();
        
                    try {
                        expirationDate = LocalDate.parse(date);
        
                        if (expirationDate.isBefore(LocalDate.now())) {
                            System.err.println("The entered date is in the past. Please enter a future date.");
                        } else {
                            validDate = true;
                        }
                    } catch (DateTimeParseException e) {
                        System.err.println("Invalid date format. Please use yyyy-MM-dd.");
                    }
                }
        
                String type;
                boolean validType = false;
                do {
                    System.out.println("Enter the type product: VIVERES, MEDICINAS, ASEO, LICORES");
                    type = sc.next().toUpperCase();
                    if (type.equals("VIVERES") || type.equals("MEDICINAS") || type.equals("ASEO") || type.equals("LICORES")) {
                        validType = true;
                    } else {
                        System.err.println("Invalid type. Please enter a valid type.");
                    }
                } while (!validType);
        
                String[] array = new String[6];
        
                array[0] = Id;
                array[1] = description;
                array[2] = Double.toString(value);
                array[3] = Integer.toString(stock);
                array[4] = expirationDate.toString(); // Store the formatted date
                array[5] = type;
        
                if (presenter.addProduct(array)) {
                    System.out.println("The product was added");
                } else {
                    System.err.println("The product was not added");
                }
        
                System.out.println("Do you want to add another product? Enter 1. No, Enter 2. Yes");
                addP = sc.nextInt();
        
            } while (addP == 2);
        }

    public void findProduct() {
        System.out.println("Enter the Id ");
        String IdFind = sc.next();

        String[] findId = presenter.findById(IdFind);
        if (findId == null) {
            System.err.println("Invalid Product");
        } else {

            System.out.println(" Product Found ");
            for (int i = 0; i < findId.length; i++) {
                System.out.println(findId[i]);
            }
        }
    }

    public void deleteProduct() {

        System.out.println("Enter the Id");
        String IdDelete = sc.next();

        String[] delete = presenter.delete(IdDelete);
        if (delete == null) {
            System.err.println("Invalid Product");

        } else {
            System.out.println(" Product Delete ");
            for (int i = 0; i < delete.length; i++) {
                System.out.println(delete[i]);
            }
        }
    }

    public void updateProduct() {
    System.out.println("Enter the Id you want to update");
    String IdUpdate = sc.next();

    System.out.println("Enter the description");
    String description = sc.next();
    System.out.println("Enter the value");
    double value = sc.nextDouble();
    System.out.println("Enter the stock");
    int stock = sc.nextInt();

    LocalDate expirationDate = null;
    boolean validDate = false;

    while (!validDate) {
        System.out.println("Enter the date expired (yyyy-MM-dd)");
        String date = sc.next();

        try {
           
            expirationDate = LocalDate.parse(date);

            
            if (expirationDate.isBefore(LocalDate.now())) {
                System.err.println("The entered date is in the past. Please enter a future date.");
            } else {
                validDate = true;
            }
        } catch (DateTimeParseException e) {
            System.err.println("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    System.out.println("Enter the type product: VIVERES, MEDICINAS, ASEO, LICORES");
    String type = sc.next().toUpperCase();

    String[] update = new String[6];

    update[0] = IdUpdate;
    update[1] = description;
    update[2] = Double.toString(value);
    update[3] = Integer.toString(stock);
    update[4] = expirationDate.toString(); 
    update[5] = type;

    String[] updateProduct = presenter.update(update);
    if (updateProduct != null) {
        System.out.println("The product was updated");
        for (int i = 0; i < updateProduct.length; i++) {
            System.out.println(updateProduct[i]);
        }
    } else {
        System.err.println("The product does not exist");
    }
}
    public void getProduct() {
        System.out.println("\n******* Product List *******");
        String[][] products = presenter.getProduct();
        if (products != null) {
            for (int i = 0; i < products.length; i++) {
                System.out.println("\n Product Id " + products[i][0]);
                for (int j = 0; j < products[i].length; j++) {
                    System.out.println(products[i][j]);
                }
            }
        } else {
            System.err.println("No products available.");
        }
    }

    public void addBill() {
        int add=0;
        do {
            System.out.println("Enter the Bill number");
            String number = sc.next();
    
            if (presenter.findBill(number) != null) {
                System.err.println("Bill with number " + number + " already exists. Please enter a unique number.");
                continue;
            }
    
            if (presenter.getProduct() == null || presenter.getProduct().length == 0) {
                System.err.println("No products available. Cannot create a bill.");
                return;
            }
    
            LocalDate dateBill = LocalDate.now();
    
            String[] array = new String[2];
    
            array[0] = number;
            array[1] = dateBill.toString();
    
            if (presenter.addBill(array)) {
                System.out.println("The bill was added");
            } else {
                System.err.println("The bill was not added");
            }
    
            System.out.println("Do you want to add another bill? Enter 1. No, Enter 2. Yes");
            add = sc.nextInt();
    
        } while (add == 2);
    }

    public void addDetails() {
        runner.getBill();
        System.out.println("Enter the bill number you want to edit");
        String number = sc.next();
    
        Bill bill = presenter.findBill(number);
        if (bill == null) {
            System.err.println("The bill does not exist.");
            return;
        }
    
        double totalValue = 0.0; // Variable para calcular el valor total de los detalles agregados
    
        while (true) {
            runner.getProduct();
            System.out.println("Enter the ID of the product you want to add (or type 'done' to finish adding products)");
            String productId = sc.next();
    
            if (productId.equalsIgnoreCase("done")) {
                break;
            }
    
            Product product = presenter.findId(productId);
            if (product == null) {
                System.err.println("The product does not exist.");
                continue;
            }
    
            int availableStock = product.getStock();
    
            System.out.println("Available stock: " + availableStock);
    
            System.out.println("Enter the quantity you wish to purchase");
            int quantity = sc.nextInt();
    
            if (quantity <= 0) {
                System.err.println("Invalid quantity. Please enter a positive quantity.");
                continue;
            }
    
            if (quantity > availableStock) {
                System.err.println("Insufficient stock. Available stock: " + availableStock);
                continue;
            }
    
            Detail detail = new Detail(product, (short) quantity);
            boolean detailsAdded = presenter.addDetails(bill, product, detail);
    
            if (detailsAdded) {
                // Resta la cantidad comprada del stock del producto
                product.setStock(product.getStock()-quantity);
                int cant = quantity;
                // Calcular el subtotal del detalle y agregarlo al valor total
                double detailSubtotal = product.getValue() * cant;
                totalValue += detailSubtotal;
    
                System.out.println("Product added to the bill.");
            } else {
                System.err.println("Details not added to the bill.");
            }
        }

        System.out.println("Details added to the bill successfully.");
        System.out.println("Total value of the bill: " + totalValue);
    }
    
    
    public void updateStock() {
        runner.getProduct();
        System.out.println("Enter the ID of the product you want to update stock");
        String productId = sc.next();
    
        Product product = presenter.findId(productId);
        if (product == null) {
            System.err.println("The product does not exist.");
            return;
        }
    
        System.out.println("Enter the additional stock quantity (positive to add, negative to subtract)");
        int additionalStock = sc.nextInt();
    
        if (additionalStock == 0) {
            System.err.println("Invalid stock quantity. Stock quantity cannot be zero.");
            return;
        }
    
        int updatedStock = product.getStock() + additionalStock;
    
        if (updatedStock < 5) {
            System.err.println("Warning: The new stock is less than 5. Minimum stock is 5.");
            return;
        }
    
    
        if (updatedStock >= 5) {
            System.out.println("Stock updated successfully. Current stock: " + updatedStock);
        } else {
            System.err.println("Stock update failed. Current stock is less than 5.");
        }

        product.setStock(updatedStock);

    
    System.out.println("Stock updated successfully. Current stock: " + product.getStock());
    }
    
    public void checkBill() {
        runner.getBill();
        System.out.println("Enter the bill number you want to check");
        String number = sc.next();
    
        Bill bill = presenter.findBill(number);
        if (bill == null) {
            System.err.println("The bill does not exist.");
            return;
        }
    
        ArrayList<Product> productsInBill = bill.getDetails(); // Obtener detalles de la factura
    
        if (!productsInBill.isEmpty()) {
            System.out.println("Bill Number: " + bill.getNumber());
            System.out.println("Date: " + bill.getDateBill());
            System.out.println("*** Products ***");
    
            double totalAmountWithTax = 0.0;
    
            for (Product product : productsInBill) {
                // No necesitas obtener la cantidad del detalle aquí, ya que tienes los productos directamente
                int quantity = 0; // Cada producto aparece una vez
                quantity = product.getStock();

                double productPrice = product.getValue();
                double itemTotal = productPrice * quantity;
    
                double ivaRate = switch (product.getTypeProduct()) {
                    case ASEO -> 0.14;
                    case MEDICINAS -> 0.04;
                    case LICORES -> 0.19;
                    case VIVERES -> 0.08;
                };
    
                double itemIva = itemTotal * ivaRate;
    
                // Calcular el costo total del artículo con impuestos
                double itemTotalWithTax = itemTotal + itemIva;
    
                totalAmountWithTax += itemTotalWithTax;
    
                System.out.println("Product: " + product.getDescription());
                System.out.println("Quantity: " + quantity);
                System.out.println("Total for this product : " + itemTotalWithTax);
            }
    
            System.out.println("Total Purchase Amount : " + totalAmountWithTax);
        } else {
            System.out.println("No details found for this bill.");
        }
    }

    public void getBill() {
        System.out.println("\n******* Bill List ******");
        String[][] bills = presenter.getBill();
        if (bills != null) {
            for (int i = 0; i < bills.length; i++) {
                System.out.println("\n Bill Number " + bills[i][0]);
                for (int j = 0; j < bills[i].length; j++) {
                    System.out.println(bills[i][j]);
                }
            }
        } else {
            System.err.println("No bills available.");
        }
    }
    
}