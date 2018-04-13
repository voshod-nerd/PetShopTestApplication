/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.voshodnerd.petshoptestapp.mapper;

import com.voshodnerd.petshoptestapp.model.Country;
import java.util.List;

/**
 *
 * @author Талалаев
 */
public interface CountryMapper {

    public List<Country> getAllCountry();

    public List<Country> getCountryByName(String cityname);

    public void insertCountry(Country el);
    public void deleteCountry(Country el);

    public void updateCountry(Country el);
}
