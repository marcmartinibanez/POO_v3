package Persistence;
import Business.Classes.Product;
import Persistence.ApiHelper.ApiHelper;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class that connects to the Interface with the methods of rather the API or the Json.
 * This class is the one that communicates with the API
 */
public class ProductAPI implements ProductIF{
    private final ApiHelper api;
    private static final String url = "https://balandrau.salle.url.edu/dpoo/S1-Project_99/products";
    private final Gson gson;

    /**
     * It tries to connect to the API, if it didn't work, it throws an exception
     * @throws IOException = Exception that is thrown if the connection to the API didn't work
     */
    public ProductAPI() throws IOException {
        api = new ApiHelper();
        gson = new Gson();
    }
    /**
     * Function that indicates if the connection worked
     * @return boolean that indicates if the API connected well.
     */
    @Override
    public boolean openJSon() {
        return true;
    }

    /**
     * Function that reads the products from the API
     * @return ArrayList with all the products stored.
     */
    @Override
    public ArrayList<Product> readAllProds() {
        try{
            return new ArrayList<>(Arrays.asList(gson.fromJson(api.getFromUrl(url), Product[].class)));
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    /**
     * method that creates a product in the API
     * @param product = Product that is meant to be created
     */
    @Override
    public void createProduct(Product product) {
        try {
            api.postToUrl(url, gson.toJson(product));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * method that removes a Product from the API
     * @param i = Position of the Product to be deleted
     */
    public void removeProd(int i) {
        String url = ProductAPI.url + "/" + i;
        try {
            api.deleteFromUrl(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * method that updates a Product in API
     * @param updateProduct = Product to be updated
     */
    public void updateProduct(Product updateProduct) {
        try {
            ArrayList<Product> existingProds = readAllProds();
            if (existingProds == null || existingProds.isEmpty()) {
                return;
            }
            for (int i = 0; i < existingProds.size(); i++) {
                Product product = existingProds.get(i);
                if (product.getName().equals(updateProduct.getName())) {
                    existingProds.set(i, updateProduct);
                    break;
                }
            }
            api.postToUrl(url, gson.toJson(existingProds));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
