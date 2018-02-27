package com.github.empyrosx.vacancies.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@JacksonXmlRootElement(localName = "vacancy")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Vacancy {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String name;

    private Integer salary;

    private String experience;

    private String city;
}
