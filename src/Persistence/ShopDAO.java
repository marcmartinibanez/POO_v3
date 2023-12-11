package Persistence;

import Business.Classes.Shop;
import com.google.gson.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class shopDAO works and manages everything related to the Json
 * file in which we write and delete the shops available
 *
 * @version 18.0.2 27 November 2023
 * @author Marc Martín
 * @author Joaquim Angas
 */
public class ShopDAO {
    private final File file;
    private final Gson gson;

    /**
     * Constructor of the class ShopDAO, it initializes the file "shops.json"
     * and it also creates the gson
     */
    public ShopDAO() {
        file = new File("files/shops.json");
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * method that writes in the file "shops.json" a Shop Arraylist
     * @param shop = Shop Arraylist to be added to the file "shops.json"
     */
    private void writeAllShops(ArrayList<Shop> shop) {
        try {
            FileWriter shopWriter = new FileWriter("files/shops.json");
            gson.toJson(shop, shopWriter);
            shopWriter.close();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * function that reads all the Shops written in the file "shops.json" and stores them in a Shop ArrayList
     * @return Shops ArrayList that contains all the Shops in the file "shops.json"
     */
    public ArrayList<Shop> readAllShops() {
        try {
            FileReader shopReader = new FileReader("files/shops.json");
            return new ArrayList<>(Arrays.asList(gson.fromJson(shopReader, Shop[].class)));
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        }
    }

    /**
     * method that adds a Shop in the file "shops.json"
     * @param shop = Shop to be written in the file "shops.json"
     */
    public void createShop(Shop shop) {
        ArrayList<Shop> existingShops = readAllShops();
        existingShops.add(shop);
        writeAllShops(existingShops);
    }

    /**
     * method that updates a Shop in the file "shops.json"
     * @param updateShop = Shop to be updated in the file "shops.json"
     */
    public void updateShop(Shop updateShop) {
        ArrayList<Shop> existingShops = readAllShops();
        if (existingShops == null || existingShops.isEmpty()) {
            return;
        }
        for (int i = 0; i < existingShops.size(); i++){
            Shop shop = existingShops.get(i);
            if (shop.getName().equals(updateShop.getName())) {
                existingShops.set(i, updateShop);
                writeAllShops(existingShops);
                return;
            }
        }
    }

    /**
     * method that updates all the Shops in the file "shops.json"
     * @param updateShops = ArrayList of shops to be updated in the file "shops.json"
     */
    public void updateShops(ArrayList<Shop> updateShops) {
        writeAllShops(updateShops);
    }
}
