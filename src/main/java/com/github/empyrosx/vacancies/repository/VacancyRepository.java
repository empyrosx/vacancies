package com.github.empyrosx.vacancies.repository;

import com.github.empyrosx.vacancies.model.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {


}
