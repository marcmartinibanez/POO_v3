package Business.Classes.ProductsTypes;

import Business.Classes.Product;
import Business.Classes.Review;

import java.util.ArrayList;
import java.util.Locale;

public class Reduced extends Product {

    public Reduced(String name, String brand, float mrp, String category, ArrayList<Review> reviews) {
        super(name, brand, mrp, category, reviews);
    }

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
