/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.voshodnerd.petshoptestapp.restcontroller;

import com.voshodnerd.petshoptestapp.model.City;
import com.voshodnerd.petshoptestapp.service.CityService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Талалаев
 */
@Controller
public class CityRestContoller {

    @Autowired
    private CityService cityService;

    @ResponseBody
    @RequestMapping(value = "/loadcity", method = RequestMethod.GET, produces = "application/json")
    public Map<String, List<City>> loadAllCity() {
        Map<String, List<City>> books = new HashMap<String, List<City>>();
        books.put("books", cityService.selectWithCountry());
        return books;
    }

    @RequestMapping(value = "/savecity", method = RequestMethod.POST)
    @ResponseBody
    public boolean insertCountry(@RequestBody City book) {
        cityService.insertCity(book);
        return true;
    }

    @ResponseBody
    @RequestMapping(value = "/deletecity", method = RequestMethod.POST)
    public boolean deleteCity(@RequestBody City book) {
        cityService.deleteCity(book);
        return true;
    }

    @ResponseBody
    @RequestMapping(value = "/updatecity", method = RequestMethod.POST)
    public boolean updateCity(@RequestBody City city) {

        cityService.updateCity(city);
        return true;
    }
}
