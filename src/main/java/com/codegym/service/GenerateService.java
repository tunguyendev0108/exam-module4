package com.codegym.service;

import java.util.Optional;

public interface GenerateService<T> {
    Iterable<T> findAll();
    Optional<T> findById(Long id);
    void save(T t);
    void remove(Long id);
}
