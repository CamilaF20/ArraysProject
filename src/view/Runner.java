package view;

import logic.Product;
import presenter.Presenter;

import java.util.Scanner;

public class Runner {
    Scanner sc = new Scanner(System.in);
    private static Presenter presenter = new Presenter();
    static Runner runner = new Runner();
    public static void main(String[] args) {
        runner.menu();
    }

    public void menu(){
        System.out.println("************* MENU *********** \n" +
                "1. Manage products \n" +
                "2. Add invoice\n" +
                "3. Add Details invoice\n" +
                "4. Update stock products\n" +
                "5. Check details invoice\n" +
                "6. Go out");

        int option = sc.nextInt();

        switch (option){
            case 1:
                runner.addProduct();
                break;
            case 2:
                runner.addBill();

            case 3:

            case 4:

            case 5:

            case 6:
                break;

            default:
        }

    }
    private void addProduct() {

        int add;
        do {
            System.out.println("Enter the Id");
            String Id = sc.next();
            System.out.println("Enter the description");
            String description = sc.next();
            System.out.println("Enter the value");
            String value = sc.next();
            System.out.println("Enter the stock");
            String stock = sc.next();
            System.out.println("Enter the date expired año-mes-dia");
            String date = sc.next();
            System.out.println("Enter de type product");
            String type = sc.next();

            String[] array = new String[6];

            array[0] = Id;
            array[1] = description;
            array[2] = value;
            array[3] = stock;
            array[4] = date;
            array[5] = type;

            if (presenter.addProduct(array) == true) {
                System.out.println("The product was added");
            } else {
                System.err.println("The product was not added");
            }

            System.out.println("Do you want to add another object? Enter 1. No, Enter 2. Yes");
            add = sc.nextInt();


        } while (add == 2);

//        System.out.println(product.toString());
//        System.out.println("You want to add the product for sale, type 1. Yes or 2.Not");
//        int addProduct = sc.nextInt();
//        if (addProduct == 1) {
//            System.out.println("Enter the amount you want to buy: ");
//            int cant1 = sc.nextInt();
//            try {
//                presenter.addSale(product.getName(), product.getValue(), product.getStock(), product.isIva(), product.getTypeProduct(), cant1);
//            } catch (Exception e) {
//                System.err.println("there is not enough quantity");
            }
}
