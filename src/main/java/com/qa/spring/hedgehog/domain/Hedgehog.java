package com.qa.spring.hedgehog.domain;

public class Hedgehog {

    private String name;

    private String colour;

    private Integer age;


    public Hedgehog(String name, String colour, Integer age) {
        this.name = name;
        this.colour = colour;
        this.age = age;
    }

    // REQUIRED
    public Hedgehog() {
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
