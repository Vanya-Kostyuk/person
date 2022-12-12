package com.example.person.service;

import com.example.person.dto.PersonDTO;
import com.example.person.model.Person;
import com.example.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public PersonDTO getPersonById(long id) {
        Person person = personRepository.findById(id)
                .orElseThrow();
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(person.getId());
        personDTO.setFirstName(person.getFirstName());
        personDTO.setLastName(person.getLastName());
        personDTO.setAge(ChronoUnit.YEARS.between(person.getBirthdayDate(), LocalDate.now()));
        return personDTO;
    }

    public void updatePerson(Long id, PersonDTO PersonDTO) {
        Optional<Person> oldPerson = personRepository.findById(id);
        if (oldPerson.isPresent()) {
            Person updatedPerson = oldPerson.get();
            updatedPerson.setFirstName(PersonDTO.getFirstName());
            updatedPerson.setLastName(PersonDTO.getLastName());
            updatedPerson.setBirthdayDate(LocalDate.now().minusYears(PersonDTO.getAge()));
            personRepository.save(updatedPerson);
        }
    }

    public void deletePersonById(Long id) {
        personRepository.deleteById(id);
    }
}
