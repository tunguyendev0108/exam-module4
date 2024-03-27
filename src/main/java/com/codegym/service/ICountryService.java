package com.codegym.service;

import com.codegym.model.Country;

public interface ICountryService {
    Iterable<Country> findAll();
}
