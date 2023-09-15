import java.util.EventObject;


/**
 * The AddCustomersPanelEvent class represents an event that occurs when customer information is submitted
 * in the AddCustomersPanel of the Aquapark Management application.
 */
public class AddCustomersPanelEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */

    private String name;
    private String surname;

    private String description;
    private String category;

    private String timeOption;

    private String price;

    /**
     * Constructs an AddCustomersPanelEvent.
     *
     * @param source      The object on which the event initially occurred.
     * @param name        The customer's name.
     * @param surname     The customer's surname.
     * @param description The customer's description.
     * @param category    The customer's category (e.g., Adult or Child).
     * @param timeOption  The selected time option (e.g., 30 minutes, 1 hour, 1 day).
     * @param price       The price associated with the selected time option.
     */
    public AddCustomersPanelEvent(Object source, String name, String surname, String description, String category, String timeOption, String price) {
        super(source);
        this.name = name;
        this.surname = surname;
        this.description = description;
        this.category = category;
        this.timeOption = timeOption;
        this.price = price;

    }

    /**
     * Gets the customer's name.
     *
     * @return The customer's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the customer's surname.
     *
     * @return The customer's surname.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Gets the customer's description.
     *
     * @return The customer's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the customer's category (e.g., Adult or Child).
     *
     * @return The customer's category.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Gets the selected time option (e.g., 30 minutes, 1 hour, 1 day).
     *
     * @return The selected time option.
     */
    public String getTimeOption() {
        return timeOption;
    }

    /**
     * Gets the price associated with the selected time option.
     *
     * @return The price associated with the selected time option.
     */
    public String getPrice() {
        return price;
    }



}
