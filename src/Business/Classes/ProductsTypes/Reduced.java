package Business.Classes.ProductsTypes;

import Business.Classes.Product;
import Business.Classes.Review;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Class Reduced contains all the information related to Reduced products
 * that will be able to be in shops
 *
 * @version 18.0.2 5 January 2024
 * @author Marc Martín
 * @author Joaquim Angas
 */
public class Reduced extends Product {

    /**
     * Constructor of the class Reduced
     */
    public Reduced(String name, String brand, float mrp, String category, ArrayList<Review> reviews) {
        super(name, brand, mrp, category, reviews);
    }

    /**
     * function that returns the price of a Reduced product without IVA
     * @param totalPrice = Float that contains the total price
     * @param reverse = Boolean that indicates if the operation has to be reversed
     * @return Float that contains the price without IVA
     */
    @Override
    public float getOriginalPrice(float totalPrice, boolean reverse) {
        float rate = 0, iva1;
        String formattedPrice;
        for (Review review : getReviews()) {
            rate += review.getRate();
        }
        if (rate > 3.5){
            iva1 = 5;
        }
        else {
            iva1 = 10;
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
