package Business.Classes.ShopsTypes;

import Business.Classes.Shop;
import Business.Classes.ShopProduct;

import java.util.ArrayList;

public class Loyalty extends Shop {
    private final float loyaltyThreshold;
    private boolean isLoyal;

    public Loyalty(String name, String description, int since, float earnings, String bussinessModel, ArrayList<ShopProduct> catalogue, float loyaltyThreshold) {
        super(name, description, since, earnings, bussinessModel, catalogue);
        this.loyaltyThreshold = loyaltyThreshold;
        this.isLoyal = false;
    }
    public float getLoyaltyThreshold() {
        return loyaltyThreshold;
    }

    public boolean isLoyal() {
        return isLoyal;
    }

    public void setLoyal(boolean loyal) {
        isLoyal = loyal;
    }
}
