package Business.Classes;

/**
 * Class ShopProduct contains a Product, the name of the shop that sells the product and his price
 * each Shop has a ShopProduct ArrayList
 *
 * @version 18.0.2 27 November 2023
 * @author Marc Martín
 * @author Joaquim Angas
 */
public class ShopProduct {
    private Product product;
    private String shopName;
    private float shopPrice;

    /**
     * Constructor of the class ShopProduct
     * @param product = Product that contains the product
     * @param shopName = String that contains the name of the shop
     * @param shopPrice = Float that contains the price of the product
     */
    public ShopProduct(Product product, String shopName, float shopPrice) {
        this.product = product;
        this.shopName = shopName;
        this.shopPrice = shopPrice;
    }

    /**
     * function that returns the product of a ShopProduct (Getter)
     * @return Product that contains the product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * function that returns the name of the shop of a ShopProduct (Getter)
     * @return String that contains the name of the shop
     */
    public String getShopName() {
        return shopName;
    }

    /**
     * function that returns the price of a ShopProduct (Getter)
     * @return Float that contains the price of the product
     */
    public float getShopPrice() {
        return shopPrice;
    }
}
