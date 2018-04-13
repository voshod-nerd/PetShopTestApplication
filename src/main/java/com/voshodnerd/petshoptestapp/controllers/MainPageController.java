/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.voshodnerd.petshoptestapp.controllers;

import com.voshodnerd.petshoptestapp.model.City;
import com.voshodnerd.petshoptestapp.model.Country;
import com.voshodnerd.petshoptestapp.service.CityService;
import com.voshodnerd.petshoptestapp.service.CountryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Талалаев
 */
@Controller
public class MainPageController {

    @Autowired
    private CountryService countyService;

    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String returnIndex(ModelMap model) {

        List<Country> list = countyService.getAllCountries();
        List<City> list1 = cityService.getAllCities();
        //  list.forEach(System.out::println);

        for (Country x : list) {
            System.out.println(x.getCountry_name());
        }

        model.addAttribute("list", list);
        model.addAttribute("list1", list1);
        return "index";
    }
    
    
    
    
    @RequestMapping(value = "/city", method = RequestMethod.GET)
    public String returnTest(ModelMap model) {

        return "city";

    }
}
