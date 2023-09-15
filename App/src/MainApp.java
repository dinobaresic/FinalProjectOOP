import javax.swing.*;

public class MainApp {
    public static void main(String[] args) {

        //TST1 passed

        /*
        Customer customer = new Customer("John", "Doe", "Adult", "30");
        Customer customer1 = new Customer("John", "Majid", "Child", "60");
        customer.setDescription("Adult of child customer");
        customer1.setDescription("Child customer");
        System.out.println(customer);

         */

        SwingUtilities.invokeLater(() -> {
            new WelcomeMainFrame();
        });



    }
}
