package com.github.empyrosx.vacancies.web;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.github.empyrosx.vacancies.model.Vacancy;
import com.github.empyrosx.vacancies.service.VacancyService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class VacancyRestControllerTest {

    private static final String REST_URL = VacancyRestController.REST_URL + "/";

    @Mock
    private VacancyService service;

    private MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new VacancyRestController(service))
                .build();
    }

    @Test
    public void vacanciesCanBeReturned() throws Exception {
        Vacancy first = new Vacancy("Middle Java developer");
        first.setId(1L);
        first.setSalary(100000);
        first.setExperience("1-2 years");
        first.setCity("Kirov");

        List<Vacancy> vacancies = asList(first, new Vacancy("Junior"));

        doReturn(vacancies).when(service).findAll();

        // TODO: Instead of using xpath custom XML matcher may be created
        mockMvc.perform(get(REST_URL).accept(MediaType.APPLICATION_XML))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/xml;charset=UTF-8"))
                .andExpect(xpath("//vacancy").nodeCount(2))
                .andExpect(xpath("//vacancy[1]/id").string("1"))
                .andExpect(xpath("//vacancy[1]/name").string("Middle Java developer"))
                .andExpect(xpath("//vacancy[1]/salary").string("100000"))
                .andExpect(xpath("//vacancy[1]/experience").string("1-2 years"))
                .andExpect(xpath("//vacancy[1]/city").string("Kirov"))
                .andExpect(xpath("//vacancy[2]/name").string("Junior"));
    }

    @Test
    public void vacancyCanBeFoundById() throws Exception {
        doReturn(new Vacancy("Junior")).when(service).findById(2L);

        mockMvc.perform(get(REST_URL + "2").accept(MediaType.APPLICATION_XML))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/xml;charset=UTF-8"))
                .andExpect(xpath("//vacancy").nodeCount(1))
                .andExpect(xpath("//vacancy[1]/name").string("Junior"));
    }


    @Test
    public void vacancyCanBeDeleted() throws Exception {
        mockMvc.perform(delete(REST_URL + "2")
                .contentType(MediaType.APPLICATION_XML))
                .andExpect(status().isOk());

        verify(service, only()).delete(2L);
    }

    @Test
    public void vacancyCanBeAdded() throws Exception {
        Vacancy vacancy = new Vacancy("Middle Java developer");
        vacancy.setId(1L);
        vacancy.setSalary(100000);
        vacancy.setExperience("1-2 years");
        vacancy.setCity("Kirov");

        String xml = new XmlMapper().writeValueAsString(vacancy);


        mockMvc.perform(put(REST_URL)
                .contentType(MediaType.APPLICATION_XML)
                .content(xml))
                .andExpect(status().isOk());

        ArgumentCaptor<Vacancy> vacancyCaptor = ArgumentCaptor.forClass(Vacancy.class);
        verify(service, only()).save(vacancyCaptor.capture());

        Vacancy added = vacancyCaptor.getValue();
        assertThat(added, samePropertyValuesAs(vacancy));
    }
}