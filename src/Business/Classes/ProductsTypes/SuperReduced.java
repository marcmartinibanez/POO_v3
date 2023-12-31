package Business.Classes.ProductsTypes;

import Business.Classes.Product;
import Business.Classes.Review;

import java.util.ArrayList;
import java.util.Locale;

public class SuperReduced extends Product {
    public SuperReduced(String name, String brand, float mrp, String category, ArrayList<Review> reviews) {
        super(name, brand, mrp, category, reviews);
    }

    @Override
    public float getOriginalPrice(float totalPrice){
        float iva1;
        if (getMrp() > 100){
            iva1 = 0;
        }
        else{
            iva1 = 4;
        }
        float iva = (iva1 / 100) + 1;
        String formattedPrice = String.format(Locale.US, "%.2f", totalPrice / iva);
        return Float.parseFloat(formattedPrice);
    }
}
