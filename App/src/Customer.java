
/**
 * The Customer class represents a customer in the Aquapark Management application.
 * It stores information about the customer's name, surname, ID, duration, category,
 * description, and price.
 */
public class Customer {

    private String name;
    private String surname;
    private int id;
    private static int cntid = 0;
    private String duration;
    private String category;
    private String description;
    private String price;


    /**
     * Constructs a Customer instance with the provided information.
     *
     * @param name        The first name of the customer.
     * @param surname     The last name of the customer.
     * @param description The description of the customer.
     * @param category    The category of the customer.
     * @param duration    The duration of the customer's visit.
     * @param price       The price associated with the customer.
     */

    public Customer(String name, String surname, String description, String category, String duration, String price) {
        this.name = name;
        this.surname = surname;
        this.duration = duration;
        this.description = description;
        this.id = cntid++;
        this.category = category;
        this.price = price;
    }

    /**
     * Constructs an empty Customer instance.
     */
    public Customer() {
    }

    /**
     * Returns the description of the customer.
     *
     * @return The description of the customer.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the customer.
     *
     * @param description The new description of the customer.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the category of the customer.
     *
     * @return The category of the customer.
     */
    public String getCategory() {
        return category;
    }


    /**
     * Returns the name of the customer.
     *
     * @return The name of the customer.
     */
    public String getName() {
        return name;
    }


    /**
     * Returns a string representation of the customer.
     *
     * @return A string representation of the customer.
     */
    @Override
    public String toString() {
        return this.getName() + " " + this.getSurname() + ", " + this.getDescription() + ", " + this.getCategory() + ", " + this.getDuration() + ", " + this.getPrice();
    }

    /**
     * Returns the unique ID of the customer.
     *
     * @return The ID of the customer.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the surname of the customer.
     *
     * @return The surname of the customer.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Returns the category of the customer.
     *
     * @return The category of the customer.
     */
    public String getCustomerType() {
        return category;
    }

    /**
     * Returns the duration of the customer's visit.
     *
     * @return The duration of the customer's visit.
     */
    public String getTimeOption() {
        return duration;
    }


    /**
     * Sets the name of the customer.
     *
     * @param name The new name of the customer.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the surname of the customer.
     *
     * @param surname The new surname of the customer.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Returns the duration of the customer's visit.
     *
     * @return The duration of the customer's visit.
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Sets the duration of the customer's visit.
     *
     * @param duration The new duration of the customer's visit.
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     * Sets the category of the customer.
     *
     * @param category The new category of the customer.
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Returns the price associated with the customer.
     *
     * @return The price associated with the customer.
     */
    public String getPrice() {
        return price;
    }

}