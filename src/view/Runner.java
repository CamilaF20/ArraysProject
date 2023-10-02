package view;

import logic.Bill;
import logic.Product;
import presenter.Presenter;

import java.util.Enumeration;
import java.time.LocalDate;
import java.util.Scanner;

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
        System.out.println("************* MENU *********** \n" +
                "1. Manage products \n" +
                "2. Add Bill\n" +
                "3. Add Details Bill\n" +
                "4. Update stock products\n" +
                "5. Check details Bill\n" +
                "6. Exit");

        option = sc.nextInt();
            switch (option) {
                case 1:
                    try {
                        int optionMenu;
                        do {
                            System.out.println("******** Menu ********\n" +
                                    "1. Add Product \n" +
                                    "2. Search Product \n" +
                                    "3. Delete Product \n" +
                                    "4. Update Product \n" +
                                    "5. Exit");

                            optionMenu = sc.nextInt();
                            switch (optionMenu) {
                                case 1 -> runner.addProduct();
                                case 2 -> runner.findProduct();
                                case 3 -> runner.deleteProduct();
                                case 4 -> runner.updateProduct();
                                case 5 -> System.out.println("Completed Execution");
                                default -> System.out.println("Invalid Option");
                            }
                            runner.getProduct();

                        } while (optionMenu != 5);
                    }catch (Exception e){
                        System.err.println("Invalid Option");
                    }
                    break;
                case 2:
                    runner.addBill();
                    runner.getBill();
                    break;
                case 3:
                    runner.addDetails();
                    break;
                case 4:

                case 5:

                case 6:
                    break;

                default:
            }
        } while (option != 5);
    }

    private void addProduct() {

        int addP;
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
            System.out.println("Enter de type product : VIVERES, MEDICINAS, ASEO, LICORES");
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

            System.out.println("Do you want to add another product? Enter 1. No, Enter 2. Yes");
            addP = sc.nextInt();


        } while (addP == 2);
    }

    public void findProduct() {
        System.out.println("Enter the Id ");
        String IdFind = sc.next();

        String[] findId = presenter.findId(IdFind);
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

    public void updateProduct(){
        System.out.println("Enter the Id you want to update");
        String IdUpdate = sc.next();

        System.out.println("Enter the description");
        String description = sc.next();
        System.out.println("Enter the value");
        String value = sc.next();
        System.out.println("Enter the stock");
        String stock = sc.next();
        System.out.println("Enter the date expired año-mes-dia");
        String date = sc.next();
        System.out.println("Enter the type product: VIVERES, MEDICINAS, ASEO, LICORES");
        String type = sc.next();

        String [] update = new String[6];

        update[0] = IdUpdate;
        update[1] = description;
        update[2] = value;
        update[3] = stock;
        update[4] = date;
        update[5] = type;

        String[] updateProduct = presenter.update(update);
        if (updateProduct != null) {
            System.out.println("The product was updated");
            for (int i = 0; i < updateProduct.length ; i++){
                System.out.println(updateProduct[i]);
            }
        } else {
            System.err.println("The product does not exist");
        }
    }
    public void getProduct(){
        System.out.println("******* Product List *******");
        for (int i = 0; i < presenter.getProduct().length; i++) {
            System.out.println(" Product Id " + presenter.getProduct()[i][0]);
            for (int j = 0; j < presenter.getProduct()[i].length; j++) {
                System.out.println(presenter.getProduct()[i][j]);
            }
        }
    }

    public void addBill(){
        int add;
        do {
            System.out.println("Enter the Bill number");
            String number = sc.next();
            LocalDate dateBill = LocalDate.now();

            Bill bill = new Bill(number, dateBill);

            String[] array = new String[2];

            array[0] = number;
            array[1] = dateBill.toString();
            System.out.println(bill);

            if (presenter.addBill(array)  == true) {
                System.out.println("The bill was added");
            } else {
                System.err.println("The bill was not added");
            }

            System.out.println("Do you want to add another bill? Enter 1. No, Enter 2. Yes");
            add = sc.nextInt();

        }while (add == 2);
    }
    public void addDetails(){
        runner.getProduct();
        runner.getBill();
        System.out.println("Enter the bill number you want to edit");
        int number = sc.nextInt();
        System.out.println("Enter the ID of the product you want to add");
        int option = sc.nextInt();
        System.out.println("Enter the cant you wish to purchase");
        int cant = sc.nextInt();
    }
    public void updateStock(){

    }
    public void checkBill(){

    }
    public void getBill(){
        System.out.println("******* Bill List ******");
        for (int i = 0; i < presenter.getBill().length; i++) {
            System.out.println(" Bill Number " + presenter.getBill()[i][0]);
            for (int j = 0; j < presenter.getBill()[i].length; j++) {
                System.out.println(presenter.getBill()[i][j]);
            }
        }
    }
}
