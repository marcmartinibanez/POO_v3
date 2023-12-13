package Business.Classes.ShopsTypes;

import Business.Classes.Shop;
import Business.Classes.ShopProduct;

import java.util.ArrayList;

public class Sponsored extends Shop {
    private final String sponsorBrand;

    public Sponsored(String name, String description, int since, float earnings, String bussinessModel, ArrayList<ShopProduct> catalogue, String sponsorBrand) {
        super(name, description, since, earnings, bussinessModel, catalogue);
        this.sponsorBrand = sponsorBrand;
    }

    public String getSponsorBrand() {
        return sponsorBrand;
    }
}
