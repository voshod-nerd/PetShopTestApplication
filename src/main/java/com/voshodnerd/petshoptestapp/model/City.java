/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.voshodnerd.petshoptestapp.model;

import java.io.Serializable;

/**
 *
 * @author Талалаев
 */
public class City implements Serializable {
      private static final long serialVersionUID = 1L;

    private Long id;
    private String city_name;
    private Long idcountry;
    private Country country;

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
     * @return the city_name
     */
    public String getCity_name() {
        return city_name;
    }

    /**
     * @param city_name the city_name to set
     */
    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    /**
     * @return the idcoutry
     */
    public Long getIdcountry() {
        return idcountry;
    }

    /**
     * @param idcoutry the idcoutry to set
     */
    public void setIdcountry(Long idcountry) {
        this.idcountry = idcountry;
    }

    /**
     * @return the country
     */
    public Country getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(Country country) {
        this.country = country;
    }

   
  
}
