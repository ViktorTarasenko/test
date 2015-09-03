package com.test.validator;

import com.test.dto.PersonDto;

import javax.ejb.Stateless;

/**
 * Created by victor on 03.09.15.
 */
@Stateless(name = "PersonValidator")
public class PersonValidator {
    public boolean validate(PersonDto dto){
        return ((dto.getName() != null) && (dto.getSurname() != null) && (dto.getPatronymic() != null) && (dto.getBirthDate() !=null) &&
                (!dto.getName().isEmpty()) && (!dto.getSurname().isEmpty()) && (!dto.getPatronymic().isEmpty()));
    }
}
