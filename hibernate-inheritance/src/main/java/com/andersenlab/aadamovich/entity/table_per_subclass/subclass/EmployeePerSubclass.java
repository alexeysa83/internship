package com.andersenlab.aadamovich.entity.table_per_subclass.subclass;

import com.andersenlab.aadamovich.entity.table_per_subclass.PersonPerSubclass;

import javax.persistence.*;

@Entity
@Table(name = "employee_per_subclass")
@PrimaryKeyJoinColumn (name = "person_id")
public class EmployeePerSubclass extends PersonPerSubclass {

    private Integer salary;

    public EmployeePerSubclass(PersonPerSubclass p, Integer salary) {
        super(p.getId(), p.getFirstName(), p.getLastName());
        this.salary = salary;
    }

    @Column(name = "salary")
    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }
}
