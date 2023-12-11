import Business.ProductManager;
import Business.ShopManager;
import Persistence.ProductDAO;
import Persistence.ShopDAO;
import Presentation.Controller;
import Presentation.UI;

/**
 * The application's main class, just to hold the main method.
 *
 * @version 18.0.2 27 November 2023
 * @author Marc Martín
 * @author Joaquim Angas
 */
public class Main {
    /**
     * The Main of our program has declared in it all the principal classes used in the program
     * such as the DAOs (this classes control everything related with the Json files) in the layer
     * Persistence. We also have the Managers (Shop and Product) and the Controller.
     * We call the function run (Contained in the controller) which implements all the logic of
     * our program.
     *
     * @param args The program's arguments
     */
    public static void main(String[] args) {
        UI ui = new UI();

        ProductDAO productDAO = new ProductDAO();
        ShopDAO shopDAO = new ShopDAO();

        ProductManager productManager = new ProductManager(productDAO);
        ShopManager shopManager = new ShopManager(shopDAO);

        Controller controller = new Controller(ui, productManager, shopManager);
        controller.run();
    }
}