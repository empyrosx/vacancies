package com.github.empyrosx.vacancies;

import com.github.empyrosx.vacancies.model.Vacancy;
import com.github.empyrosx.vacancies.service.VacancyService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Demo implements CommandLineRunner {

    private final VacancyService service;

    public Demo(VacancyService service) {
        this.service = service;
    }

    @Override
    public void run(String... strings) {
        service.save(new Vacancy("Java Middle Developer"));
        service.save(new Vacancy("Java Junior Developer"));
    }
}
