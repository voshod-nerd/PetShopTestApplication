/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.voshodnerd.petshoptestapp.service;

import com.voshodnerd.petshoptestapp.mapper.CityMapper;
import com.voshodnerd.petshoptestapp.model.City;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Талалаев
 */
@Service
public class CityService {

    @Autowired
    private CityMapper cityMapper;

    public List<City> getAllCities() {
        return cityMapper.getAllCity();
    }

    public List<City> selectWithCountry() {
        return cityMapper.selectWithCountry();
    }

    public List<City> getCity(String cityName) {
        return cityMapper.getCityByName(cityName);
    }

    @Transactional
    public void deleteCity(City el) {
        cityMapper.deleteCity(el);
    }

    @Transactional
    public void insertCity(City el) {
        cityMapper.insertCity(el);

    }

    @Transactional
    public void updateCity(City el) {
        cityMapper.updateCity(el);

    }

}
