package Business.Classes.ProductsTypes;

import Business.Classes.Product;
import Business.Classes.Review;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Class SuperReduced contains all the information related to SuperReduced products
 * that will be able to be in shops
 *
 * @version 18.0.2 5 January 2024
 * @author Marc Martín
 * @author Joaquim Angas
 */
public class SuperReduced extends Product {

    /**
     * Constructor of the class SuperReduced
     */
    public SuperReduced(String name, String brand, float mrp, String category, ArrayList<Review> reviews) {
        super(name, brand, mrp, category, reviews);
    }

    /**
     * function that returns the price of a SuperReduced product without IVA
     * @param totalPrice = Float that contains the total price
     * @param reverse = Boolean that indicates if the operation has to be reversed
     * @return Float that contains the price without IVA
     */
    @Override
    public float getOriginalPrice(float totalPrice, boolean reverse){
        float iva1;
        String formattedPrice;
        if (getMrp() > 100){
            iva1 = 0;
        }
        else{
            iva1 = 4;
        }
        float iva = (iva1 / 100) + 1;
        if (!reverse) {
            formattedPrice = String.format(Locale.US, "%.2f", totalPrice / iva);
        }
        else {
            formattedPrice = String.format(Locale.US, "%.2f", totalPrice * iva);
        }
        return Float.parseFloat(formattedPrice);
    }
}
