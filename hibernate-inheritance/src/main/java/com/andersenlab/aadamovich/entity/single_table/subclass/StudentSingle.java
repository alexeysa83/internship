package com.andersenlab.aadamovich.entity.single_table.subclass;

import com.andersenlab.aadamovich.entity.single_table.PersonSingle;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Student")
public class StudentSingle extends PersonSingle {

    private String faculty;

    public StudentSingle(PersonSingle p, String faculty) {
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
