package Presentation;

import Business.ProductManager;
import Business.ShopManager;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Class Controller manages all the game logic, and it contains the
 * program structure.
 *
 * @version 18.0.2 27 November 2023
 * @author Marc Martín
 * @author Joaquim Angas
 */
public class Controller {
    private final UI ui;
    private final ProductManager productManager;
    private final ShopManager shopManager;

    /**
     * Constructor of the class Controller
     * @param ui = Attribute of the type UI, it contains the scanner and all the messages that will be shown to the user
     * @param productManager = Attribute of the type ProductManager, it contains all the functions related to creating/deleting a Product
     * @param shopManager = Attribute of the type ShopManager, it contains all the functions related to creating/deleting a Shop
     */
    public Controller(UI ui, ProductManager productManager, ShopManager shopManager) {
        this.ui = ui;
        this.productManager = productManager;
        this.shopManager = shopManager;
    }

    /**
     * Function that loads the file "products.json" and returns a boolean
     * @return boolean that indicates if the file has been loaded correctly
     */
    private boolean loadFile(){
        try{
            return productManager.loadProducts();
        }catch (FileNotFoundException e){
            throw new  RuntimeException(e);
        }
    }

    /**
     * Function that loads the file "products.json" and returns a boolean
     * @return boolean that indicates if the file has been loaded correctly
     */
    private boolean loadStart(boolean api){
        return ui.loadInitial(loadFile(), api);
    }

    /**
     * Main method that contains all the logic of the program, in this function we control, and
     * we call all the functions and methods of the code.
     */
    public void run(boolean api){
        if (loadStart(api)) {
            return;
        }
        int option;
        do {
            ui.showOptions();
            option = ui.askForInteger("Choose a Digital Shopping Experience: ");
            if (option <= 0 || option > 6){
                ui.showMessage("\nInvalid option\n");
            }
            executeOption(option);
        }while (option != 6);
    }

    /**
     * method that executes the option chosen by the user
     * @param option = Integer that contains the option chosen by the user
     */
    private void executeOption(int option) {
        switch (option){
            case 1:
                manageProducts();
                break;
            case 2:
                manageShops();
                break;
            case 3:
                searchProducts();
                break;
            case 4:
                listShops();
                break;
            case 5:
                manageCart();
                break;
            case 6:
                ui.showMessage("\nWe hope to see you again!");
        }
    }

    /**
     * method that manages the products, it shows the menu and it calls the functions related to the products
     */
    private void manageProducts(){
        int option;
        do {
            ui.menuProductManager();
            option = ui.askForInteger("Choose an option: ");
            if (option <= 0 || option > 3){
                ui.showMessage("\nInvalid option\n");
            }
        } while (option <= 0 || option > 3);

        switch (option){
            case 1:
                createProduct();
                break;
            case 2:
                removeProduct();
                break;
            case 3:
                // BACK
                break;
        }
    }

    /**
     * method that creates a product, it asks the user for the name, brand, mrp and category of the product
     */
    private void createProduct() {
        String name = ui.askForString("Please enter the product’s name: ");
        if (!productManager.unicProductName(name)){
            ui.showMessage("A product with the same name already exists!\n");
            return;
        }
        String brand = ui.askForString("Please enter the product’s brand: ");
        brand = productManager.transformFormat(brand);
        float mrp = ui.askForFloat("Please enter the product’s maximum retail price: ");
        if (mrp < 0){
            ui.showMessage("The shop will not pay you to buy it. Enter a positive number!\n");
            return;
        }
        ui.menuCreateProductCategory();
        char option = ui.askForChar("Please pick the product’s category: ");
        String category = transformOptionProduct(option);
        if (category == null) {
            return;
        }
        productManager.writeProductInDao(productManager.createProduct(name, brand, mrp, category));
        ui.productCreated(name, brand);
    }

    /**
     * method that transforms the option chosen by the user into a specific String
     * @param option = Char that contains the option chosen by the user
     * @return String that contains the category of the product
     */
    private String transformOptionProduct(char option) {
        option = Character.toUpperCase(option);
        return switch (option) {
            case 'A' -> "General";
            case 'B' -> "Reduced Taxes";
            case 'C' -> "Superreduced Taxes";
            default -> {
                ui.showMessage("Invalid option");
                yield null;
            }
        };
    }

    /**
     * method that removes a product, it shows the list of products and it asks the user for the product to be removed
     */
    private void removeProduct() {
        String confirm;
        do {
            ArrayList<String> names = productManager.getListProductNames();
            ui.listProducts(names);
            int position = ui.askForInteger("Which one would you like to remove? ");
            if (position <= 0 || position > ((names.size() / 2) + 1)) {
                ui.showMessage("Invalid option! You must enter a number between 1 and " + ((names.size() / 2) + 1) + "\n");
                return;
            }
            else if (position == ((names.size() / 2) + 1)){
                return;
            }
            ArrayList<String> selectedProduct = productManager.getSelectedProduct(position);
            ui.confirmDeleteProduct(selectedProduct);
            confirm = ui.askForString(" ");
            if (confirm.equalsIgnoreCase("yes")) {
                shopManager.removeProductFromAllShops(selectedProduct.getFirst());
                productManager.deleteProduct(position);
                ui.productDeleted(selectedProduct);
            }
            else if (!confirm.equalsIgnoreCase("no")){
                ui.showMessage("\nInvalid option! You must enter yes or no\n");
            }
        }while (confirm.equalsIgnoreCase("no"));
    }

    /**
     * method that manages the shops, it shows the menu and it calls the functions related to the shops
     */
    private void manageShops() {
        int option;
        do {
            ui.menuShopManager();
            option = ui.askForInteger("Choose an option: ");
            if (option <= 0 || option > 4) {
                ui.showMessage("\nInvalid option\n");
            }
        }while(option <= 0 || option > 4);

        switch (option){
            case 1:
                createShop();
                break;
            case 2:
                expandShopCatalogue();
                break;
            case 3:
                reduceShopCatalogue();
                break;
            case 4:
                // Back
                break;
        }
    }

    /**
     * method that creates a shop, it asks the user for the name, description, founding year and business model of the shop
     */
    private void createShop() {
        String shopName = ui.askForString("Please enter the shop’s name: ");
        if (!shopManager.unicShopName(shopName)){
            ui.showMessage("A shop with the same name already exists!\n");
            return;
        }
        String shopDescription = ui.askForString("Please enter the shop’s description: ");
        int foundingYear = ui.askForInteger("Please enter the shop’s founding year: ");
        if (foundingYear <= 0){
            ui.showMessage("In this year Jesus was not born yet. Enter a positive number!\n");
            return;
        }
        ui.menuCreateShop();
        char option = ui.askForChar("Please pick the shop’s business model: ");
        String businessModel = transformOptionShop(option);
        if (businessModel == null) {
            return;
        }
        float loyaltyThreshold = 0;
        if (businessModel.equals("Loyalty")) {
            loyaltyThreshold = ui.askForFloat("Please enter the shop’s loyalty threshold: ");
            if (loyaltyThreshold < 0){
                ui.showMessage("That's not good for the shop. Enter a positive number!\n");
                return;
            }
        }
        String sponsorBrand = "";
        if (businessModel.equals("Sponsored")) {
            sponsorBrand = ui.askForString("Please enter the shop’s sponsoring brand: ");
            sponsorBrand = productManager.transformFormat(sponsorBrand);
        }
        shopManager.writeShopInDao(shopManager.createShop(shopName, shopDescription, foundingYear, 0, businessModel, loyaltyThreshold, sponsorBrand));
        ui.shopCreated(shopName);
    }

    /**
     * method that transforms the option chosen by the user into a specific String
     * @param option = Char that contains the option chosen by the user
     * @return String that contains the business model of the shop
     */
    private String transformOptionShop(char option) {
        option = Character.toUpperCase(option);
        return switch (option) {
            case 'A' -> "Maximum Benefits";
            case 'B' -> "Loyalty";
            case 'C' -> "Sponsored";
            default -> {
                ui.showMessage("Invalid option");
                yield null;
            }
        };
    }

    /**
     * method that expands the catalogue of a shop, it asks the user for the shop and the product to be added
     */
    private void expandShopCatalogue() {
        String shopName = ui.askForString("Please enter the shop’s name: ");
        shopName = productManager.transformFormat(shopName);
        if (!shopManager.checkShopExistence(shopName)){
            ui.showMessage("The shop is not part of the elCofre family!\n");
            return;
        }
        String prodName = ui.askForString("Please enter the product’s name: ");
        prodName = productManager.transformFormat(prodName);
        if (!productManager.checkProductExistence(prodName)){
            ui.showMessage("The product does not exist!\n");
            return;
        }
        if (shopManager.checkProdExistence(prodName, shopName)){
            ui.showMessage("The product is already in the shop’s catalogue!\n");
            return;
        }
        float price = ui.askForFloat("Please enter the product’s price at this shop: ");
        if (price > productManager.getProduct(prodName).getMrp()){
            ui.showMessage("The price cannot be higher than the product’s MRP!\n");
            return;
        }
        shopManager.updateShopDao(shopManager.addProductToShop(productManager.getProduct(prodName), shopName, price));
        ui.productAddedToShop(prodName, productManager.getProduct(prodName).getBrand(), shopName);
    }

    /**
     * method that reduces the catalogue of a shop, it asks the user for the shop and the product to be removed
     */
    private void reduceShopCatalogue() {
        String shopName = ui.askForString("Please enter the shop’s name: ");
        if (!shopManager.checkShopExistence(shopName)){
            ui.showMessage("The shop is not part of the elCofre family!\n");
            return;
        }
        ArrayList<String> productNamesBrands = shopManager.getShopProductNames(shopName);
        if (productNamesBrands.isEmpty()) {
            ui.showMessage("No products found in this shop.\n");
            return;
        }

        ui.listProducts(productNamesBrands);
        int productIndex = ui.askForInteger("Which one would you like to remove? ");
        if (productIndex == productNamesBrands.size() + 1) {
            return;
        }
        if (productIndex < 1 || productIndex > productNamesBrands.size()) {
            ui.showMessage("Invalid option.\n");
            return;
        }
        String prodName = productNamesBrands.get(((productIndex - 1) * 2));
        String prodBrand = productNamesBrands.get(((productIndex - 1) * 2) + 1);

        if (shopManager.removeProductFromShop(shopName, prodName)){
            ui.productRemovedFromShop(prodName, prodBrand, shopName);
        }
        else{
            ui.showMessage("Error removing product from shop.\n");
        }
    }

    /**
     * method that searches a product, it asks the user for the query and it shows the products found
     */
    private void searchProducts() {
        String query = ui.askForString("\nEnter your query: ");
        ArrayList<String> productNames = productManager.productsFound(query);
        ArrayList<String> shopNames = shopManager.shopsWithProduct(productNames);
        ui.productsFound(productNames, shopNames);
        if (productNames.isEmpty()) {
            return;
        }
        int position = ui.askForInteger("Which one would you like to review? ");
        if (position <= 0 || position > ((productNames.size() / 2) + 1)) {
            ui.showMessage("Invalid option! You must enter a number between 1 and " + ((productNames.size() / 2) + 1) + "\n");
            return;
        }
        else if (position == ((productNames.size() / 2) + 1)){
            return;
        }
        String productName = productNames.get(((position - 1) * 2));
        String productBrand = productNames.get(((position - 1) * 2) + 1);
        ui.reviewMenu();
        int option = ui.askForInteger("Choose an option: ");
        if (option <= 0 || option > 2) {
            ui.showMessage("Invalid option! You must enter a number between 1 and 2\n");
            return;
        }
        switch (option){
            case 1:
                readReviews(productName, productBrand);
                break;
            case 2:
                reviewProduct(productName, productBrand);
                break;
        }
    }

    /**
     * method that shows the reviews of a product
     * @param name = String that contains the name of the product
     * @param brand = String that contains the brand of the product
     */
    private void readReviews(String name, String brand) {
        ArrayList<String> reviews = productManager.getProductReviews(name, brand);
        if (reviews.isEmpty()) {
            ui.showMessage("No reviews found for this product.\n");
            return;
        }
        ui.listReviews(reviews, name, brand);
    }

    /**
     * method that reviews a product, it asks the user for stars and comment of the product
     * @param name = String that contains the name of the product
     * @param brand = String that contains the brand of the product
     */
    private void reviewProduct(String name, String brand) {
        String stars = ui.askForString("Please rate the product (1-5 stars): ");
        if (stars.length() > 5 || stars.isEmpty()){
            ui.showMessage("Invalid number of stars!\n");
            return;
        }
        String comment = ui.askForString("Please add a comment to your review: ");
        productManager.updateProductDao(productManager.addReviewToProduct(name, brand, stars.length(), comment));
        ui.reviewDone(name, brand);
    }

    /**
     * method that lists the shops, it shows the shops and it asks the user for function to be executed for a specific product
     */
    private void listShops() {
        int option;
        ArrayList<String> shopNames = shopManager.getShopsNames();
        ui.listShops(shopNames);
        int positionShop = ui.askForInteger("Which catalogue do you want to see? ");
        if (positionShop <= 0 || positionShop > ((shopNames.size() + 1))) {
            ui.showMessage("Invalid option! You must enter a number between 1 and " + (shopNames.size() + 1) + "\n");
            return;
        }
        else if (positionShop == (shopNames.size() + 1)){
            return;
        }
        do {
            ArrayList<String> shopInfo = shopManager.getSelectedShopInfo(positionShop);
            ArrayList<String> catalogue = shopManager.getSelectedCatalogue(positionShop);
            ui.listCatalogue(shopInfo, catalogue);
            int positionProduct = ui.askForInteger("Which one are you interested in? ");
            if (positionProduct <= 0 || positionProduct > ((catalogue.size() / 3) + 1)) {
                ui.showMessage("Invalid option! You must enter a number between 1 and " + ((catalogue.size() / 3) + 1) + "\n");
                return;
            }
            else if (positionProduct == ((catalogue.size() / 3) + 1)){
                return;
            }
            String productName = catalogue.get(((positionProduct - 1) * 3));
            String productBrand = catalogue.get(((positionProduct - 1) * 3) + 1);
            String shopName = shopInfo.getFirst();
            ui.catalogueMenu();
            option = ui.askForInteger("Choose an option: ");
            if (option <= 0 || option > 3) {
                ui.showMessage("Invalid option! You must enter a number between 1 and 3\n");
                return;
            }
            switch (option){
                case 1:
                    readReviews(productName, productBrand);
                    break;
                case 2:
                    reviewProduct(productName, productBrand);
                    break;
                case 3:
                    addProductToCart(productName, productBrand, shopName);
                    break;
            }
        }while (true);
    }

    /**
     * method that adds a product to the cart, it asks the user for the product and the shop
     * @param productName = String that contains the name of the product
     * @param productBrand = String that contains the brand of the product
     * @param shopName = String that contains the name of the shop
     */
    private void addProductToCart(String productName, String productBrand, String shopName) {
        shopManager.addProductToCart(productName, productBrand, shopName);
        ui.addCart(productName, productBrand);
    }

    /**
     * method that manages the cart, it shows the menu and it calls the functions related to the cart
     */
    private void manageCart() {
        ArrayList<String> cart = shopManager.getCart();
        if (cart.isEmpty()) {
            ui.showMessage("Your cart is empty!\n");
            return;
        }
        ui.manageCart(cart);
        int option = ui.askForInteger("Choose an option: ");
        if (option <= 0 || option > 3) {
            ui.showMessage("Invalid option! You must enter a number between 1 and 3\n");
            return;
        }

        switch (option){
            case 1:
                checkoutCart();
                break;
            case 2:
                clearCart();
                break;
            case 3:
                // Back
                break;
        }
    }

    /**
     * method that checks out the cart, it asks the user for confirmation
     */
    private void checkoutCart() {
        String confirm = ui.askForString("\nAre you sure you want to checkout? ");
        if (confirm.equalsIgnoreCase("yes")) {
            ArrayList<Float> earnings = shopManager.checkoutCart();
            ArrayList<String> shopInfo = shopManager.getShopEarnings();
            ui.checkoutCart(shopInfo, earnings);
            shopManager.clearCart();
            ui.showMessage("Your cart has been cleared!\n");
        }
        else if (!confirm.equalsIgnoreCase("no")){
            ui.showMessage("\nInvalid option! You must enter 'yes' or 'no'\n");
        }
    }

    /**
     * method that clears the cart, it asks the user for confirmation
     */
    private void clearCart() {
        String confirm = ui.askForString("\nAre you sure you want to clear your cart? ");
        if (confirm.equalsIgnoreCase("yes")) {
            shopManager.clearCart();
            ui.showMessage("Your cart has been cleared!\n");
        }
        else if (!confirm.equalsIgnoreCase("no")){
            ui.showMessage("\nInvalid option! You must enter 'yes' or 'no'\n");
        }
    }
}
