/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.voshodnerd.petshoptestapp.model;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;


/**
 *
 * @author Талалаев
 */
public class Country implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @JsonProperty("id")
    private Long id;
     @JsonProperty("country_name")
    private String country_name;
    private List<City> cities;

    /**
     * @return the id
     */
    
   
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the country_name
     */
    public String getCountry_name() {
        return country_name;
    }

    /**
     * @param country_name the country_name to set
     */
    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    @Override
    public String toString() {
        return getId() + "," + getCountry_name();
    }

    /**
     * @return the cities
     */
    public List<City> getCities() {
        return cities;
    }

    /**
     * @param cities the cities to set
     */
    public void setCities(List<City> cities) {
        this.cities = cities;
    }

}
