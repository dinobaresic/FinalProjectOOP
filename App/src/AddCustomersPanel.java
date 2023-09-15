import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The AddCustomersPanel class represents a JPanel for inputting customer information in the Aquapark Management application.
 * It provides fields for name, surname, description, category, time option, and price, along with submit and back buttons.
 */
public class AddCustomersPanel extends JPanel {

    private JTextField nameField;
    private JTextField surnameField;
    private JTextArea descriptionArea;
    private JComboBox<String> categoryBox;
    private JRadioButton thirtyMinutes;
    private JRadioButton oneHour;
    private JRadioButton oneDay;
    private JTextField priceField;

    private JButton submitButton;
    private JButton backButton;
    private ButtonGroup buttonGroup;

    private AddCustomersPanelListener addCustomersPanelListener;
    private BackButtonListener backButtonListener;

    /**
     * Constructs an AddCustomersPanel instance.
     */
    public AddCustomersPanel() {
        createComponents();
        componentLayout();
    }

    /**
     * Creates the GUI components for the panel.
     */
    private void createComponents() {
        nameField = new JTextField("Name");
        surnameField = new JTextField("Surname");
        descriptionArea = new JTextArea("Description");
        categoryBox = new JComboBox<>(new String[]{"Adult", "Child"});
        thirtyMinutes = new JRadioButton("1/2 hour");
        oneHour = new JRadioButton("1 hour");
        oneDay = new JRadioButton("1 day");
        priceField = new JTextField("Price");
        submitButton = new JButton("Submit");
        backButton = new JButton("Back");
        buttonGroup = new ButtonGroup();

    }


    /**
     * Arranges the components in the panel's layout.
     */
    private void componentLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 10, 10, 10);


        nameField.setPreferredSize(new Dimension(220, 30));
        nameField.setFont(new Font("Arial", Font.PLAIN, 15));
        add(nameField, gbc);

        gbc.gridy++;
        surnameField.setPreferredSize(new Dimension(220, 30));
        surnameField.setFont(new Font("Arial", Font.PLAIN, 15));
        add(surnameField, gbc);

        gbc.gridy++;
        descriptionArea.setPreferredSize(new Dimension(220, 100));
        descriptionArea.setLineWrap(true); // Enable line wrap
        descriptionArea.setWrapStyleWord(true); // Wrap at word boundaries
        descriptionArea.setAlignmentX(Component.LEFT_ALIGNMENT); // Set text alignment to left
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 15));
        add(descriptionArea, gbc);

        gbc.gridx++;
        gbc.gridy = 0;

        categoryBox.setPreferredSize(new Dimension(220, 30));
        categoryBox.setFont(new Font("Arial", Font.PLAIN, 15));
        add(categoryBox, gbc);

        gbc.gridy++;

        thirtyMinutes.setSelected(true);
        thirtyMinutes.setFont(new Font("Arial", Font.PLAIN, 15));
        add(thirtyMinutes, gbc);

        gbc.weightx = 1;

        gbc.gridx++;

        oneHour.setFont(new Font("Arial", Font.PLAIN, 15));
        add(oneHour, gbc);

        gbc.gridx++;
        gbc.weightx = 1;

        oneDay.setFont(new Font("Arial", Font.PLAIN, 15));
        add(oneDay, gbc);

        gbc.gridx--;
        gbc.gridx--;

        gbc.gridy++;

        priceField.setPreferredSize(new Dimension(220, 30));
        priceField.setFont(new Font("Arial", Font.PLAIN, 15));

        add(priceField,gbc);

        gbc.gridy++;
        gbc.gridx = 0;

        submitButton.setPreferredSize(new Dimension(150, 50));
        submitButton.setFont(new Font("Arial", Font.PLAIN, 15));
        add(submitButton, gbc);

        gbc.gridx++;
        gbc.gridx++;

        backButton.setPreferredSize(new Dimension(150, 50));
        backButton.setFont(new Font("Arial", Font.PLAIN, 15));

        add(backButton, gbc);

        priceField.setEditable(false);

        buttonGroup.add(thirtyMinutes);
        buttonGroup.add(oneHour);
        buttonGroup.add(oneDay);
    }

    /**
     * Sets the listener for the back button.
     *
     * @param listener The listener to set.
     */
    public void setBackButtonListener(BackButtonListener listener) {
        this.backButtonListener = listener;
    }

    /**
     * Sets the listener for the add customers panel.
     *
     * @param listener The listener to set.
     */
    public void setAddCustomersPanelListener(AddCustomersPanelListener acpl){
        this.addCustomersPanelListener = acpl;
    }

    /**
     * Activates the panel's components and defines their behavior.
     */
    public void  activateComps(){

        if (addCustomersPanelListener !=null ){
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // System.out.println("Submit button pressed!");

                    String name = nameField.getText();
                    String surname = surnameField.getText();
                    String description = descriptionArea.getText();
                    String category = (String) categoryBox.getSelectedItem();
                    String price = priceField.getText();
                    String timeOption = getTimeOption();
                    AddCustomersPanelEvent acpl = new AddCustomersPanelEvent(this, name, surname, description, category, timeOption, price);
                    addCustomersPanelListener.addCustomersPanelEventOccurred(acpl);
                    resetForm();
                    // System.out.println("Customer added!");

                }
            });
            thirtyMinutes.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //System.out.println("30 minutes selected!");
                    priceField.setText("5€");
                }
            });
            oneHour.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //System.out.println("1 hour selected!");
                    priceField.setText("8€");
                }
            });
            oneDay.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //System.out.println("1 day selected!");
                    priceField.setText("20€");
                }
            });
        }

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Notify the listener when the back button is clicked
                if (backButtonListener != null) {
                    backButtonListener.backButtonClicked();
                }
            }
        });


    }

    /**
     * Gets the selected time option (e.g., 30 minutes, 1 hour, 1 day).
     *
     * @return The selected time option.
     */
    public String getTimeOption(){
        String timeOption = "";
        if (thirtyMinutes.isSelected()) {
            timeOption = "30 minutes";
            priceField.setText("5€");
        } else if (oneHour.isSelected()) {
            timeOption = "1 hour";
            priceField.setText("8€");
        } else if (oneDay.isSelected()) {
            timeOption = "1 day";
            priceField.setText("20€");
        }
        return timeOption;
    }


    /**
     * Resets the form by clearing and setting default values in input fields.
     */
    private void resetForm(){
        nameField.setText("Name");
        surnameField.setText("Surname");
        nameField.requestFocus();
        descriptionArea.setText("Description");
        categoryBox.setSelectedIndex(0);
        thirtyMinutes.setSelected(true);
        priceField.setText("8€");
    }

}
