package Business.Classes.ShopsTypes;

import Business.Classes.Shop;
import Business.Classes.ShopProduct;

import java.util.ArrayList;

public class Loyalty extends Shop {
    private final float loyaltyThreshold;

    public Loyalty(String name, String description, int since, float earnings, String bussinessModel, ArrayList<ShopProduct> catalogue, float loyaltyThreshold) {
        super(name, description, since, earnings, bussinessModel, catalogue);
        this.loyaltyThreshold = loyaltyThreshold;
    }
    public float getLoyaltyThreshold() {
        return loyaltyThreshold;
    }

    public boolean isLoyal(float initialEarnings) {
        return getEarnings() - initialEarnings > loyaltyThreshold;
    }
}
