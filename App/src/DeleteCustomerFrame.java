import javax.swing.*;

/**
 * The DeleteCustomerFrame class represents the frame for deleting customers in the Aquapark Management application.
 */
public class DeleteCustomerFrame extends JFrame {


    private DeleteCustomerPanel deleteCustomerPanel;

    /**
     * Constructs a DeleteCustomerFrame instance.
     */
    public DeleteCustomerFrame(){
        super("Delete Customers");

        initFrameComps();
        layoutComps();

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }


    /**
     * Initializes the components of the frame.
     */
    private void initFrameComps(){

        deleteCustomerPanel = new DeleteCustomerPanel();

    }

    /**
     * Defines the layout of components within the frame.
     */
    private void layoutComps(){

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(deleteCustomerPanel);

    }


}
