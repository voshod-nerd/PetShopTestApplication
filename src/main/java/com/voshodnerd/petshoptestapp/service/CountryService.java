/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.voshodnerd.petshoptestapp.service;

import com.voshodnerd.petshoptestapp.mapper.CountryMapper;
import com.voshodnerd.petshoptestapp.model.Country;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Талалаев
 */
@Service
public class CountryService {
    
    @Autowired
    private CountryMapper countryMapper;
    
    public List<Country> getAllCountries() {
        return countryMapper.getAllCountry();
    }
    
    public List<Country> getCountry(String cityName) {
        return countryMapper.getCountryByName(cityName);
    }
    
    @Transactional
    public void insertCountry(Country el) {
        countryMapper.insertCountry(el);
        
    }
    
    @Transactional
    public void deleteCountry(Country el) {
        countryMapper.deleteCountry(el);
    }
    
    @Transactional
    public void updateCountry(Country el) {
        countryMapper.updateCountry(el);
        
    }
}
