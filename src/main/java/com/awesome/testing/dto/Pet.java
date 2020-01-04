package com.awesome.testing.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "pet")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pet {

    @Id
    private int id;
    private String name;

}
