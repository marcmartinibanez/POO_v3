package Business.Classes.ProductsTypes;

import Business.Classes.Product;
import Business.Classes.Review;

import java.util.ArrayList;

public class SuperReduced extends Product {
    public SuperReduced(String name, String brand, float mrp, String category, ArrayList<Review> reviews) {
        super(name, brand, mrp, category, reviews);
    }
    public int returnIva (){
        if (getMrp() > 100){
            return 0;
        }
        else{
            return 4;
        }
    }
}
