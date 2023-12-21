package Persistence;

import Business.Classes.Shop;
import Persistence.ApiHelper.ApiHelper;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class that connects to the Interface with the methods of rather the API or the Json.
 * This class is the one that communicates with the API
 */
public class ShopAPI implements ShopIF {
    private final ApiHelper api;
    private static final String url = "https://balandrau.salle.url.edu/dpoo/S1-Project_99/shops";
    private final Gson gson;

    /**
     * It tries to connect to the API, if it didn't work, it throws an exception
     * @throws IOException = Exception that is thrown if the connection to the API didn't work
     */
    public ShopAPI() throws IOException {
        api = new ApiHelper();
        gson = new Gson();
        //removeAllShop();
    }

    /**
     * method that writes the shops to the API
     * @param shops = Arraylist with all the shops that are meant to be written
     */
    private void writeAllShops(ArrayList<Shop> shops) {
        try{
            ArrayList<Shop> allShops = readAllShops();
            for (int j = 0; j < allShops.size(); j++) {
                removeShop(0);
            }
            for (Shop shop : shops) {
                api.postToUrl(url, gson.toJson(shop));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Function that reads the shops from the API
     * @return ArrayList with all the shops stored.
     */
    @Override
    public ArrayList<Shop> readAllShops() {
        try{
            return new ArrayList<>(Arrays.asList(gson.fromJson(api.getFromUrl(url), (Type) Shop[].class)));
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    /**
     * method that creates a shop in the API
     * @param shop = Shop that is meant to be created
     */
    @Override
    public void createShop(Shop shop) {
        try {
            api.postToUrl(url, gson.toJson(shop));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * method that updates a Shop in the API
     * @param updateShop = Shop to be updated
     */
    @Override
    public void updateShop(Shop updateShop) {
        ArrayList<Shop> existingShops = readAllShops();
        if (existingShops == null || existingShops.isEmpty()) {
            return;
        }
        for (int i = 0; i < existingShops.size(); i++) {
            Shop shop = existingShops.get(i);
            if (shop.getName().equals(updateShop.getName())) {
                existingShops.set(i, updateShop);
                break;
            }
        }
        writeAllShops(existingShops);
    }

    /**
     * method that updates all the Shops in the API
     * @param updateShops = ArrayList of shops to be updated in the API
     */
    public void updateShops(ArrayList<Shop> updateShops) {
        try {
            api.postToUrl(url, gson.toJson(updateShops));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * method that removes a Shop from the API
     * @param i = Position of the Shop to be deleted
     */
    public void removeShop(int i) {
        String url1 = url + "/" + i;
        try {
            api.deleteFromUrl(url1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //FUNCION TEMPORAL PARA BORRAR TODOS LOS SHOPS
    public void removeAllShop() throws IOException {
        for (int i = 0; i < 10; i++) {
            String url1 = url + "/" + i;
            api.deleteFromUrl(url1);
        }
    }
}
