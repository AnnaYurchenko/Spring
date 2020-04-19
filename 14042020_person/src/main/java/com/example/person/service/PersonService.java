package com.example.person.service;

import com.example.person.dto.PersonDto;
import com.example.person.model.Person;
import com.example.person.persistence.IPersonRepository;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private static final String PERSON_NOT_FOUND = "Person not found";
    final IPersonRepository personRepository;

    public PersonService(IPersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void add(PersonDto personDto) {
        Person person = new Person(personDto.firstName, personDto.lastName, personDto.birthday);
        personRepository.save(person);
    }

    public void edit(PersonDto personDto) {
        Person person = personRepository.findById(personDto.id).orElseThrow(() -> new EntityNotFoundException(PERSON_NOT_FOUND));

        person.setName(personDto.firstName);
        person.setLastName(personDto.lastName);
        person.setBirthday(personDto.birthday);
        personRepository.save(person);
    }

    public PersonDto getById(int id) {
        Person person = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(PERSON_NOT_FOUND));
        return new PersonDto(id, person.getName(), person.getLastName(), person.getBirthday());
    }

    public void removeById(int id) {
        personRepository.deleteById(id);
    }


    public List<PersonDto> getAll() {
        List<Person> persons = personRepository.findAll();
        return persons.stream()
                .map(person -> new PersonDto(person.getId(),
                        person.getName(),
                        person.getLastName(),
                        person.getBirthday()
                ))
                .collect(Collectors.toList());
    }

    public List<PersonDto> getAllByName(String name) {
        List<Person> persons = personRepository.findByName(name);

        return persons.stream()
                .map(person -> new PersonDto(
                        person.getId(),
                        person.getName(),
                        person.getLastName(),
                        person.getBirthday()
                ))
                .collect(Collectors.toList());
    }

    public List<PersonDto> sortByBirthdayDate(int min, int max) {
        LocalDate now = LocalDate.now();
        LocalDate previousBirthday = now.minusYears(max);
        LocalDate nextBirthday = now.minusYears(min);

        return personRepository.findMyNextBirthdayAndPrevious(previousBirthday, nextBirthday).stream()
                .map(person -> new PersonDto(
                        person.getId(),
                        person.getName(),
                        person.getLastName(),
                        person.getBirthday()
                ))
                .collect(Collectors.toList());
    }
}