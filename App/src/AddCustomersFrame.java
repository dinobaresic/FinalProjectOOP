import javax.swing.*;
import java.awt.*;

/**
 * The AddCustomersFrame class represents a GUI frame for adding customers to the Aquapark Management application.
 * It provides an interface to input customer information and adds customers to the system.
 */
public class AddCustomersFrame extends JFrame {

    private AddCustomersPanel panel;


    /**
     * Constructs an AddCustomersFrame instance.
     */
    public AddCustomersFrame() {

        super("Add Customers");
        setSize(800, 600);

        createComponents();
        componentLayout();
        activateApp();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    /**
     * Activates the application by setting up event listeners and initializing components.
     */
    private void activateApp() {
        panel.setAddCustomersPanelListener(new AddCustomersPanelListener() {
            @Override
            public void addCustomersPanelEventOccurred(AddCustomersPanelEvent ace) {
                String name = ace.getName();
                String surname = ace.getSurname();
                String description = ace.getDescription();
                String category = ace.getCategory();
                String timeOption = ace.getTimeOption();
                String price = ace.getPrice();
                Customer newCustomer = new Customer(name, surname, description, category, timeOption, price);
                newCustomer.setDuration(timeOption);
                CustomerManager customerManager = new CustomerManager(newCustomer);
                customerManager.addCustomer(newCustomer);
                JOptionPane.showMessageDialog(null, "Customer added successfully!");

            }
        });

        panel.setBackButtonListener(new BackButtonListener() {
            @Override
            public void backButtonClicked() {
                dispose();
                WelcomeMainFrame welcomeFrame = new WelcomeMainFrame();
                welcomeFrame.setVisible(true);
            }
        });

        panel.activateComps();
    }

    /**
     * Creates and initializes components for the frame.
     */
    private void createComponents() {
        panel = new AddCustomersPanel();
    }

    /**
     * Sets the layout of the frame and adds the panel to it.
     */
    private void componentLayout() {
        setLayout(new BorderLayout());
        add(panel);
    }

}
