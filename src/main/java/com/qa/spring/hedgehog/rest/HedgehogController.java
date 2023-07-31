package com.qa.spring.hedgehog.rest;


import com.qa.spring.hedgehog.domain.Hedgehog;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
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
        Hedgehog created= this.hedgehogs.get(this.hedgehogs.size() - 1);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PostMapping("/createMultiple")
    public Hedgehog create(@RequestBody List<Hedgehog> newHedgehogs) {

        System.out.println("RECEIVED: " + newHedgehogs);
        return null;
    }


    @GetMapping("/getAll")
    public List<Hedgehog> getAll() {
        return null;
    }

    @GetMapping("/get/{id}")
    public Hedgehog getById(@PathVariable Integer id) {
        System.out.println("ID: " + id);
        return null;
    }

    @PatchMapping("/update/{id}")
    public Hedgehog update(
            @PathVariable Integer id,
            @PathParam("name") String name,
            @PathParam("colour") String colour,
            @PathParam("age") Integer age) {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Colour: " + colour);
        System.out.println("Age: " + age);

        return null;
    }

    @DeleteMapping("/remove/{id}")
    public Hedgehog remove(@PathVariable Integer id) {
        return null;
    }

}
