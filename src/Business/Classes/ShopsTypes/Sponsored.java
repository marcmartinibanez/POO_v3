package Business.Classes.ShopsTypes;

import Business.Classes.Shop;
import Business.Classes.ShopProduct;

import java.util.ArrayList;

/**
 * Class Sponsored contains all the information related to Sponsored shops
 *
 * @version 18.0.2 5 January 2024
 * @author Marc Martín
 * @author Joaquim Angas
 */
public class Sponsored extends Shop {
    private final String sponsorBrand;

    /**
     * Constructor of the class Sponsored
     */
    public Sponsored(String name, String description, int since, float earnings, String bussinessModel, ArrayList<ShopProduct> catalogue, String sponsorBrand) {
        super(name, description, since, earnings, bussinessModel, catalogue);
        this.sponsorBrand = sponsorBrand;
    }

    /**
     * function that returns the sponsor brand of a Sponsored shop (Getter)
     * @return String that contains the sponsor brand
     */
    public String getSponsorBrand() {
        return sponsorBrand;
    }
}
