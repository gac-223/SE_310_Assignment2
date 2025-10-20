package com.se310.store.model;

import java.util.Date;

/**
 * Customer class implementation representing Customer in the Store
 *
 * @author  Sergey L. Sundukovskiy
 * @version 1.0
 * @since   2025-09-25
 */
public class Customer {

    private String id;
    private String firstName;
    private String lastName;
    private CustomerType type;
    private String email;
    private String accountAddress;
    private CustomerAgeGroup ageGroup;
    private StoreLocation storeLocation;
    private Date lastSeen;
    private Basket basket;

    /**
     * Constructor for Customer class
     * @param id
     * @param firstName
     * @param lastName
     * @param type
     * @param email
     * @param accountAddress
     */
    public Customer(String id, String firstName, String lastName, CustomerType type, String email,
                    String accountAddress) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
        this.email = email;
        this.accountAddress = accountAddress;
    }

    /**
     * Getter method for Customer id
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * Setter method for Customer id
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter method for Customer's first name
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter method for Customer's first name
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter method for Customer's last name
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter method for Customer's last name
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter method for Customer's type
     * @return
     */
    public CustomerType getType() {
        return type;
    }

    /**
     * Setter method for Customer's type
     * @param type
     */
    public void setType(CustomerType type) {
        this.type = type;
    }

    /**
     * Getter method for Customer's email
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter method for Customer's email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter method for Customer's account address
     * @return
     */
    public String getAccountAddress() {
        return accountAddress;
    }

    /**
     * Setter method for Customer's account address
     * @param accountAddress
     */
    public void setAccountAddress(String accountAddress) {
        this.accountAddress = accountAddress;
    }

    /**
     * Getter method for Customer's age group
     * @return
     */
    public CustomerAgeGroup getAgeGroup() {
        return ageGroup;
    }

    /**
     * Setter method for Customer's age group
     * @param ageGroup
     */
    public void setAgeGroup(CustomerAgeGroup ageGroup) {
        this.ageGroup = ageGroup;
    }

    /**
     * Method for retrieving location of the Customer
     * @return
     */
    public StoreLocation getStoreLocation() {
        return storeLocation;
    }

    /**
     * Method for recording location of the Customer
     * @param storeLocation
     */
    public void setStoreLocation(StoreLocation storeLocation) {
        this.storeLocation = storeLocation;
    }

    /**
     * Method retrieving timestamp when Customer was last seen
     * @return
     */
    public Date getLastSeen() {
        return lastSeen;
    }

    /**
     * Method recording timestamp when Customer was last seen
     * @return
     */
    public void setLastSeen(Date lastSeen) {
        this.lastSeen = lastSeen;
    }

    /**
     * Method for retrieving Customer's Basket
     * @return
     */
    public Basket getBasket() {
        return basket;
    }

    /**
     * Method for assigning Basket to the Customer
     * @param basket
     */
    public void assignBasket(Basket basket) {
        this.basket = basket;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", type=" + type +
                ", email='" + email + '\'' +
                ", accountAddress='" + accountAddress + '\'' +
                ", ageGroup=" + ageGroup +
                ", storeLocation=" + storeLocation +
                ", lastSeen=" + lastSeen +
                ", basketId=" + basket +
                '}';
    }
}
