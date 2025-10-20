package com.se310.store.model;

/**
 * Product class implementation representing products in the Stores associated with Inventory items
 *
 * @author  Sergey L. Sundukovskiy
 * @version 1.0
 * @since   2025-09-25
 */
public class Product {

    private String id;
    private String name;
    private String description;
    private String size;
    private String category;
    private Double price;
    private Temperature temperature;

    /**
     * Constructor for the Product class
     * @param id
     * @param name
     * @param description
     * @param size
     * @param category
     * @param price
     * @param temperature
     */
    public Product(String id, String name, String description, String size,
                   String category, Double price, Temperature temperature) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.size = size;
        this.category = category;
        this.price = price;
        this.temperature = temperature;
    }

    /**
     * Getter method for Product id
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * Setter method for Product id
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter method for Product name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for Product name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for Product description
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter method for Product descirption
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter method for Product size
     * @return
     */
    public String getSize() {
        return size;
    }

    /**
     * Setter method for Product size
     * @param size
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * Getter method for Product category
     * @return
     */
    public String getCategory() {
        return category;
    }

    /**
     * Setter method for Product category
     * @param category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Setter method for Product price
     * @return
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Getter method for Product price
     * @param price
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Getter method for Product temperature
     * @return
     */
    public Temperature getTemperature() {
        return temperature;
    }

    /**
     * Setter method for Product temperature
     * @param temperature
     */
    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", size='" + size + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", temperature=" + temperature +
                '}';
    }
}
