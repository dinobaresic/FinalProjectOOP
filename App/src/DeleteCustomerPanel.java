import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * The DeleteCustomerPanel class represents the panel for deleting customers in the Aquapark Management application.
 */
public class DeleteCustomerPanel extends JPanel {

    private JTextField nameField;
    private JButton searchButton;
    private JComboBox<Customer> customerList;
    private JButton deleteButton;

    private JButton backButton;
    private CustomerManager customerManager;

    /**
     * Constructs a DeleteCustomerPanel instance.
     */
    public DeleteCustomerPanel(){
        initComps();
        layoutComps();
        activateComps();

    }

    /**
     * Initializes the components of the panel.
     */
    private void initComps(){
        nameField = new JTextField("Name", 20);
        searchButton = new JButton("Search");
        customerList = new JComboBox<>();
        deleteButton = new JButton("Delete");
        customerManager = new CustomerManager();
        backButton = new JButton("Back");
    }

    /**
     * Defines the layout of components within the panel.
     */
    private void layoutComps(){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        nameField.setPreferredSize(new Dimension(220, 30));
        nameField.setFont(new Font("Arial", Font.PLAIN, 15));
        add(nameField, gbc);

        gbc.gridx++;
        searchButton.setPreferredSize(new Dimension(150, 50));
        searchButton.setFont(new Font("Arial", Font.PLAIN, 15));
        add(searchButton, gbc);

        gbc.gridx--;
        gbc.gridy++;
        customerList.setPreferredSize(new Dimension(430, 30));
        customerList.setFont(new Font("Arial", Font.PLAIN, 15));
        add(customerList, gbc);

        gbc.gridx++;
        deleteButton.setPreferredSize(new Dimension(150, 50));
        deleteButton.setFont(new Font("Arial", Font.PLAIN, 15));
        add(deleteButton, gbc);

        gbc.gridx--;
        gbc.gridy++;
        backButton.setPreferredSize(new Dimension(150, 50));
        backButton.setFont(new Font("Arial", Font.PLAIN, 15));
        add(backButton, gbc);
    }

    /**
     * Activates the components and defines their behavior.
     */
    private void activateComps(){
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                List<Customer> customers = customerManager.searchCustomer(name);
                if(customers.size() == 0){
                    JOptionPane.showMessageDialog(null, "No customers found!");
                }
                for (Customer customer : customers){
                    customerList.addItem(customer);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Customer selectedCustomer = (Customer) customerList.getSelectedItem();
                if (selectedCustomer != null) {
                    customerManager.deleteCustomer(selectedCustomer, selectedCustomer.getName());
                    DefaultComboBoxModel<Customer> model = (DefaultComboBoxModel<Customer>) customerList.getModel();
                    model.removeElement(selectedCustomer);

                    JOptionPane.showMessageDialog(null, "Customer deleted.");

                    customerList.setSelectedIndex(-1);


                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the parent frame of the DeleteCustomerPanel
                JFrame deleteCustomerFrame = (JFrame) SwingUtilities.getWindowAncestor(DeleteCustomerPanel.this);

                deleteCustomerFrame.dispose();

                WelcomeMainFrame welcomeFrame = new WelcomeMainFrame();
                welcomeFrame.setVisible(true);
            }
        });


    }

}
