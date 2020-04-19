package com.example.person.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class PersonDto {

    public PersonDto(int id, String firstName, String lastName, LocalDate birthday) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
    }

    @JsonFormat(pattern = "YYYY.MM.DD")
    public LocalDate birthday;

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    @Min(0)
    public int id;

    @Size(max = 10, min = 1, message = "The name '${validatedValue}' is shorter than {min} or longer than {max}")
    public String firstName;

    @Size(min = 2, max = 20)
    public String lastName;

//    @Override
//    public String toString() {
//        return "PersonDto{" +
//                ", id=" + id +
//                ", firstName='" + firstName +
//                ", lastName='" + lastName +
//                ", age=" + age;
//    }

}