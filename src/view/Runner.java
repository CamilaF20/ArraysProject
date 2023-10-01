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

    public void menu() {
        int option;
        do {
        System.out.println("************* MENU *********** \n" +
                "1. Manage products \n" +
                "2. Add invoice\n" +
                "3. Add Details invoice\n" +
                "4. Update stock products\n" +
                "5. Check details invoice\n" +
                "6. Exit");

        option = sc.nextInt();
            switch (option) {
                case 1:
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
                                System.out.println("Invalid Option");
                                break;
                        }
                    }while (optionMenu != 5);
                case 2:

                case 3:

                case 4:

                case 5:

                case 6:
                    break;

                default:
            }
        } while (option != 5);
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
    }

    public void findProduct() {
        System.out.println("Enter the plate ");
        String IdFind = sc.next();

        String[] findId = presenter.findId(IdFind);
        if (findId == null) {
            System.err.println("Invalid Product");
        } else {

            System.out.println(" Vehicle Found ");
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
        System.out.println("Enter de type product");
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
}
