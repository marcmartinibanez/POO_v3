package Business.Classes;

import java.util.ArrayList;

/**
 * Class Shop contains all the information related to shops
 *
 * @version 18.0.2 27 November 2023
 * @author Marc Martín
 * @author Joaquim Angas
 */
public class Shop {

    private final String name;
    private final String description;
    private final int since;
    private float earnings;
    private final String businessModel;
    private final ArrayList<ShopProduct> catalogue;

    /**
     * Constructor of the Class Shop
     * @param name = String that contains the name of a Shop
     * @param description = String that contains the description of a Shop
     * @param since = Integer that indicates the year of foundation of a Shop
     * @param earnings = Float that contains the earnings of a Shop
     * @param bussinessModel = String that contains the bussiness model of a Shop
     * @param catalogue = ShopProduct Arraylist that contains the products of a Shop
     */
    public Shop(String name, String description, int since, float earnings, String bussinessModel, ArrayList<Business.Classes.ShopProduct> catalogue) {
        this.name = name;
        this.description = description;
        this.since = since;
        this.earnings = earnings;
        this.businessModel = bussinessModel;
        this.catalogue = catalogue;
    }

    /**
     * function that returns the name of a Shop (Getter)
     * @return String that contains the name
     */
    public String getName() {
        return name;
    }

    /**
     * function that returns the description of a Shop (Getter)
     * @return String that contains the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * function that returns the year of foundation of a Shop (Getter)
     * @return Integer that contains the year of foundation
     */
    public int getSince() {
        return since;
    }

    /**
     * function that returns the earnings of a Shop (Getter)
     * @return Float that contains the earnings
     */
    public float getEarnings() {
        return earnings;
    }

    /**
     * function that returns the bussiness model of a Shop (Getter)
     * @return String that contains the bussiness model
     */
    public String getBusinessModel() {
        return businessModel;
    }

    /**
     * function that returns the products of a Shop (Getter)
     * @return ShopProduct Arraylist that contains the products
     */
    public ArrayList<ShopProduct> getCatalogue() {
        return catalogue;
    }

    /**
     * function that sets the name of a Shop (Setter)
     * @param earnings = Float that contains the earnings we want to add
     */
    public void setEarnings(float earnings) {
        this.earnings = earnings;
    }

    /**
     * function that adds a product to a Shop
     * @param shopProduct = ShopProduct that will be added to a Shop
     */
    public void addProductToCatalogue(ShopProduct shopProduct) {
        this.catalogue.add(shopProduct);
    }

    public String getSponsorBrand() {
        return "";
    }

    public float getLoyaltyThreshold() {
        return 0;
    }

    public boolean isLoyal(float initialEarnings) {
        return false;
    }
}
