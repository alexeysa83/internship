package com.andersenlab.aadamovich.entity.mapped_superclass.subclass;

import com.andersenlab.aadamovich.entity.mapped_superclass.PersonMapped;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "student_mapped")
public class StudentMapped extends PersonMapped {

    private String faculty;

    public StudentMapped(PersonMapped p, String faculty) {
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
