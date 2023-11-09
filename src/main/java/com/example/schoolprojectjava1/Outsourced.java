package com.example.schoolprojectjava1;


/**
 * Represents a subclass of Part called Outsourced, with the companyname property.
 */
public class Outsourced extends Part {
    /**
     * the name of the company associated with the outsourced part
     */
    private String companyName;

    /**
     * Constructs an Outsourced part with the provided attributes.
     *
     * @param id             The ID of the outsourced part.
     * @param name           The name of the outsourced part.
     * @param price          The price of the outsourced part.
     * @param inventoryLevel The current inventory level of the outsourced part.
     * @param min            The minimum allowed inventory level for the outsourced part.
     * @param max            The maximum allowed inventory level for the outsourced part.
     * @param companyName   The name of the company associated with the outsourced part.
     */
    public Outsourced(int id, String name, double price, int inventoryLevel, int min, int max, String companyName) {
        // Call the constructor of the parent class (Part) to set common attributes
        super(id, name, price, inventoryLevel, min, max);
        this.companyName = companyName; // Set the company name for the outsourced part
    }

    /**
     * Sets the company name associated with the outsourced part.
     *
     * @param companyName The name of the company.
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * Retrieves the company name associated with the outsourced part.
     *
     * @return The company name.
     */
    public String getCompanyName() {
        return companyName;
    }
}
