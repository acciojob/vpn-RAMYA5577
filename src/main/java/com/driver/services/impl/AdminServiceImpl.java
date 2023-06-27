package com.driver.services.impl;

import com.driver.Exceptions.IdNotPresentException;
import com.driver.model.*;
import com.driver.repository.AdminRepository;
import com.driver.repository.CountryRepository;
import com.driver.repository.ServiceProviderRepository;
import com.driver.repository.UserRepository;
import com.driver.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminRepository adminRepository1;

    @Autowired
    ServiceProviderRepository serviceProviderRepository1;

//    @Autowired
//    CountryRepository countryRepository1;
//
//    @Autowired
//    UserRepository userRepository;

    @Override
    public Admin register(String username, String password) {
        Admin admin=new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        admin.setServiceProviders(new ArrayList<>());

        adminRepository1.save(admin);
        return admin;
    }

    @Override
    public Admin addServiceProvider(int adminId, String providerName) {

        Admin admin=adminRepository1.findById(adminId).get();
        List<ServiceProvider> serviceProviderList=admin.getServiceProviders();

        ServiceProvider serviceProvider=new ServiceProvider();
        serviceProvider.setName(providerName);
        serviceProvider.setCountryList(new ArrayList<>());
        serviceProvider.setUsers(new ArrayList<>());
        serviceProvider.setAdmin(admin);
        serviceProviderList.add(serviceProvider);
        admin.setServiceProviders(serviceProviderList);
        adminRepository1.save(admin);
        return admin;
    }

    @Override
    public ServiceProvider addCountry(int serviceProviderId, String countryName) throws Exception{
        //add a country under the serviceProvider and return respective service provider
        //country name would be a 3-character string out of ind, aus, usa, chi, jpn. Each character can be
        // in uppercase or lowercase. You should create a new Country object based on the given country name and
        // add it to the country list of the service provider.
        // Note that the user attribute of the country in this case would be null.
        //In case country name is not amongst the above mentioned strings, throw "Country not found" exception

        ServiceProvider serviceProvider=serviceProviderRepository1.findById(serviceProviderId).get();
        List<Country> countryList=serviceProvider.getCountryList();
        Country country=new Country();
        country.enrich(countryName);

        country.setServiceProvider(serviceProvider);
        countryList.add(country);
        serviceProvider.setCountryList(countryList);
        serviceProviderRepository1.save(serviceProvider);
        return serviceProvider;
    }
}
