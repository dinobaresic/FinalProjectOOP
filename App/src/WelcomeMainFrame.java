import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * The WelcomeMainFrame class represents the main frame of the Aquapark Management application.
 * It provides a menu with various options for managing the application.
 */
public class WelcomeMainFrame extends JFrame {

    private JMenuBar menuBar;
    private  JMenu mainMenu;
    private JMenuItem startNewDayItem;
    private JMenuItem addNewCustomerItem;
    private JMenuItem showCurrentCustomersItem;
    private JMenuItem deleteCustomerItem;
    private JMenuItem exitCustomerItem;

    private JPanel welcomePanel;
    private JLabel welcomeLabel;

    /**
     * Constructs a WelcomeMainFrame instance.
     */
    public WelcomeMainFrame() {
        super("Aquapark Managmant application");
        initPanelComps();
        layoutComps();
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);



        // Postavljanje ActionListener-a za opcije
        startNewDayItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                File datoteka = new File("customerHistory.txt");

                try {
                    if (datoteka.exists()) {
                        // File exists, read its content to determine the last day number
                        FileReader fileReader = new FileReader(datoteka);
                        BufferedReader bufferedReader = new BufferedReader(fileReader);
                        String line;
                        int lastDayNumber = 0;

                        while ((line = bufferedReader.readLine()) != null) {
                            if (line.matches(".*Day (\\d+).*")) {
                                String dayNumberString = line.replaceAll(".*Day (\\d+).*", "$1");
                                lastDayNumber = Integer.parseInt(dayNumberString);
                            }
                        }

                        bufferedReader.close();

                        // Increment the last day number and append the new day information
                        lastDayNumber++;
                        FileWriter fileWriter = new FileWriter(datoteka, true);
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                        bufferedWriter.newLine(); // Add a new line before the new day information
                        bufferedWriter.write("------------------------- Day " + lastDayNumber + " -------------------------");
                        bufferedWriter.close();
                    } else {
                        // File doesn't exist, create it and write the first day information
                        FileWriter fileWriter = new FileWriter(datoteka);
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                        bufferedWriter.write("------------------------- Day 1 -------------------------");
                        bufferedWriter.close();
                    }

                    JOptionPane.showMessageDialog(null, "New day started! Now you can add new users.");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        addNewCustomerItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Dodajte funkcionalnost za "Add new customer" ovdje
                if(!new File("customerHistory.txt").exists()){
                    JOptionPane.showMessageDialog(null, "You must start new day first!");
                    return;
                }
                new AddCustomersFrame();
                dispose();
            }
        });

        showCurrentCustomersItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Check if the "customerHistory.txt" file exists
                File dataFile = new File("customerHistory.txt");

                if (!dataFile.exists()) {
                    // Display a message informing the user to start a new day first
                    JOptionPane.showMessageDialog(null, "Please start a new day first.");
                } else {
                    // The file exists, so open the CurrentCustomersFrame
                    new CurrentCustomersFrame();
                    dispose();
                }
            }

        });

        deleteCustomerItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Check if the "customerHistory.txt" file exists
                File dataFile = new File("customerHistory.txt");

                if (!dataFile.exists()) {
                    // Display a message informing the user to start a new day first
                    JOptionPane.showMessageDialog(null, "Please start a new day first.");
                } else {
                    // The file exists, so open the CurrentCustomersFrame
                    dispose();
                   new DeleteCustomerFrame();
                }
            }

        });

        exitCustomerItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    /**
     * Initializes the layout of components in the main frame.
     */
    private void layoutComps() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridy = 0;
        gbc.gridx = 0;
        getContentPane().setBackground(new Color(3, 140, 252));


        mainMenu.add(startNewDayItem);
        mainMenu.add(addNewCustomerItem);
        mainMenu.add(showCurrentCustomersItem);
        mainMenu.add(deleteCustomerItem);
        mainMenu.add(exitCustomerItem);

        menuBar.add(mainMenu);


        setJMenuBar(menuBar);

        welcomePanel.setBounds(0, 0, 800, 600);
        welcomePanel.setBackground(Color.WHITE);
        welcomePanel.add(welcomeLabel);
        welcomeLabel.setBounds(0, 0, 800, 600);
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomeLabel.setVerticalAlignment(JLabel.CENTER);
        welcomeLabel.setForeground(Color.BLACK);
        welcomeLabel.setFont(welcomeLabel.getFont().deriveFont(25.0f));
        add(welcomePanel, gbc);
    }

    /**
     * Initializes the components for the main frame.
     */
    private void initPanelComps() {
        menuBar = new JMenuBar();
        // Stvaranje opcija u JMenuBar-u
        mainMenu = new JMenu("Menu");
        startNewDayItem = new JMenuItem("Start new day");
        addNewCustomerItem = new JMenuItem("Add new customer");
        showCurrentCustomersItem = new JMenuItem("Show current customers");
        deleteCustomerItem = new JMenuItem("Delete customer");
        exitCustomerItem = new JMenuItem("Exit");
        welcomePanel = new JPanel();
        welcomeLabel = new JLabel("Welcome to Aquapark Managmant application!");
    }

}
