package com.example.person.service;

import com.example.person.dto.PersonDTO;
import com.example.person.model.Person;
import com.example.person.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
class PersonServiceTest {

    @InjectMocks
    PersonService personService;

    @Mock
    PersonRepository personRepository;

    @BeforeEach
    void init_mocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Should return person by id")
    void shouldReturnPersonById() {

        var personId = 1L;

        Person person = new Person();
        person.setId(personId);
        person.setFirstName("FirstName1");
        person.setLastName("LastName1");
        person.setBirthdayDate(LocalDate.of(2000, 9, 9));

        when(personRepository.findById(personId)).thenReturn(Optional.of(person));
        PersonDTO personById = personService.getPersonById(personId);

        PersonDTO expectedPerson = new PersonDTO();
        expectedPerson.setId(1L);
        expectedPerson.setFirstName("FirstName1");
        expectedPerson.setLastName("LastName1");
        expectedPerson.setAge(22);

        assertEquals(expectedPerson, personById);
    }

    @Test
    @DisplayName("Should return error that person not found")
    void shouldReturnErrorThatPersonNotFound() {

        var personId = 1L;

        when(personRepository.findById(personId)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> personService.getPersonById(personId));
    }
}