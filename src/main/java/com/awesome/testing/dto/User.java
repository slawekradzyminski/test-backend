package com.awesome.testing.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "users")
@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "username")
    private String userName;
    private int salary;
    private int age;

    public User(String firstName, String lastName, String userName, int salary, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.salary = salary;
        this.age = age;
    }

}
