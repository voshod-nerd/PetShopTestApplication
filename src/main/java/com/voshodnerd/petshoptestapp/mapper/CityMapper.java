/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.voshodnerd.petshoptestapp.mapper;

import com.voshodnerd.petshoptestapp.model.City;
import java.util.List;

/**
 *
 * @author Талалаев
 */

public interface CityMapper {
    
    
  public  List<City> getAllCity();  
  public  List<City> getCityByName(String cityname);
  public  List<City> selectWithCountry();
  public void deleteCity(City el);
  public  void insertCity(City el );
  public  void updateCity(City el);
    
    
}
