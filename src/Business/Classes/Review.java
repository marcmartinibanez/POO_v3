package Business.Classes;

/**
 * Class Review contains a rate and a comment
 * each Product has a Review ArrayList
 *
 * @version 18.0.2 27 November 2023
 * @author Marc Martín
 * @author Joaquim Angas
 */
public class Review {
    private int rate;
    private String comment;

    /**
     * Constructor of the class Review
     * @param rate = Integer that indicates the rate of a review
     * @param comment = String that contains the comment of a review
     */
    public Review(int rate, String comment) {
        this.rate = rate;
        this.comment = comment;
    }

    /**
     * function that returns the rate of a review (Getter)
     * @return Integer that indicates the rate of a review
     */
    public int getRate() {
        return rate;
    }

    /**
     * function that returns the comment of a review (Getter)
     * @return String that contains the comment of a review
     */
    public String getComment() {
        return comment;
    }
}
