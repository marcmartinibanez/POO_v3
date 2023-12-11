package Persistence;
import Business.Classes.Product;

import java.util.ArrayList;

/**
 * The Interface ProductIF contains the functions that work with the files or with the API
 */
public interface ProductIF{

    /**
     * function that indicates if the json has opened correctly
     * @return boolean that indicates if the json has opened correctly
     */
    boolean openJSon();
    /**
     * function that reads all the Products in the file and adds them in a Product ArrayList
     * @return Product ArrayList that contains all the products in the file
     */
    ArrayList<Product> readAllProds();

    /**
     * method that creates a product
     * @param product Product to add
     */
    void createProduct(Product product);

    /**
     * method that removes a product from the file
     * @param i = Position of the product to delete
     */
    void removeProd(int i);

    /**
     * method that updates a Product in the file
     * @param updateProduct = Product to be updated in the files
     */
    void updateProduct(Product updateProduct);
}
