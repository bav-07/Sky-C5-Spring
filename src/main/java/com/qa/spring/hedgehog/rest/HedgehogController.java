package com.qa.spring.hedgehog.rest;


import com.qa.spring.hedgehog.domain.Hedgehog;
import com.qa.spring.hedgehog.services.HedgehogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hedgehog")
public class HedgehogController {

    private HedgehogService service;

    public HedgehogController(HedgehogService service) {
        this.service = service;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World";
    }


    @PostMapping("/create")
    public ResponseEntity<Hedgehog> create(@RequestBody Hedgehog hedgehog) {
        return new ResponseEntity<>(this.service.create(hedgehog), HttpStatus.CREATED);
    }

    @PostMapping("/createMultiple")
    public ResponseEntity<List<Hedgehog>> create(@RequestBody List<Hedgehog> newHedgehogs) {
        System.out.println("RECEIVED: " + newHedgehogs);
        if (this.service.create(newHedgehogs) != null) {
            return new ResponseEntity<>(newHedgehogs, HttpStatus.CREATED);
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping("/getAll")
    public List<Hedgehog> getAll() {
        return this.service.getAll();
    }

    @GetMapping("/get/{id}")
    public Hedgehog getById(@PathVariable int id) {
        return this.service.getById(id);
    }

    // @RequestParam works like @PathParam but it allows you to make certain parameters mandatory
    @PatchMapping("/update/{id}")
    public Hedgehog update(@PathVariable int id, @RequestParam(value = "name", required = false) String name, @RequestParam(value = "colour", required = false) String colour, @RequestParam(value = "age", required = false) Integer age) {
        return this.service.update(id, name, colour, age);

    }

    @DeleteMapping("/remove/{id}")
    public Hedgehog remove(@PathVariable int id) {
        return this.service.remove(id);
    }

    @GetMapping("/findByName/{name}")
    public List<Hedgehog> findByName(@PathVariable String name) {
        return this.service.findByName(name);
    }

    @GetMapping("/findAgeByName/{name}")
    public Integer findAgeByName(@PathVariable String name) {
        return this.service.findAgeByName(name);
    }
}
