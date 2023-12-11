package Presentation;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class UI is the one in charge of gathering external information.
 * It also displays in the screen all the messages.
 *
 * @version 18.0.2 27 November 2023
 * @author Marc Martín
 * @author Joaquim Angas
 */
public class UI {
    /**
     * Constructor of the class UI, it does not contain any attributes
     */
    public UI(){
    }

    Scanner sn = new Scanner(System.in);

    /**
     * method that displays a message
     * @param message = String that contains the message to be displayed
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * method that displays the header of the Program and the loading data message
     */
    private void showTitle(){
        System.out.println("\n" +
                "        ________      ____        \n" +
                "  ___  / / ____/___  / __/_______ \n" +
                " / _ \\/ / /   / __ \\/ /_/ ___/ _ \\\n" +
                "/  __/ / /___/ /_/ / __/ /  /  __/\n" +
                "\\___/_/\\____/\\____/_/ /_/   \\___/ \n");
    }

    /**
     * method that displays the initial message of the program
     * @param fileLoaded = Boolean that indicates if the file has been loaded correctly
     * @return Boolean that indicates if the file has been loaded correctly
     */
    public boolean loadInitial (Boolean fileLoaded){
        showTitle();
        System.out.println("Welcome to elCofre Digital Shopping Experiences. \n");
        System.out.println("Verifying local files...");
        if (!fileLoaded){
            System.out.println("Starting program...");
            return false;
        }
        else{
            System.out.println("Error: The products.json file can’t be accessed. \n");
            System.out.println("Shutting down...");
            return true;
        }
    }

    /**
     * method that displays the main menu of the program
     */
    public void showOptions() {
        System.out.println();
        System.out.println("\t1) Manage Products");
        System.out.println("\t2) Manage Shops");
        System.out.println("\t3) Search Products");
        System.out.println("\t4) List Shops");
        System.out.println("\t5) Your Cart");
        System.out.println();
        System.out.println("\t6) Exit");
        System.out.println();
    }


    /**
     * function that asks the user for an integer
     * @param message = String that contains the message to be displayed
     * @return Integer introduced by the user
     */
    public int askForInteger(String message) {
        while (true){
            try{
                System.out.print(message);
                return sn.nextInt();
            }catch (InputMismatchException e){
                System.out.println("This is not a integer!");
            }finally {
                sn.nextLine();
            }
        }
    }
    /**
     * function that asks the user for a float
     * @param message = String that contains the message to be displayed
     * @return @return float introduced by the user
     */
    public float askForFloat(String message) {
        while (true){
            try{
                System.out.print(message);
                return sn.nextFloat();
            }catch (InputMismatchException e){
                System.out.println("This is not a float!");
            }finally {
                sn.nextLine();
            }
        }
    }
    /**
     * function that asks the user for a char
     * @param message = String that contains the message to be displayed
     * @return char introduced by the user
     */
    public char askForChar(String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = sn.nextLine();
                if (input.length() == 1) {
                    return input.charAt(0);
                } else {
                    System.out.println("Please enter only one character!");
                }
            } catch (InputMismatchException e) {
                System.out.println("This is not a char!");
            }
        }
    }

    /**
     * function that asks the user for a String
     * @param message = String that contains the message to be displayed
     * @return String introduced by the user
     */
    public String askForString(String message) {
        while (true){
            try{
                System.out.print(message);
                return sn.nextLine();
            }catch (InputMismatchException e){
                System.out.println("This is not a string!");
            }
        }
    }
    /**
     * method that displays the submenu of the ManageProducts option
     */
    public void menuProductManager(){
        System.out.println();
        System.out.println("\t1) Create a Product");
        System.out.println("\t2) Remove a Product");
        System.out.println();
        System.out.println("\t3) Back");
        System.out.println();
    }

    /**
     * method that displays the main options of category from products
     */
    public void menuCreateProductCategory() {
        System.out.println();
        System.out.println("The system supports the following product categories:");
        System.out.println();
        System.out.println("A) General");
        System.out.println("B) Reduced Taxes");
        System.out.println("C) Superreduced Taxes");
        System.out.println();
    }

    /***
     * method that displays a message when a product is created
     * @param name = String that contains the name of the product
     * @param brand = String that contains the brand of the product
     */
    public void productCreated(String name, String brand){
        System.out.println();
        System.out.println("The product \""+ name + "\" by \"" + brand + "\" was added to the system.");
    }

    /**
     * method that displays all the products in the system
     * @param names = ArrayList that contains the names and brands of the products
     */
    public void listProducts(ArrayList<String> names) {
        int i = 0;
        System.out.println();
        System.out.println("These are the currently available products:");
        System.out.println();
        while (i < names.size()) {
            String productName = names.get(i);
            String brandName = names.get(i + 1);
            System.out.println("\t" + (i / 2 + 1) + ") \"" + productName + "\" by \"" + brandName + "\"");
            i += 2;
        }
        System.out.println();
        System.out.println("\t" + (i / 2 + 1) + ") Back");
        System.out.println();
    }

    /**
     * method that displays the confirmation of the deletion of a product
     * @param names = ArrayList that contains the name and brand of the product
     */
    public void confirmDeleteProduct(ArrayList<String> names) {
        System.out.println();
        System.out.print("Are you sure you want to remove \"" + names.get(0) + "\" by \"" + names.get(1) + "\"?");
    }

    /**
     * method that displays a message when a product is deleted
     * @param names = ArrayList that contains the name and brand of the product
     */
    public void productDeleted (ArrayList<String> names) {
        System.out.println();
        System.out.println("\"" + names.get(0) + "\" by \"" + names.get(1) + "\" has been withdrawn from sale.");
    }

    /**
     * method that displays the submenu of the ManageShops option
     */
    public void menuShopManager(){
        System.out.println();
        System.out.println("\t1) Create a Shop");
        System.out.println("\t2) Expand a Shop’s Catalogue");
        System.out.println("\t3) Reduce a Shop’s Catalogue");
        System.out.println();
        System.out.println("\t4) Back");
        System.out.println();
    }

    /**
     * method that displays the main options of business models from shops
     */
    public void menuCreateShop(){
        System.out.println("The system supports the following business models: ");
        System.out.println();
        System.out.println("\tA) Maximum Benefits");
        System.out.println("\tB) Loyalty");
        System.out.println("\tC) Sponsored");
        System.out.println();
    }

    /**
     * method that displays a message when a shop is created
     * @param name = String that contains the name of the shop
     */
    public void shopCreated(String name){
        System.out.println();
        System.out.println("\"" + name +  "\"is now a part of the elCofre family.");
    }

    /**
     * method that displays all the products from the system that has been found
     * @param products = ArrayList that contains the names and brands of the products
     * @param shops = ArrayList that contains the names of the shops and the products with their prices
     */
    public void productsFound(ArrayList<String> products, ArrayList<String> shops) {
        int i = 0, flagIsSold = 0;
        System.out.println();
        System.out.println("The following products where found:");
        System.out.println();

        if (products.size() == 0) {
            System.out.println("\tNo products where found!");
        }
        else{
            while (i < products.size()) {
                flagIsSold = 0;
                String productName = products.get(i);
                String brandName = products.get(i + 1);
                System.out.println("\t" + (i / 2 + 1) + ") \"" + productName + "\" by \"" + brandName + "\"");
                int j = 0;
                while (j < shops.size()) {
                    String shopProduct = shops.get(j);
                    String shopName = shops.get(j + 1);
                    String shopPrice = shops.get(j + 2);
                    if (shopProduct.equals(productName)) {
                        if (flagIsSold == 0) {
                            System.out.println("\t\tSold at:");
                        }
                        flagIsSold = 1;
                        System.out.println("\t\t\t- "+shopName+": "+shopPrice);
                    }
                    j += 3;
                }
                if (flagIsSold == 0) {
                    System.out.println("\t\tThis product is not currently being sold in any shops.");
                }
                i += 2;
                System.out.println();
            }
            System.out.println();
            System.out.println("\t" + (i / 2 + 1) + ") Back");
            System.out.println();
        }
    }

    /**
     * method that displays the submenu of the products from ManageShops option
     */
    public void reviewMenu() {
        System.out.println();
        System.out.println("\t1) Read Reviews");
        System.out.println("\t2) Review Product");
        System.out.println();
    }

    /**
     * method that displays that a product has been added to a shop
     * @param prodName = String that contains the name of the product
     * @param prodBrand = String that contains the brand of the product
     * @param shopName = String that contains the name of the shop
     */
    public void productAddedToShop(String prodName, String prodBrand , String shopName) {
        System.out.println();
        System.out.println("\"" + prodName + "\" by \"" + prodBrand + "\" is now being sold at \"" + shopName + "\".");
    }

    /**
     * method that displays that a product has been removed from a shop
     * @param prodName = String that contains the name of the product
     * @param prodBrand = String that contains the brand of the product
     * @param shopName = String that contains the name of the shop
     */
    public void productRemovedFromShop(String prodName, String prodBrand , String shopName) {
        System.out.println();
        System.out.println("\"" + prodName + "\" by \"" + prodBrand + "\" is no longer being sold at \"" + shopName + "\".");
    }

    /**
     * method that displays the reviews of a product
     * @param reviews = ArrayList that contains the reviews of a product
     * @param productName = String that contains the name of the product
     * @param brandName = String that contains the brand of the product
     */
    public void listReviews(ArrayList<String> reviews, String productName, String brandName) {
        System.out.println();
        System.out.println("These are the reviews for \"" + productName + "\" by \"" + brandName + "\":");
        System.out.println();
        int i = 0;
        float average = 0;
        while (i < reviews.size()) {
            int rate = Integer.parseInt(reviews.get(i));
            average += rate;
            String comment = reviews.get(i + 1);
            System.out.println("\t" + rate + "* " + comment);
            i += 2;
        }
        average = (average * 2) / reviews.size();
        System.out.println();
        System.out.println("\tAverage rating: " + average + "*");
    }

    /**
     * method that displays a message when a review is added
     * @param prodName = String that contains the name of the product
     * @param prodBrand = String that contains the brand of the product
     */
    public void reviewDone(String prodName, String prodBrand) {
        System.out.println();
        System.out.println("Thank you for your review of \"" + prodName + "\" by \"" + prodBrand + "\".");
    }

    /**
     * method that displays all the shops in the system
     * @param shops = ArrayList that contains the names of the shops
     */
    public void listShops(ArrayList<String> shops) {
        System.out.println();
        System.out.println("The elCofre family is formed by the following shops:");
        System.out.println();
        for (int i = 0; i < shops.size(); i++) {
            System.out.println("\t" + (i+1) + ") " + shops.get(i));
        }
        System.out.println();
        System.out.println("\t" + (shops.size() + 1) + ") Back");
        System.out.println();
    }

    /**
     * method that lists the products of a shop
     * @param shopInfo = ArrayList that contains the name, since and description of the shop
     * @param catalogue = ArrayList that contains the products, brands and prices of the products from shop
     */
    public void listCatalogue(ArrayList<String> shopInfo, ArrayList<String> catalogue) {
        System.out.println();
        System.out.println(shopInfo.get(0) + " – Since " + shopInfo.get(1));
        System.out.println(shopInfo.get(2));
        System.out.println();
        int i = 0;
        while (i < catalogue.size()) {
            String productName = catalogue.get(i);
            String brandName = catalogue.get(i + 1);
            String price = catalogue.get(i + 2);
            System.out.println("\t" + (i / 3 + 1) + ") \"" + productName + "\" by \"" + brandName + "\"");
            System.out.println("\t\tPrice: " + price);
            System.out.println();
            i += 3;
        }
        System.out.println();
        System.out.println("\t" + (i / 3 + 1) + ") Back");
        System.out.println();
    }

    /**
     * method that displays the submenu of the products from ListShops option
     */
    public void catalogueMenu() {
        System.out.println();
        System.out.println("\t1) Read Reviews");
        System.out.println("\t2) Review Product");
        System.out.println("\t3) Add to Cart");
        System.out.println();
    }

    /**
     * method that displays a message when a product is added to the cart
     * @param prodName = String that contains the name of the product
     * @param prodBrand = String that contains the brand of the product
     */
    public void addCart(String prodName, String prodBrand) {
        System.out.println();
        System.out.println("1x \"" + prodName + "\" by \"" + prodBrand + "\" has been added to your cart.");
    }

    /**
     * method that displays all the products in the cart
     * @param cart = ArrayList that contains the products, brands and prices of the products from cart
     */
    public void manageCart(ArrayList<String> cart) {
        System.out.println();
        System.out.println("Your cart contains the following items:");
        System.out.println();
        int i = 0;
        float totalPrice = 0;
        while (i < cart.size()) {
            String productName = cart.get(i);
            String brandName = cart.get(i + 1);
            String price = cart.get(i + 2);
            totalPrice += Float.parseFloat(price);
            System.out.println("\t- \"" + productName + "\" by \"" + brandName + "\"");
            System.out.println("\t\tPrice: " + price);
            System.out.println();
            i += 3;
        }
        System.out.println("Total: " + totalPrice);
        System.out.println();
        System.out.println("\t1) Checkout");
        System.out.println("\t2) Clear cart");
        System.out.println();
        System.out.println("\t3) Back");
        System.out.println();
    }

    /**
     * method tha displays the earnings of every shop
     * @param shopInfo = ArrayList that contains the name and total earning of every shop
     * @param earnings = ArrayList that contains the earnings of every shop
     */
    public void checkoutCart(ArrayList<String> shopInfo, ArrayList<Float> earnings) {
        System.out.println();
        int i = 0, j = 0;
        while (j < earnings.size()) {
            System.out.println("\"" + shopInfo.get(i) + "\" has earned " + earnings.get(j) + ", for an historic total of " + shopInfo.get(i + 1) + ".");
            i += 2;
            j++;
        }
        System.out.println();
    }
}
