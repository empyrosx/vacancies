package com.github.empyrosx.vacancies.service;

import com.github.empyrosx.vacancies.model.Vacancy;

import java.util.List;

public interface VacancyService {

    List<Vacancy> findAll();

    Vacancy findById(Long id);

    Vacancy save(Vacancy vacancy);
}
