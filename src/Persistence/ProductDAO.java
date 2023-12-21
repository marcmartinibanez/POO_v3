package Persistence;

import Business.Classes.Product;
import Business.Classes.ProductsTypes.General;
import Business.Classes.ProductsTypes.Reduced;
import Business.Classes.ProductsTypes.SuperReduced;
import com.google.gson.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class productDAO works and manages everything related to the Json
 * file in which we write and delete the products available
 *
 * @version 18.0.2 27 November 2023
 * @author Marc Martín
 * @author Joaquim Angas
 */
    public class ProductDAO implements ProductIF{
    private final File file;
    private final Gson gson;

    /**
     * Constructor of the class ProductDAO, it initializes the file "products.json"
     * and it also creates the gson
     */
    public ProductDAO() {
        file = new File("files/products.json");
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * function that opens the file "products.json" and it indicates if it has been correctly done
     * @return Boolean that indicates if the file has been opened correctly
     */
    public boolean openJSon()  {
        try {
            JsonArray JsonProducts = JsonParser.parseReader(new FileReader("files/products.json")).getAsJsonArray();
        } catch (FileNotFoundException e) {
            return true;
        }
        return  false;
    }

    /**
     * method that writes in the file "products.json" a Product Arraylist
     * @param product = Product Arraylist to be added to the file "products.json"
     */
    private void writeAllProds(ArrayList<Product> product) {
        try {
            FileWriter prodWriter = new FileWriter("files/products.json");
            gson.toJson(product, prodWriter);
            prodWriter.close();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * function that reads all the Products written in the file "products.json" and stores them in a Product ArrayList
     * @return Products ArrayList that contains all the Products in the file "products.json"
     */
    public ArrayList<Product> readAllProds() {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        ArrayList<Product> products = new ArrayList<>();

        try {
            FileReader reader = new FileReader("files/products.json");
            JsonElement json = parser.parse(reader);
            JsonArray jsonArray = json.getAsJsonArray();

            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                String category = jsonObject.get("category").getAsString();

                switch (category) {
                    case "General":
                        products.add(gson.fromJson(jsonObject, General.class));
                        break;
                    case "Reduced Taxes":
                        products.add(gson.fromJson(jsonObject, Reduced.class));
                        break;
                    case "Superreduced Taxes":
                        products.add(gson.fromJson(jsonObject, SuperReduced.class));
                        break;
                    default:
                        System.out.println("Invalid product type.");
                }
            }

        } catch (IOException e) {
            System.out.println("An error occurred while reading products from file: " + e.getMessage());
        }
        return products;
    }

    /**
     * method that adds a Product in the file "products.json"
     * @param product = Product to be written in the file "products.json"
     */
    public void createProduct(Product product) {
        ArrayList<Product> products = readAllProds();
        products.add(product);
        writeAllProds(products);
    }

    /**
     * method that removes a Product from the file "products.json"
     * @param i = Position of the Product to be deleted
     */
    public void removeProd(int i) {
        ArrayList<Product> removeProduct = readAllProds();
        if (i >= 0 && i < removeProduct.size()) {
            removeProduct.remove(i);
            writeAllProds(removeProduct);
        }
    }

    /**
     * method that updates a Product in the file "products.json"
     * @param updateProduct = Product to be updated in the file "products.json"
     */
    public void updateProduct(Product updateProduct) {
        ArrayList<Product> existingProducts = readAllProds();
        if (existingProducts == null || existingProducts.isEmpty()) {
            return;
        }
        for (int i = 0; i < existingProducts.size(); i++){
            Product product = existingProducts.get(i);
            if (product.getName().equals(updateProduct.getName())) {
                existingProducts.set(i, updateProduct);
                writeAllProds(existingProducts);
                return;
            }
        }
    }
}
