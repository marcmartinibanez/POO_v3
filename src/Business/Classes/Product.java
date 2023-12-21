package Business.Classes;

import java.util.ArrayList;

/**
 * Class Product contains all the information related to products,
 * that will be able to be in shops
 *
 * @version 18.0.2 27 November 2023
 * @author Marc Martín
 * @author Joaquim Angas
 */
public class Product {
    private final String name;
    private final String brand;
    private final float mrp;
    private final String category;
    private final ArrayList<Review> reviews;

    /**
     * Constructor of the Class Product
     * @param name = String that contains the name of a Product
     * @param brand = String that contains the brand of a Product
     * @param mrp = Float that contains the MRP of a Product
     * @param category = String that contains the category of a Product
     * @param reviews = Review Arraylist that contains the reviews of a Product
     */
    public Product(String name, String brand, float mrp, String category, ArrayList<Business.Classes.Review> reviews) {
        this.name = name;
        this.brand = brand;
        this.mrp = mrp;
        this.category = category;
        this.reviews = reviews;
    }

    /**
     * function that returns the name of a Product (Getter)
     * @return String that contains the name
     */
    public String getName() {
        return name;
    }

    /**
     * function that returns the brand of a Product (Getter)
     * @return String that contains the brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * function that returns the MRP of a Product (Getter)
     * @return Float that contains the MRP
     */
    public float getMrp() {
        return mrp;
    }

    /**
     * function that returns the reviews of a Product (Getter)
     * @return Review Arraylist that contains the reviews
     */
    public ArrayList<Review> getReviews() {
        return reviews;
    }

    /**
     * function that adds a review to a Product
     * @param review = Review that will be added to a Product
     */
    public void addReview(Review review) {
        this.reviews.add(review);
    }

    /**
     * function that returns the price without IVA of a Product
     * @return Integer that contains the price that is being sold in the shop
     */
    public float getOriginalPrice(float totalPrice){
        return totalPrice;
    }
}
