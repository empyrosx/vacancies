package com.github.empyrosx.vacancies.service;

import com.github.empyrosx.vacancies.model.Vacancy;
import com.github.empyrosx.vacancies.repository.VacancyRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VacancyServiceImpl implements VacancyService {

    private final VacancyRepository repository;

    public VacancyServiceImpl(VacancyRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Vacancy> findAll() {
        return repository.findAll(new Sort(Sort.Direction.ASC, "name"));
    }

    @Override
    public Vacancy findById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Vacancy save(Vacancy vacancy) {
        return repository.save(vacancy);
    }
}
