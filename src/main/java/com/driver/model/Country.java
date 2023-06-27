// Note: Do not write @Enumerated annotation above CountryName in this model.

package com.driver.model;

import javax.persistence.*;

@Entity

public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private CountryName countryName;
    private String code;
    @OneToOne
    @JoinColumn
    private User user;  //this field is null in case a virtual connection is made to this country

    @ManyToOne
    @JoinColumn
    private ServiceProvider serviceProvider;  //this field is non-null only when a virtual conection is made

    public Country(int id, CountryName countryName, String code, User user, ServiceProvider serviceProvider) {
        this.id = id;
        this.countryName = countryName;
        this.code = code;
        this.user = user;
        this.serviceProvider = serviceProvider;
    }

    public Country() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CountryName getCountryName() {
        return countryName;
    }

    public void setCountryName(CountryName countryName) {
        this.countryName = countryName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ServiceProvider getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }
    public void enrich(String countryName)throws Exception{
        String updateName=countryName.toUpperCase();
        if(updateName.equals("IND")){
            this.setCountryName(CountryName.IND);
            this.setCode(CountryName.IND.toCode());

        }
        else if(updateName.equals("USA")){
            this.setCountryName(CountryName.USA);
            this.setCode(CountryName.USA.toCode());

        }
        else if(updateName.equals("AUS")){
            this.setCountryName(CountryName.AUS);
            this.setCode(CountryName.AUS.toCode());

        }
        else if(updateName.equals("CHI")){
            this.setCountryName(CountryName.CHI);
            this.setCode(CountryName.CHI.toCode());

        }
        else if(updateName.equals("JPN")){
            this.setCountryName(CountryName.JPN);
            this.setCode(CountryName.JPN.toCode());

        }
        else{
            throw new Exception("Country not found");
        }
    }
}