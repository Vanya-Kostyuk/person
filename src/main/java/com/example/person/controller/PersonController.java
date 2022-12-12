package com.example.person.controller;

import com.example.person.dto.PersonDTO;
import com.example.person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<PersonDTO> getPerson(@PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        PersonDTO personDTO = personService.getPersonById(id);
        if (personDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(personDTO, HttpStatus.OK);
    }

    @PostMapping("/updatePerson/{id}")
    public ResponseEntity<PersonDTO> updatePerson(@PathVariable Long id, @RequestBody PersonDTO personDTO) {
        personService.updatePerson(id, personDTO);
        return new ResponseEntity<>(personDTO, HttpStatus.OK);
    }

    @DeleteMapping("/deletePerson/{id}")
    public ResponseEntity<HttpStatus> deletePersonById(@PathVariable("id") Long id) {
        personService.deletePersonById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
