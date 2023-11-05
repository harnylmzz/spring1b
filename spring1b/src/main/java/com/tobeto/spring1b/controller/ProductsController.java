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

import com.tobeto.spring1b.model.Product;

@RestController
@RequestMapping("/api/products")
public class ProductsController {

    List<Product> inMemoryList = new ArrayList<>();

    @GetMapping("/getall")
    public List<Product> get() {
        return inMemoryList;
    }

    @GetMapping({ "getById" })
    public int getById(@RequestParam int id) {
        Product product = inMemoryList.stream()
                .filter((p) -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Araduğınız ürün bulunamadı!"));
        return id;

    }

    @PostMapping("/api/add")
    public String add(Product product) {
        inMemoryList.add(product);

        return product.getName() + " : Başarıyla eklendi!";

    }

    @PutMapping("/api/update")
    public String update(Product product) {
        Product inMemoryProduct = inMemoryList.stream()
                .filter((p) -> p.getId() == product.getId())
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Aradığınız ürün bulunamadı!" + product));

        inMemoryProduct.setId(inMemoryProduct.getId());
        inMemoryProduct.setName(inMemoryProduct.getName());
        inMemoryProduct.setPrice(inMemoryProduct.getPrice());
        return product.getName() + "Ürün başarıyla güncellendi.";

    }

    @DeleteMapping("/api/delete")
    public void delete(@RequestParam int id) {
        inMemoryList.removeIf(p -> p.getId() == id);
    }
}
