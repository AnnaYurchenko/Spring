package com.example.person.persistence;

import com.example.person.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IPersonRepository extends CrudRepository<Person, Integer> {

    public List<Person> findAll();

    public List<Person> findByName(String name);

    public List<Person> findMyNextBirthdayAndPrevious(LocalDate previousBirthday, LocalDate nextBirthday);
}