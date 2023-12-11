package Business.Classes;

import java.util.ArrayList;

/**
 * Class Cart contains a ShopProduct ArrayList
 *
 * @version 18.0.2 27 November 2023
 * @author Marc Martín
 * @author Joaquim Angas
 */
public class Cart {
    ArrayList<Business.Classes.ShopProduct> products;

    /**
     * Constructor of the class Cart
     */
    public Cart() {
        this.products = new ArrayList<>();
    }

    /**
     * function that adds a product to the cart
     * @param product = ShopProduct that is going to be added to the cart
     */
    public void addProduct(Business.Classes.ShopProduct product) {
        this.products.add(product);
    }

    /**
     * function that removes a product from the cart
     * @return ArrayList of ShopProduct that contains the products in the cart
     */
    public ArrayList<Business.Classes.ShopProduct> getProducts() {
        return products;
    }
}
