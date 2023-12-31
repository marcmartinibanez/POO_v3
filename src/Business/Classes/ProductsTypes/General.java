package Business.Classes.ProductsTypes;
import Business.Classes.Product;
import Business.Classes.Review;

import java.util.ArrayList;
import java.util.Locale;

public class General extends Product {
    public General(String name, String brand, float mrp, String category, ArrayList<Review> reviews) {
        super(name, brand, mrp, category, reviews);
    }

    @Override
    public float getOriginalPrice(float totalPrice){
        float iva = ((float) 21 / 100) + 1;
        String formattedPrice = String.format(Locale.US, "%.2f", totalPrice / iva);
        return Float.parseFloat(formattedPrice);
    }

}
