package Business.Classes.ProductsTypes;
import Business.Classes.Product;
import Business.Classes.Review;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Class General contains all the information related to General products
 * that will be able to be in shops
 *
 * @version 18.0.2 5 January 2024
 * @author Marc Martín
 * @author Joaquim Angas
 */
public class General extends Product {

    /**
     * Constructor of the class General
     */
    public General(String name, String brand, float mrp, String category, ArrayList<Review> reviews) {
        super(name, brand, mrp, category, reviews);
    }

    /**
     * function that returns the price of a General product without IVA
     * @param totalPrice = Float that contains the total price
     * @param reverse = Boolean that indicates if the operation has to be reversed
     * @return Float that contains the price without IVA
     */
    @Override
    public float getOriginalPrice(float totalPrice, boolean reverse){
        float iva = ((float) 21 / 100) + 1;
        String formattedPrice;
        if (!reverse) {
            formattedPrice = String.format(Locale.US, "%.2f", totalPrice / iva);
        }
        else {
            formattedPrice = String.format(Locale.US, "%.2f", totalPrice * iva);
        }
        return Float.parseFloat(formattedPrice);
    }

}
