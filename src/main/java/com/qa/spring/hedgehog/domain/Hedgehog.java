package com.qa.spring.hedgehog.domain;

import javax.persistence.*;

@Entity
//@Table used to set the name of table you create
public class Hedgehog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = false, nullable = true)
    private String name;

    private String colour;

    private Integer age;

    public Hedgehog(Integer id, String name, String colour, Integer age) {
        this.id = id;
        this.name = name;
        this.colour = colour;
        this.age = age;
    }

    public Hedgehog(String name, String colour, Integer age) {
        this.name = name;
        this.colour = colour;
        this.age = age;
    }

    // REQUIRED
    public Hedgehog() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Hedgehog{" +
                "name='" + name + '\'' +
                ", colour='" + colour + '\'' +
                ", age=" + age +
                '}';
    }
}
