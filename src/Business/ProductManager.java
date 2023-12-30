package Business;

import Business.Classes.Product;
import Business.Classes.ProductsTypes.General;
import Business.Classes.ProductsTypes.Reduced;
import Business.Classes.ProductsTypes.SuperReduced;
import Business.Classes.Review;
import Persistence.ProductIF;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Class ProductManager contains ProductDAO, and it contains all the functions
 * in charge of working and controlling everything related to the products.
 *
 * @version 18.0.2 27 November 2023
 * @author Marc Martín
 * @author Joaquim Angas
 */
public class ProductManager {
    private final ProductIF productIF;

    /**
     * Constructor of the class ProductManager, it contains a ProductDAO, which will write and provide
     * the products from the json file "products.json"
     * @param productIF = ProductIF that will write and provide the products from the json file "products.json" or from the API
     */
    public ProductManager(ProductIF productIF) {
        this.productIF = productIF;
    }

    /**
     * function that opens the json file "products.json"
     * @return boolean that indicates if the json file "products.json" has been opened correctly
     * @throws FileNotFoundException = Exception that indicates that the file has not been found
     */
    public boolean loadProducts() throws FileNotFoundException {
        return productIF.openJSon();
    }

    /**
     * method that writes a product in the file "products.json"
     * @param product = Product to be written in the file "products.json"
     */
    public void writeProductInDao (Product product) {
        productIF.createProduct(product);
    }

    /**
     * method tha updates a product in the file "products.json"
     * @param product = Product to be updated in the file "products.json"
     */
    public void updateProductDao(Product product) {
        productIF.updateProduct(product);
    }

    /**
     * function that creates a product
     * @param name = String that contains the name of the product
     * @param brand = String that contains the brand of the product
     * @param mrp = Float that contains the MRP of the product
     * @param category = String that contains the category of the product
     * @return Product that contains the product created
     */
    public Product createProduct(String name, String brand, float mrp, String category) {
        ArrayList<Review> reviews= new ArrayList<>();
        if (category.equalsIgnoreCase("Superreduced Taxes")) {
            return new SuperReduced(name, brand, mrp, category, reviews);
        }
        else if (category.equalsIgnoreCase("Reduced Taxes")) {
            return new Reduced(name, brand, mrp, category, reviews);
        }
        else if (category.equalsIgnoreCase("General")) {
            return new General(name, brand, mrp, category, reviews);
        }
        return null;
    }

    /**
     * function that returns if the product name is unique
     * @param productName = String that contains the name of the product
     * @return boolean that indicates if the product name is unique
     */
    public boolean unicProductName(String productName) {
        ArrayList<Product> products = productIF.readAllProds();
        productName = productName.toLowerCase();
        for (Product product : products) {
            if (product.getName().toLowerCase().equals(productName)) {
                return false;
            }
        }
        return true;
    }

    /**
     * function that return the string with the first letter of each word capitalized
     * @param name = String that contains the string to be transformed
     * @return String that contains the name of the product with the first letter of each word capitalized
     */
    public String transformFormat(String name) {
        String nameLowerCase = name.toLowerCase();

        StringBuilder transformedName = new StringBuilder();
        boolean capitalizar = true;

        for (char c : nameLowerCase.toCharArray()) {
            if (Character.isWhitespace(c)) {
                transformedName.append(c);
                capitalizar = true;
            }
            else if (capitalizar) {
                transformedName.append(Character.toUpperCase(c));
                capitalizar = false;
            }
            else {
                transformedName.append(c);
            }
        }
        return transformedName.toString();
    }

    /**
     * method that deletes a product from the file "products.json"
     * @param position = Integer that indicates the position of the product to be deleted
     */
    public void deleteProduct(int position) {
        productIF.removeProd(position - 1);
    }

    /**
     * function that return all the products names and brands stored in the file "products.json"
     * @return ArrayList of Strings that contains the names and brands of all the products
     */
    public ArrayList<String> getListProductNames() {
        ArrayList<Product> products = productIF.readAllProds();
        ArrayList<String> names = new ArrayList<>();
        for (Product product : products) {
            names.add(product.getName());
            names.add(product.getBrand());
        }
        return names;
    }

    /**
     * function that returns the name and brand of a selected product
     * @param position = Integer that indicates the position of the product to be returned
     * @return ArrayList of Strings that contains the name and brand of the product
     */
    public ArrayList<String> getSelectedProduct(int position) {
        ArrayList<Product> products = productIF.readAllProds();
        ArrayList<String> names = new ArrayList<>();
        names.add(products.get(position - 1).getName());
        names.add(products.get(position - 1).getBrand());
        return names;
    }

    /**
     * function that returns if the product exists or not
     * @param prodName = String that contains the name of the product
     * @return boolean that indicates if the product exists
     */
    public boolean checkProductExistence(String prodName) {
        ArrayList<Product> products = productIF.readAllProds();
        if (products == null || products.isEmpty()) {
            return false;
        }
        prodName = prodName.toLowerCase();
        for (Product product : products) {
            if (product.getName().toLowerCase().equals(prodName)) {
                return true;
            }
        }
        return  false;
    }

    /**
     * function that returns the Product that contains the name of the product passed by parameter
     * @param prodName = String that contains the name of the product
     * @return Product that contains the product
     */
    public Product getProduct(String prodName) {
        ArrayList<Product> products = productIF.readAllProds();
        if (products == null || products.isEmpty()) {
            return null;
        }
        prodName = prodName.toLowerCase();
        for (Product product : products) {
            if (product.getName().toLowerCase().equals(prodName)) {
                return product;
            }
        }
        return null;
    }

    /**
     * function that returns the names and brands of the products that includes the query passed by parameter
     * @param query = String that contains the query to be searched
     * @return ArrayList of Strings that contains the names and brands of the products found
     */
    public ArrayList<String> productsFound(String query) {
        ArrayList<Product> products = productIF.readAllProds();
        ArrayList<String> names = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().toLowerCase().contains(query.toLowerCase()) || product.getBrand().equals(query)) {
                names.add(product.getName());
                names.add(product.getBrand());
            }
        }
        return names;
    }

    /**
     * function that returns the reviews of a product
     * @param name = String that contains the name of the product
     * @param brand = String that contains the brand of the product
     * @return ArrayList of Strings that contains the reviews of the product
     */
    public ArrayList<String> getProductReviews(String name, String brand) {
        ArrayList<Product> products = productIF.readAllProds();
        ArrayList<String> reviews = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name) && product.getBrand().equalsIgnoreCase(brand)) {
                for (Review review : product.getReviews()) {
                    reviews.add(String.valueOf(review.getRate()));
                    reviews.add(review.getComment());
                }
                break;
            }
        }
        return reviews;
    }

    /**
     * function that adds a review to a product
     * @param productName = String that contains the name of the product
     * @param productBrand = String that contains the brand of the product
     * @param rate = Integer that contains the rate of the review
     * @param comment = String that contains the comment of the review
     * @return Product that contains the product with the review added
     */
    public Product addReviewToProduct(String productName, String productBrand,int rate, String comment) {
        ArrayList<Product> products = productIF.readAllProds();
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(productName) && product.getBrand().equalsIgnoreCase(productBrand)) {
                Review review = new Review(rate, comment);
                product.addReview(review);
                return product;
            }
        }
        return null;
    }

    public void xxx() {
        ArrayList<Product> products = productIF.readAllProds();
        for (Product product : products) {
            System.out.println(product.getOriginalPrice(100));
        }
    }
}
