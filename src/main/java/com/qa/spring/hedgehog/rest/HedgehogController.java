package com.qa.spring.hedgehog.rest;


import com.qa.spring.hedgehog.domain.Hedgehog;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/hedgehog")
public class HedgehogController {

    private List<Hedgehog> hedgehogs = new ArrayList<>();

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World";
    }


    @PostMapping("/create")
    public ResponseEntity<Hedgehog> create(@RequestBody Hedgehog hedgehog) {
        System.out.println("RECEIVED: " + hedgehog);
        this.hedgehogs.add(hedgehog);
        Hedgehog created = this.hedgehogs.get(this.hedgehogs.size() - 1);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PostMapping("/createMultiple")
    public ResponseEntity<List<Hedgehog>> create(@RequestBody List<Hedgehog> newHedgehogs) {
        System.out.println("RECEIVED: " + newHedgehogs);
        if (this.hedgehogs.addAll(newHedgehogs)) {
            return new ResponseEntity<>(newHedgehogs, HttpStatus.CREATED);
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping("/getAll")
    public List<Hedgehog> getAll() {
        return this.hedgehogs;
    }

    @GetMapping("/get/{id}")
    public Hedgehog getById(@PathVariable int id) {
        System.out.println("ID: " + id);
        Hedgehog toRemove = this.hedgehogs.get(id);
        return toRemove;
    }

    // @RequestParam works like @PathParam but it allows you to make certain parameters mandatory
    @PatchMapping("/update/{id}")
    public Hedgehog update(
            @PathVariable int id,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "colour", required = false) String colour,
            @RequestParam(value = "age", required = false) Integer age) {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Colour: " + colour);
        System.out.println("Age: " + age);
        Hedgehog toUpdate = this.hedgehogs.get(id);
        if (name != null)
            toUpdate.setName(name);
        if (colour != null)
            toUpdate.setColour(colour);
        if (age != null)
            toUpdate.setAge(age);
            return toUpdate;
    }

    @DeleteMapping("/remove/{id}")
    public Hedgehog remove(@PathVariable int id) {
        return this.hedgehogs.remove(id);
    }

}
