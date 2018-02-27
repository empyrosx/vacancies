package com.github.empyrosx.vacancies.web;

import com.github.empyrosx.vacancies.model.Vacancy;
import com.github.empyrosx.vacancies.service.VacancyService;
import com.github.empyrosx.vacancies.web.to.Vacancies;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = VacancyRestController.REST_URL)
public class VacancyRestController {

    static final String REST_URL = "/vacancy";

    private final VacancyService service;

    public VacancyRestController(VacancyService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    public Vacancies findAll() {
        List<Vacancy> result = service.findAll();
        return new Vacancies(result);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE
            , path = "/{id}")
    public Vacancy findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_XML_VALUE)
    public void create(@RequestBody Vacancy vacancy) {
        service.save(vacancy);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }
}
