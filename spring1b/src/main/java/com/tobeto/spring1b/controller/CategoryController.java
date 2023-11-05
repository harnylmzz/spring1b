package com.tobeto.spring1b.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tobeto.spring1b.model.Category;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    List<Category> inMemoryList = new ArrayList<>();

    @GetMapping("/getall")
    public List<Category> get() {
        return inMemoryList;
    }

    @GetMapping({ "getById" })
    public int getById(int id) {
        Category category = inMemoryList.stream()
                .filter((p -> p.getId() == id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Kişiye ulaşılamadı."));

        return this.getById(id);
    }

    @PostMapping("/api/add")
    public String add(@RequestParam Category category) {
        inMemoryList.add(category);

        return category.getName() + " : Başarıyla eklendi!";
    }

    @PutMapping("/api/update")
    public String update(@RequestParam Category category) {
        Category inMemoryCategory = inMemoryList.stream()
                .filter((p -> p.getId() == category.getId()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Aradığınız kategori bulunamadı!" + category));
        inMemoryCategory.setId(category.getId());
        inMemoryCategory.setName(category.getName());

        return category.getName() + " : Başarıyla güncellendi!";

    }

    @DeleteMapping("/api/delete")
    public void delete(@RequestParam int id) {
        Category category = inMemoryList.stream()
                .filter((p -> p.getId() == id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Aradığınız kategori bulunamadı!" + id));
        inMemoryList.remove(category);
    }

}
