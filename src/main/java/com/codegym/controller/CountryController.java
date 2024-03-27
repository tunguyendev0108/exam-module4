package com.codegym.controller;

import com.codegym.model.Country;
import com.codegym.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("country")
public class CountryController {
    @Autowired
    public ICountryService countryService;

    @GetMapping("")
    public ModelAndView listCountry(){
        ModelAndView modelAndView = new ModelAndView("/index");
        Iterable<Country> countries = countryService.findAll();
        modelAndView.addObject("countries",countries);
        return modelAndView;
    }
}
