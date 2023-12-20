package Business;

import Business.Classes.Cart;
import Business.Classes.Product;
import Business.Classes.Shop;
import Business.Classes.ShopProduct;
import Business.Classes.ShopsTypes.Loyalty;
import Business.Classes.ShopsTypes.Sponsored;
import Persistence.ShopIF;

import java.util.ArrayList;

/**
 * Class ShopManager contains ShopDAO, and it contains all the functions
 * in charge of working and controlling everything related to the shops.
 *
 * @version 18.0.2 27 November 2023
 * @author Marc Martín
 * @author Joaquim Angas
 */
public class ShopManager {
    private final ShopIF shopIF;

    /**
     * Constructor of the class ShopManager, it contains a ShopDAO, which will write and provide
     * the shops from the json file "shops.json"
     * @param shopIF = ShopIF that will write and provide the shops from the json file "shops.json" or from the API
     */
    public ShopManager(ShopIF shopIF) {
        this.shopIF = shopIF;
    }

    private final Cart cart = new Cart();

    /**
     * method that writes a shop in the file "shops.json"
     * @param shop = Shop to be written in the file "shops.json"
     */
    public void writeShopInDao (Shop shop) {
        shopIF.createShop(shop);
    }

    /**
     * method tha updates a shop in the file "shops.json"
     * @param shop = Shop to be updated in the file "shops.json"
     */
    public void updateShopDao(Shop shop) {
        shopIF.updateShop(shop);
    }

    /**
     * function that creates a shop
     * @param name = String that contains the name of the shop
     * @param description = String that contains the description of the shop
     * @param since = Integer that contains the year since the shop is open
     * @param earnings = Float that contains the earnings of the shop
     * @param businessModel = String that contains the business model of the shop
     * @return Shop that contains all the information of the shop
     */
    public Shop createShop(String name, String description, int since, float earnings, String businessModel, float loyaltyThreshold, String sponsorBrand) {
        ArrayList<ShopProduct> products = new ArrayList<>();
        if (businessModel.equalsIgnoreCase("Loyalty")) {
            return new Loyalty(name, description, since, earnings, businessModel, products, loyaltyThreshold);
        }
        else if (businessModel.equalsIgnoreCase("Sponsored")) {
            return new Sponsored(name, description, since, earnings, businessModel, products, sponsorBrand);
        }
        else if (businessModel.equalsIgnoreCase("Maximum Benefits")) {
            return new Shop(name, description, since, earnings, businessModel, products);
        }
        return null;
    }

    /**
     * function that returns all the shops names in the file "shops.json"
     * @return String Arraylist that contains all the shops names in the file "shops.json"
     */
    public ArrayList<String> getShopsNames() {
        ArrayList<String> shopNames = new ArrayList<>();
        ArrayList<Shop> shops = shopIF.readAllShops();
        for (Shop shop : shops) {
            shopNames.add(shop.getName());
        }
        return shopNames;
    }

    /**
     * function that return if the shop name is unique
     * @param shopName = String that contains the name of the shop
     * @return boolean that indicates if the shop name is unique
     */
    public boolean unicShopName(String shopName) {
        ArrayList<Shop> shops = shopIF.readAllShops();
        shopName = shopName.toLowerCase();
        for (Shop shop : shops) {
            if (shop.getName().toLowerCase().equals(shopName)) {
                return false;
            }
        }
        return true;
    }

    /**
     * function that returns if the shop exists
     * @param shopName = String that contains the name of the shop
     * @return boolean that indicates if the shop exists
     */
    public boolean checkShopExistence(String shopName) {
        ArrayList<Shop> shops = shopIF.readAllShops();
        if (shops == null || shops.isEmpty()) {
            return false;
        }
        shopName = shopName.toLowerCase();
        for (Shop shop : shops) {
            if (shop.getName().toLowerCase().equals(shopName)) {
                return true;
            }
        }
        return  false;
    }

    /**
     * function that returns if the product exists in the shop
     * @param prodName = String that contains the name of the product
     * @param shopName = String that contains the name of the shop
     * @return boolean that indicates if the product exists in the shop
     */
    public boolean checkProdExistence(String prodName, String shopName) {
        ArrayList<Shop> shops = shopIF.readAllShops();
        if (shops == null || shops.isEmpty()) {
            return false;
        }
        shopName = shopName.toLowerCase();
        prodName = prodName.toLowerCase();
        for (Shop shop : shops) {
            if (shop.getName().toLowerCase().equals(shopName)) {
                for (ShopProduct shopProduct : shop.getCatalogue()) {
                    Product product = shopProduct.getProduct();
                    if (product != null && product.getName().toLowerCase().equals(prodName)) {
                        return true;
                    }
                }
            }
        }
        return  false;
    }

    /**
     * function that returns the Shop that contains the name passed by parameter
     * @param product = Product to be added to the shop
     * @param shopName = String that contains the name of the shop
     * @param price = Float that contains the price of the product in the shop
     * @return Shop that contains the product added
     */
    public Shop addProductToShop(Product product, String shopName, float price) {
        ArrayList<Shop> shops = shopIF.readAllShops();
        for (Shop shop : shops) {
            if (shop.getName().equalsIgnoreCase(shopName)) {
                ShopProduct shopProduct = new ShopProduct(product, shopName, price);
                shop.addProductToCatalogue(shopProduct);
                return shop;
            }
        }
        return null;
    }

    /**
     * function that return an Arraylist of strings that contains the names and brands of the products of the shop
     * @param shopName = String that contains the name of the shop
     * @return ArrayList of Strings that contains the names and brands of the products of the shop
     */
    public ArrayList<String> getShopProductNames(String shopName) {
        ArrayList<String> productInfo = new ArrayList<>();
        ArrayList<Shop> shops = shopIF.readAllShops();

        for (Shop shop : shops) {
            if (shop.getName().equalsIgnoreCase(shopName)) {
                for (ShopProduct shopProduct : shop.getCatalogue()) {
                    productInfo.add(shopProduct.getProduct().getName());
                    productInfo.add(shopProduct.getProduct().getBrand());
                }
                break;
            }
        }
        return productInfo;
    }

    /**
     * function that return if the product has been removed from the shop
     * @param shopName = String that contains the name of the shop
     * @param productName = String that contains the name of the product
     * @return boolean that indicates if the product has been removed from the shop
     */
    public boolean removeProductFromShop(String shopName, String productName) {
        ArrayList<Shop> shops = shopIF.readAllShops();

        for (Shop shop : shops) {
            if (shop.getName().equalsIgnoreCase(shopName)) {
                for (ShopProduct shopProduct : shop.getCatalogue()) {
                    if (shopProduct.getProduct().getName().equalsIgnoreCase(productName)) {
                        shop.getCatalogue().remove(shopProduct);
                        shopIF.updateShop(shop);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * function that removes the product from all the shop's catalogue
     * @param productName = String that contains the name of the product
     */
    public void removeProductFromAllShops(String productName) {
        ArrayList<Shop> shops = shopIF.readAllShops();
        for (Shop shop : shops) {
            if (shop.getCatalogue() != null) {
                ArrayList<ShopProduct> toRemove = new ArrayList<>();
                for (ShopProduct shopProduct : shop.getCatalogue()) {
                    if (shopProduct.getProduct() != null && shopProduct.getProduct().getName().equalsIgnoreCase(productName)) {
                        toRemove.add(shopProduct);
                    }
                }
                shop.getCatalogue().removeAll(toRemove);
                if (!toRemove.isEmpty()) {
                    shopIF.updateShop(shop);
                }
            }
        }
    }

    /**
     * function that returns information about all the products from all the shops
     * @param productsStrings = ArrayList of Strings that contains the product names, shops names and prices of the products
     * @return ArrayList of Strings that contains the product names, shops names and prices of the products
     */
    public ArrayList<String> shopsWithProduct(ArrayList<String> productsStrings) {
        int i;
        ArrayList<Shop> shops = shopIF.readAllShops();
        ArrayList<String> shopProducts = new ArrayList<>();
        for (Shop shop : shops) {
            if (!shop.getCatalogue().isEmpty()) {
                i = 0;
                while (i < productsStrings.size()) {
                    for (ShopProduct shopProduct : shop.getCatalogue()) {
                        if (shopProduct.getProduct().getName().equalsIgnoreCase(productsStrings.get(i)) && shopProduct.getProduct().getBrand().equalsIgnoreCase(productsStrings.get(i + 1))) {
                            shopProducts.add(shopProduct.getProduct().getName());
                            shopProducts.add(shopProduct.getShopName());
                            shopProducts.add(String.valueOf(shopProduct.getShopPrice()));
                        }
                    }
                    i += 2;
                }
            }
        }
        return shopProducts;
    }

    /**
     * function that returns information about the shop selected
     * @param position = Integer that indicates the position of the shop to be returned
     * @return ArrayList of Strings that contains the name, since and description of the shop
     */
    public ArrayList<String> getSelectedShopInfo(int position) {
        ArrayList<String> shopInfo = new ArrayList<>();
        ArrayList<Shop> shops = shopIF.readAllShops();
        shopInfo.add(shops.get(position - 1).getName());
        shopInfo.add(String.valueOf(shops.get(position - 1).getSince()));
        shopInfo.add(shops.get(position - 1).getDescription());
        return shopInfo;
    }

    /**
     * function that returns information about all the products from a shop (catalogue)
     * @param position = Integer that indicates the position of the shop to be returned
     * @return ArrayList of Strings that contains the name, brand and price of the products of the shop
     */
    public ArrayList<String> getSelectedCatalogue(int position) {
        ArrayList<String> catalogue = new ArrayList<>();
        ArrayList<Shop> shops = shopIF.readAllShops();
        for (ShopProduct shopProduct : shops.get(position - 1).getCatalogue()) {
            catalogue.add(shopProduct.getProduct().getName());
            catalogue.add(shopProduct.getProduct().getBrand());
            catalogue.add(String.valueOf(shopProduct.getShopPrice()));
        }
        return catalogue;
    }

    /**
     * method that adds a product to the cart
     * @param productName = String that contains the name of the product
     * @param productBrand = String that contains the brand of the product
     * @param shopName = String that contains the name of the shop
     */
    public void addProductToCart(String productName, String productBrand, String shopName) {
        ArrayList<Shop> shops = shopIF.readAllShops();
        for (Shop shop : shops) {
            for (ShopProduct shopProduct : shop.getCatalogue()) {
                if (shopProduct.getProduct().getName().equalsIgnoreCase(productName) && shopProduct.getProduct().getBrand().equalsIgnoreCase(productBrand) && shopProduct.getShopName().equalsIgnoreCase(shopName)) {
                    cart.addProduct(shopProduct);
                    return;
                }
            }
        }
    }

    /**
     * function that returns information about the products in the cart
     * @return ArrayList of Strings that contains the name, brand and price of the products in the cart
     */
    public ArrayList<String> getCart() {
        ArrayList<String> cartInfo = new ArrayList<>();
        for (ShopProduct shopProduct : cart.getProducts()) {
            cartInfo.add(shopProduct.getProduct().getName());
            cartInfo.add(shopProduct.getProduct().getBrand());
            cartInfo.add(String.valueOf(shopProduct.getShopPrice()));
        }
        return cartInfo;
    }

    /**
     * fucntion that return the total price spent in every shop
     * @return ArrayList of Floats that contains the total price spent in every shop
     */
    public ArrayList<Float> checkoutCart() {
        ArrayList<Shop> shops = shopIF.readAllShops();
        ArrayList<Float> earnings = new ArrayList<>();
        float earningsTotal;
        for (Shop shop : shops) {
            earningsTotal = 0;
            for (int j = 0; j < cart.getProducts().size(); j++) {
                if (cart.getProducts().get(j).getShopName().equalsIgnoreCase(shop.getName())) {
                    float priceWithoutIVA = cart.getProducts().get(j).getProduct().getOriginalPrice(cart.getProducts().get(j).getShopPrice());
                    shop.setEarnings(shop.getEarnings() + priceWithoutIVA);
                    earningsTotal = earningsTotal + cart.getProducts().get(j).getShopPrice();
                }
            }
            if (earningsTotal != 0) {
                earnings.add(earningsTotal);
            }
        }
        shopIF.updateShops(shops);
        return earnings;
    }

    /**
     * function that returns the name and earnings of every shop involved in the purchase
     * @return ArrayList of Strings that contains the name and earnings of every shop involved in the purchase
     */
    public ArrayList<String> getShopEarnings() {
        ArrayList<String> earnings = new ArrayList<>();
        ArrayList<Shop> shops = shopIF.readAllShops();

        for (Shop shop : shops) {
            boolean shopAlreadyAdded = false;
            for (int j = 0; j < earnings.size(); j += 2) {
                if (earnings.get(j).equalsIgnoreCase(shop.getName())) {
                    shopAlreadyAdded = true;
                    break;
                }
            }

            if (!shopAlreadyAdded) {
                for (int i = 0; i < cart.getProducts().size(); i++) {
                    if (cart.getProducts().get(i).getShopName().equalsIgnoreCase(shop.getName())) {
                        earnings.add(shop.getName());
                        earnings.add(String.valueOf(shop.getEarnings()));
                        break;
                    }
                }
            }
        }
        return earnings;
    }

    /**
     * method that clears the cart
     */
    public void clearCart() {
        cart.getProducts().clear();
    }

}
