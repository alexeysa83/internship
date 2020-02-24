package com.andersenlab.aadamovich.entity.table_per_subclass;

import javax.persistence.*;

@Entity
@Table (name = "person_per_subclass")
@Inheritance(strategy = InheritanceType.JOINED)
public class PersonPerSubclass {

    private Integer id;
    private String firstName;
    private String lastName;

    public PersonPerSubclass(Integer id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name= "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
