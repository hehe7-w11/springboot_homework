package com.oocl.springboot_exercise.model;
import jakarta.persistence.*;

@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    public Company(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Company() {
    }

    public Company(Integer companyId) {
        this.id = companyId;
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


}
