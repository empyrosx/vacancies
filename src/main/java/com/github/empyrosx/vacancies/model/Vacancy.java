package com.github.empyrosx.vacancies.model;

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
