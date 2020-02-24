package com.andersenlab.aadamovich.entity.table_per_subclass.subclass;

import com.andersenlab.aadamovich.entity.table_per_subclass.PersonPerSubclass;
import org.hibernate.annotations.Polymorphism;

import javax.persistence.*;

@Entity
@Table(name = "student_per_subclass")
@PrimaryKeyJoinColumn(name = "person_id")
public class StudentPerSubclass extends PersonPerSubclass {

    private String faculty;

    public StudentPerSubclass(PersonPerSubclass p, String faculty) {
        super(p.getId(), p.getFirstName(), p.getLastName());
        this.faculty = faculty;
    }

    @Column(name = "faculty")
    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
}
