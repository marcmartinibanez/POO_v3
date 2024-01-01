package Business.Classes.ShopsTypes;

import Business.Classes.Shop;
import Business.Classes.ShopProduct;

import java.util.ArrayList;

/**
 * Class Loyalty contains all the information related to Loyalty shops
 *
 * @version 18.0.2 5 January 2024
 * @author Marc Martín
 * @author Joaquim Angas
 */
public class Loyalty extends Shop {
    private final float loyaltyThreshold;
    private boolean isLoyal;

    /**
     * Constructor of the class Loyalty
     */
    public Loyalty(String name, String description, int since, float earnings, String bussinessModel, ArrayList<ShopProduct> catalogue, float loyaltyThreshold) {
        super(name, description, since, earnings, bussinessModel, catalogue);
        this.loyaltyThreshold = loyaltyThreshold;
        this.isLoyal = false;
    }

    /**
     * function that returns the loyalty threshold of a Loyalty shop (Getter)
     * @return Float that contains the loyalty threshold
     */
    public float getLoyaltyThreshold() {
        return loyaltyThreshold;
    }

    /**
     * function that returns if a client of the shop is loyal or not (Getter)
     * @return Boolean that indicates if a client of the shop is loyal or not
     */
    public boolean isLoyal() {
        return isLoyal;
    }

    /**
     * function that sets if a client of the shop is loyal or not (Setter)
     * @param loyal = Boolean that contains if it is or not
     */
    public void setLoyal(boolean loyal) {
        isLoyal = loyal;
    }
}
