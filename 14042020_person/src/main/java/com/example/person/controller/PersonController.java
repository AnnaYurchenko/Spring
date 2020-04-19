package com.example.person.controller;

import com.example.person.dto.PersonDto;
import com.example.person.service.PersonService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController // all methods will return json. We dont need ResponceBody
@Validated
public class PersonController {

    PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/person")
    public void createPerson(@RequestBody @Valid PersonDto personDto) {
        personService.add(personDto);
    }

    @PutMapping("/person")
    public void editPerson(@RequestBody @Valid PersonDto personDto) {
        personService.edit(personDto);
    }

    @GetMapping("/person")
    public List<PersonDto> getAll() {
        return personService.getAll();
    }

    @GetMapping("/person/{id}")
    public PersonDto getPersonById(@PathVariable @Min(1) int id) {
        return personService.getById(id);
    }

    @GetMapping("/person/name/{name}")
    public List<PersonDto> getAllByName(@PathVariable String name) {
        return personService.getAllByName(name);
    }

    @DeleteMapping("/person/{id}")
    public void removePersonById(@PathVariable int id) {
        personService.removeById(id);
    }

    @GetMapping("/person")
    public List<PersonDto> sortByBirthdayDate(
            @RequestParam(required = false, defaultValue = "0") int min,
            @RequestParam(required = false, defaultValue = "" + Integer.MAX_VALUE) int max) {
        return personService.sortByBirthdayDate(min, max);
    }
}