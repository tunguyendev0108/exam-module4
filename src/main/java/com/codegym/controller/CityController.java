package com.codegym.controller;

import com.codegym.model.City;
import com.codegym.model.Country;
import com.codegym.service.ICityService;
import com.codegym.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/cities")
public class CityController {
    @Autowired
    private ICityService cityService;
    @Autowired
    private ICountryService countryService;

    @ModelAttribute("country")
    public Iterable<Country> listCountry() {
        return countryService.findAll();
    }

    @GetMapping
    public ModelAndView list(){
        ModelAndView modelAndView = new ModelAndView("/index");
        Iterable<City> cities = cityService.findAll();
        modelAndView.addObject("cities", cities);
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView showCreate(){
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("cities", new City());
        return modelAndView;
    }
    @PostMapping("/create")
    public String create(@ModelAttribute("city") City city){
        cityService.save(city);
        return "redirect:/cities";
    }
    @GetMapping("/edit/{id}")
    public ModelAndView showEdit(@PathVariable Long id){
        Optional<City> cityOptional = cityService.findById(id);
        if (cityOptional.isPresent()){
            ModelAndView modelAndView = new ModelAndView("/edit");
            modelAndView.addObject("city", cityOptional.get());
            return modelAndView;
        }
        return new ModelAndView("error_404");
    }
    @PostMapping("/edit/{id}")
    public String edit(@ModelAttribute City city){
        cityService.save(city);
        return "redirect:/cities";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        Optional<City> cityOptional = cityService.findById(id);
        if (cityOptional.isPresent()){
            cityService.remove(id);
            return "redirect:/cities";
        }
        return "redirect:/error_404";
    }
}