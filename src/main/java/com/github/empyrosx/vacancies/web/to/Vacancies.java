package com.github.empyrosx.vacancies.web.to;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.github.empyrosx.vacancies.model.Vacancy;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@JacksonXmlRootElement(localName = "vacancies")
public class Vacancies {

    @JacksonXmlElementWrapper(useWrapping=false)
    @JacksonXmlProperty(localName = "vacancy")
    private List<Vacancy> vacancies;
}
