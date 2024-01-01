package Persistence;
import Business.Classes.Shop;

import java.util.ArrayList;

/**
 * The Interface ShopIF contains the functions that work with the files or with the API
 * @version 18.0.2 5 January 2024
 * @author Marc Martín
 * @author Joaquim Angas
 */
public interface ShopIF{
    /**
     * function that reads all the Shops in the file and adds them in a Shops ArrayList
     * @return Shops ArrayList that contains all the shops in the file
     */
    ArrayList<Shop> readAllShops();

    /**
     * method that creates a shop
     * @param shop Shop to add
     */
    void createShop(Shop shop);

    /**
     * method that updates a Shop in the file
     * @param updateShop = Shop to be updated in the files
     */
    void updateShop(Shop updateShop);

    /**
     * method that updates all the Shops in the file
     * @param updateShops = ArrayList of shops to be updated in the files
     */
    void  updateShops(ArrayList<Shop> updateShops);
}
