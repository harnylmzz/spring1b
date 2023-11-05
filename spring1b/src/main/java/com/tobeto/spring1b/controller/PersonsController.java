package com.tobeto.spring1b.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tobeto.spring1b.model.Person;

@RestController
@RequestMapping("api/persons")
public class PersonsController {

    List<Person> inMemoryList = new ArrayList<>();

    @GetMapping("/getall")
    public List<Person> get() {
        return inMemoryList;
    }

    @GetMapping({ "getById" })
    public int getById(@RequestParam int id) {

        // lamda
        // stream api
        Person person = inMemoryList.stream()
                .filter((p) -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Kişiye ulaşılamadı."));
        return this.getById(id);
    }

    @PostMapping("/add")
    public String add(@RequestBody Person person) {
        inMemoryList.add(person);

        return person.getFirstName() + " " + person.getLastName() + " : Başarıyla eklendi!";
    }

    @PutMapping("/update")
    public String update(@RequestBody Person person) {
        Person inMemoryPerson = inMemoryList
                .stream()
                .filter((p) -> p.getId() == person.getId())
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Aradığınız kişi bulunamadı!" + person));
        inMemoryPerson.setAge(person.getAge());
        inMemoryPerson.setFirstName(person.getFirstName());
        inMemoryPerson.setLastName(person.getLastName());

        return person.getFirstName() + " " + person.getLastName() + " : Başarıyla güncellendi!";

    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam int id) {
        inMemoryList.removeIf(p -> p.getId() == id);
    }

}
