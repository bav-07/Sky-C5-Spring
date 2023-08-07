package com.qa.spring.hedgehog.domain;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
//@Table used to set the name of table you create
public class Hedgehog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = false, nullable = true)
    @NotBlank
    private String name;

    @Size(min = 1, max = 10)
    private String colour;
    @Range(min = 1, max = 20)
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hedgehog hedgehog = (Hedgehog) o;

        if (!id.equals(hedgehog.id)) return false;
        if (!name.equals(hedgehog.name)) return false;
        if (!colour.equals(hedgehog.colour)) return false;
        return age.equals(hedgehog.age);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + colour.hashCode();
        result = 31 * result + age.hashCode();
        return result;
    }
}
