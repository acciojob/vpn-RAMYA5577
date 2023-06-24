package com.driver.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
   private String password;
    private String originalIP;
    private String maskedIP;
    private boolean connected;

    @ManyToMany
    @JoinColumn
    List<ServiceProvider> serviceProviderList;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<Connection> connectionList;
    @OneToOne
    @JoinColumn
    Country originalCountry;

    public User(int id, String username, String password, String originalIP, String maskedIP,
                boolean connected, List<ServiceProvider> serviceProviderList, List<Connection> connectionList,
                Country country) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.originalIP = originalIP;
        this.maskedIP = maskedIP;
        this.connected = connected;
        this.serviceProviderList = serviceProviderList;
        this.connectionList = connectionList;
        this.originalCountry = country;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passWord) {
        this.password = password;
    }

    public String getOriginalIP() {
        return originalIP;
    }

    public void setOriginalIP(String originalIP) {
        this.originalIP = originalIP;
    }

    public String getMaskedIP() {
        return maskedIP;
    }

    public void setMaskedIP(String maskedIP) {
        this.maskedIP = maskedIP;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public List<ServiceProvider> getServiceProviderList() {
        return serviceProviderList;
    }

    public void setServiceProviderList(List<ServiceProvider> serviceProviderList) {
        this.serviceProviderList = serviceProviderList;
    }

    public List<Connection> getConnectionList() {
        return connectionList;
    }

    public void setConnectionList(List<Connection> connectionList) {
        this.connectionList = connectionList;
    }

    public Country getCountry() {
        return originalCountry;
    }

    public void setCountry(Country country) {
        this.originalCountry = country;
    }
}
