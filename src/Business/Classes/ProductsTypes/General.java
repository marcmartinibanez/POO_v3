package Business.Classes.ProductsTypes;
import Business.Classes.Product;
import Business.Classes.Review;

import java.util.ArrayList;

public class General extends Product {
    public General(String name, String brand, float mrp, String category, ArrayList<Review> reviews) {
        super(name, brand, mrp, category, reviews);
    }

    public float getOriginalPrice(float totalPrice){
        float iva = ((float) 21 / 100) + 1;
        return (totalPrice / iva);
    }
}
