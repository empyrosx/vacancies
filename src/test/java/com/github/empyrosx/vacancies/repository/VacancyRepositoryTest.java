package com.github.empyrosx.vacancies.repository;

import com.github.database.rider.core.DBUnitRule;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.empyrosx.vacancies.model.Vacancy;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class VacancyRepositoryTest {

    @Autowired
    private VacancyRepository repository;

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Rule
    public DBUnitRule dbUnitRule = DBUnitRule.instance(() -> jdbcTemplate.getDataSource().getConnection());

    @Test
    @DataSet("vacancies.xml")
    public void ifVacanciesAreFoundTheyAreReturned() {
        List<Vacancy> actual = repository.findAll();
        Vacancy first = new Vacancy("Middle Java developer");
        first.setId(1L);
        first.setSalary(100000);
        first.setExperience("1-2 years");
        first.setCity("Kirov");

        Vacancy second = new Vacancy("Junior Java developer");
        second.setId(2L);
        second.setSalary(90000);
        second.setExperience("1-2 years");
        second.setCity("Moscow");

        assertThat(actual, hasItems(samePropertyValuesAs(first), samePropertyValuesAs(second)));
    }

    @Test
    @DataSet("vacancies.xml")
    public void findVacancyById() throws Exception {
        Vacancy actual = repository.findOne(2L);

        Vacancy expected = new Vacancy("Junior Java developer");
        expected.setId(2L);
        expected.setSalary(90000);
        expected.setExperience("1-2 years");
        expected.setCity("Moscow");

        assertThat(actual, samePropertyValuesAs(expected));
    }

    @Test
    @DataSet("vacancies.xml")
    @ExpectedDataSet("vacancies_after_add.xml")
    @Commit
    public void vacancyMayBeAdded() {
        Vacancy vacancy = new Vacancy("Python developer");
        vacancy.setSalary(50000);
        vacancy.setExperience("1-2 years");
        vacancy.setCity("Moscow");
        repository.save(vacancy);
    }

    @Test
    @DataSet("vacancies.xml")
    @ExpectedDataSet("vacancies_after_delete.xml")
    @Commit
    public void vacancyMayBeDeleted() {
        repository.delete(2L);
    }

    @Test
    @DataSet("vacancies.xml")
    public void getSortedVacancies() throws Exception {
        List<Vacancy> vacancies = repository.findAll(new Sort(Sort.Direction.ASC, "name"));
        assertThat(vacancies, hasSize(2));
        assertThat(vacancies.get(0).getName(), equalTo("Junior Java developer"));
        assertThat(vacancies.get(1).getName(), equalTo("Middle Java developer"));
    }
}
