package com.test.service;

import com.test.dto.PersonDto;
import com.test.entity.Person;
import com.test.singleton.PersonsHolder;

import java.util.Collection;

/**
 * Created by victor on 03.09.15.
 */
public interface PersonService {
    public void savePerson(PersonDto personDto);
    public Person getPerson(Long id);
    public boolean updatePerson(Long id,PersonDto personDto);
    public Collection<Person> getAll();
}
