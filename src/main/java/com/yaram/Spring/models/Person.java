package com.yaram.Spring.models;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {

    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 25, message = "Name should has at least 2 symbols and less then 50")
    private String name;

    @Email(message = "Email should be valid")
    @NotEmpty(message = "Name should not be empty")
    private String email;

    public Person(){
        //Пустой конструктор для thymeleaf
    }

    public Person(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}





}
