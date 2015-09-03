package com.test.singleton;

import com.test.dto.PersonDto;
import com.test.entity.Person;

import javax.ejb.*;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by victor on 03.09.15.
 */
@Singleton(name = "personsHolder")
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class PersonsHolder {
    private AtomicLong idCounter = new AtomicLong();

    private long createId() {
        return idCounter.getAndIncrement();
    }
    private ConcurrentHashMap<Long,Person> persons = new ConcurrentHashMap<Long, Person>();
    public Person getPerson(Long userId) {
        return persons.get(userId);
    }
    public void savePerson(PersonDto personDto) {
        Person person = new Person();
        person.setName(personDto.getName());
        person.setBirthDate(personDto.getBirthDate());
        person.setPatronymic(personDto.getPatronymic());
        person.setSurname(personDto.getSurname());
        person.setId(createId());
        persons.put(person.getId(),person);
    }
    public boolean updatePerson(PersonDto personDto,Long id) {
        Person person = persons.get(id);
        if (person == null) {
            return false;
        }
        synchronized (person) {
            person.setName(personDto.getName());
            person.setBirthDate(personDto.getBirthDate());
            person.setPatronymic(personDto.getPatronymic());
            person.setSurname(personDto.getSurname());
        }
        return true;
    }

    public Collection<Person> getAll() {
        return persons.values();
    }
}
