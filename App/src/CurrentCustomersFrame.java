import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * The CurrentCustomersFrame class represents a GUI frame for displaying the current customers in the Aquapark Management application.
 * It provides a table to view customer information and buttons to go back to the main menu and update the table.
 */
public class CurrentCustomersFrame extends JFrame {

    private JPanel BottomPanel;

    private JPanel viewPanel;
    private JTable customerTable;
    private JScrollPane scrollPane;

    private JButton backButton;

    private JButton updateButton;


    CustomerManager customerManager;
    private String[][] data;
    private String[] columnNames = {"Name", "Surname", "Description", "Category", "Time Option", "Price"};
    private DefaultTableModel model;

    /**
     * Constructs a CurrentCustomersFrame instance.
     */
    public CurrentCustomersFrame() {

        setTitle("Current Customers");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        createComponents();
        componentLayout();
        activateApp();
        loadDataAndPopulateTable();

        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Loads customer data from a file and populates the table with it.
     */
    private void loadDataAndPopulateTable() {
        List<Customer> customers = loadDataFromFIle();
        populateTable(customers);
    }

    /**
     * Loads customer data from a file and returns a list of Customer objects.
     *
     * @return A list of Customer objects loaded from the file.
     */
    private List<Customer> loadDataFromFIle() {
        List<Customer> customers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("customerHistory.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Parse the line and create Customer objects
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    String name = parts[0].trim();
                    String surname = parts[1].trim();
                    String description = parts[2].trim();
                    String category = parts[3].trim();
                    String timeOption = parts[4].trim();
                    String price = parts[5].trim();

                    Customer customer = new Customer(name, surname, description, category, timeOption, price);
                    customers.add(customer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return customers;
    }

    /**
     * Refreshes the customer table by reloading and populating it with updated data.
     */
    private void refreshTable(){
        loadDataAndPopulateTable();
    }

    /**
     * Populates the customer table with data from a list of Customer objects.
     *
     * @param customers A list of Customer objects to populate the table with.
     */
    private void populateTable(List<Customer> customers) {
        // Clear the table
        model.setRowCount(0);

        // Populate the table
        for (Customer customer : customers) {
            Vector<String> row = new Vector<>();
            row.add(customer.getName());
            row.add(customer.getSurname());
            row.add(customer.getDescription());
            row.add(customer.getCategory());
            row.add(customer.getTimeOption());
            row.add(customer.getPrice());
            model.addRow(row);
        }
    }

    /**
     * Sets up event listeners and actions for the back and update buttons.
     */
    private void activateApp() {
        backButton.addActionListener(e -> {
            dispose();
            WelcomeMainFrame welcomeFrame = new WelcomeMainFrame();
            welcomeFrame.setVisible(true);
        });

        updateButton.addActionListener(e -> {
            refreshTable();
        });
    }


    /**
     * Creates and initializes GUI components.
     */
    private void createComponents() {
        BottomPanel = new JPanel();
        viewPanel = new JPanel();
        customerManager = new CustomerManager();
        backButton = new JButton("Back");
        updateButton = new JButton("Update");
        data = customerManager.getCustomersForLastDay();
        model = new DefaultTableModel(data, columnNames);
        customerTable = new JTable(model);
        scrollPane = new JScrollPane(customerTable);
    }

    /**
     * Defines the layout of GUI components within the frame.
     */
    private void componentLayout() {
        setLayout(new BorderLayout());
        add(viewPanel, BorderLayout.CENTER);
        add(BottomPanel, BorderLayout.SOUTH);
        backButton.setPreferredSize(new Dimension(150, 50));
        BottomPanel.add(backButton);
        updateButton.setPreferredSize(new Dimension(150, 50));
        BottomPanel.add(updateButton);
        add(scrollPane);

    }
}
