import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The FileHandler class provides methods for saving, loading, and deleting customer data
 * to/from a file, as well as searching for customers by name in the customer history file.
 */
public class FileHandler {

    private Customer customer;

    /**
     * Constructs an empty FileHandler instance.
     */
    public FileHandler(){

    }

    /**
     * Constructs a FileHandler instance with the provided customer.
     *
     * @param customer The customer associated with this FileHandler.
     */
    public FileHandler(Customer customer) {
        this.customer = customer;
    }

    /**
     * Saves customer data to the "customerHistory.txt" file.
     */
    public void saveCustomer() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("customerHistory.txt", true))) {
            writer.write("\n");
            writer.write(customer.getName() + "," + customer.getSurname() + "," + customer.getDescription() + "," + customer.getCustomerType() + "," + customer.getTimeOption() + "," + customer.getPrice());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a customer's data from the "customerHistory.txt" file.
     *
     * @param customer The customer to be deleted.
     */
    public void deleteCustomer(Customer customer) {
        File inputFile = new File("customerHistory.txt");
        File tempFile = new File("temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;

            while ((line = reader.readLine()) != null) {
                String[] customerData = line.split(",");
                if (customerData.length == 6) {
                    String name = customerData[0].trim();
                    String surname = customerData[1].trim();

                    // Compare the name and surname to identify the customer
                    if (!name.equals(customer.getName()) || !surname.equals(customer.getSurname())) {
                        writer.write(line);
                        writer.newLine();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Delete the original file and rename the temporary file to the original file name
        if (inputFile.delete() && tempFile.renameTo(inputFile)) {
            System.out.println("Customer deleted successfully.");
        } else {
            System.err.println("Failed to delete customer.");
        }
    }


    /**
     * Searches for customers by name in the "customerHistory.txt" file.
     *
     * @param name The name to search for.
     * @return A list of customers with matching names.
     */
    public List<Customer> searchCustomersByName(String name) {
        List<Customer> matchingCustomers = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader("customerHistory.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] customerData = line.split(",");
                if (customerData.length == 6) {
                    String customerName = customerData[0].trim(); // Assuming the name is in the first column

                    if (customerName.equalsIgnoreCase(name)) {
                        // Create a Customer object and add it to the list
                        String surname = customerData[1].trim();
                        String description = customerData[2].trim();
                        String category = customerData[3].trim();
                        String timeOption = customerData[4].trim();
                        String price = customerData[5].trim();

                        Customer customer = new Customer(name, surname, description, category, timeOption, price);
                        matchingCustomers.add(customer);
                    }
                }
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return matchingCustomers;
    }


}
