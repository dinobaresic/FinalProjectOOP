import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The CustomerManager class manages customer data and interactions with the file system in the Aquapark Management application.
 */
public class CustomerManager {

    private List<Customer> customers;
    private Customer customer;
    private FileHandler fileHandler;

    /**
     * Constructs a CustomerManager instance.
     *
     * @param customer The customer for whom the manager is responsible.
     */
    public CustomerManager(Customer customer) {
        this.customer = customer;
        customers = new ArrayList<>();
    }

    /**
     * Constructs a CustomerManager instance without a specific customer.
     */
    public CustomerManager(){

    }

    /**
     * Adds a customer to the internal list and saves the customer's data to a file.
     *
     * @param customer The customer to add.
     */
    public void addCustomer(Customer customer) {
        customers.add(customer);
        fileHandler = new FileHandler(customer);
        fileHandler.saveCustomer();
    }

    /**
     * Deletes a customer from the file based on the selected customer and name.
     *
     * @param selectedCustomer The customer to delete.
     * @param name             The name of the customer to delete.
     */
    public void deleteCustomer(Customer selectedCustomer, String name) {
        fileHandler = new FileHandler();
        List<Customer> foundCustomers = fileHandler.searchCustomersByName(name);
        for (Customer customer : foundCustomers) {
            if (customer.getName().equals(selectedCustomer.getName()) && customer.getSurname().equals(selectedCustomer.getSurname())) {
                // System.out.println("Deleting customer...");
                fileHandler.deleteCustomer(customer);
                break;
            }
        }
    }

    /**
     * Searches for customers with a given name in the file.
     *
     * @param name The name to search for.
     * @return A list of customers matching the given name.
     */
    public List<Customer> searchCustomer(String name) {
        fileHandler = new FileHandler();
        List<Customer> foundCustomers = fileHandler.searchCustomersByName(name);
        return foundCustomers;
    }

    /**
     * Retrieves customer data for the last recorded day and returns it as a 2D array.
     *
     * @return A 2D array of customer data for the last recorded day.
     */
    public String[][] getCustomersForLastDay() {
        List<String[]> lastDayCustomers = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader("customerHistory.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            boolean isLastDayData = false;

            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith("------------------------- Day ")) {

                    isLastDayData = true;
                    lastDayCustomers.clear();
                } else if (isLastDayData && !line.trim().isEmpty()) {

                    String[] customerData = line.split(",");
                    if (customerData.length == 6) {
                        lastDayCustomers.add(customerData);
                    }
                }
            }

            bufferedReader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        String[][] result = new String[lastDayCustomers.size()][6];
        for (int i = 0; i < lastDayCustomers.size(); i++) {
            result[i] = lastDayCustomers.get(i);
        }

        Collections.reverse(lastDayCustomers);

        return result;
    }
}

