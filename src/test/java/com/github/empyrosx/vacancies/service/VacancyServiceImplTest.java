package com.github.empyrosx.vacancies.service;

import com.github.empyrosx.vacancies.model.Vacancy;
import com.github.empyrosx.vacancies.repository.VacancyRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class VacancyServiceImplTest {

    @Mock
    private VacancyRepository repository;

    private VacancyService service;

    @Before
    public void init() {
        service = new VacancyServiceImpl(repository);
    }

    @Test
    public void allVacanciesAreReturned() {
        List<Vacancy> expected = new ArrayList<>();
        expected.add(new Vacancy("Junior Java Developer"));
        expected.add(new Vacancy("Middle Java Developer"));
        when(repository.findAll(Mockito.any(Sort.class))).thenReturn(expected);

        List<Vacancy> actual = service.findAll();
        assertThat(actual, is(expected));
        verify(repository).findAll(Mockito.any(Sort.class));
    }

    @Test
    public void findVacancyById() {
        Vacancy expected = new Vacancy("Junior Java Developer");
        when(repository.findOne(2L)).thenReturn(expected);

        Vacancy actual = service.findById(2L);
        assertThat(actual, is(expected));
        verify(repository).findOne(2L);
    }

    @Test
    public void vacancyCanBeSaved() throws Exception {
        Vacancy added = new Vacancy("Junior Java Developer");
        Vacancy saved = new Vacancy("Junior Java Developer");
        saved.setId(1L);
        when(repository.save(added)).thenReturn(saved);

        Vacancy actual = service.save(added);

        assertThat(actual, is(saved));
        verify(repository, only()).save(added);
    }
}