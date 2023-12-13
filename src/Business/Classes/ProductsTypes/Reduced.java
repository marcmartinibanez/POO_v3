package Business.Classes.ProductsTypes;

import Business.Classes.Product;
import Business.Classes.Review;

import java.util.ArrayList;

public class Reduced extends Product {

    public Reduced(String name, String brand, float mrp, String category, ArrayList<Review> reviews) {
        super(name, brand, mrp, category, reviews);
    }
    public int returnIva(){
        float rate = 0;
        for (Review review : getReviews()) {
            rate += review.getRate();
        }
        if (rate > 3.5){
            return 5;
        }
        else {
            return 10;
        }
    }
}
