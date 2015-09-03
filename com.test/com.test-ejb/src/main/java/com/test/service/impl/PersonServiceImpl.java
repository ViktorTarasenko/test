package com.test.service.impl;

import com.test.dto.PersonDto;
import com.test.entity.Person;
import com.test.service.PersonService;
import com.test.singleton.PersonsHolder;

import javax.ejb.DependsOn;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Collection;
import java.util.List;

/**
 * Created by victor on 03.09.15.
 */
@Stateless(name = "userService")
public class PersonServiceImpl implements PersonService{
    @Inject
    private PersonsHolder personsHolder;
    public void savePerson(PersonDto personDto){
        personsHolder.savePerson(personDto);
    }
    public Person getPerson(Long id){
        return personsHolder.getPerson(id);
    }
    public boolean updatePerson(Long id,PersonDto personDto) {
        return personsHolder.updatePerson(personDto, id);
    }
    public Collection<Person> getAll() {
        return personsHolder.getAll();
    }
}
