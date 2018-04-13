/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.voshodnerd.petshoptestapp.restcontroller;

import com.voshodnerd.petshoptestapp.service.CountryService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.voshodnerd.petshoptestapp.model.Country;
import java.util.HashMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author User
 */
@Controller
public class CountryRestController {

    @Autowired
    private CountryService countyService;

    @ResponseBody
    @RequestMapping(value = "/loadcountry", method = RequestMethod.GET, produces = "application/json")
    public Map<String, List<Country>> loadAllCountry() {
        Map<String, List<Country>> books = new HashMap<String, List<Country>>();
        books.put("books", countyService.getAllCountries());
        return books;
    }

    @RequestMapping(value = "/savecountry", method = RequestMethod.POST)
    @ResponseBody
    public boolean insertCountry(@RequestBody Country book) {
        countyService.insertCountry(book);
        return true;
    }

    @RequestMapping(value = "/deletecountry", method = RequestMethod.POST)
    @ResponseBody
    public boolean deleteCountry(@RequestBody Country book) {
        countyService.deleteCountry(book);
        return true;
    }

    @RequestMapping(value = "/updatecountry", method = RequestMethod.POST)
    @ResponseBody
    public boolean updateBooks(@RequestBody Country book) {
        System.out.println(book.getCountry_name());
        countyService.updateCountry(book);
        return true;
    }

}
