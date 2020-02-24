package com.andersenlab.aadamovich.entity.single_table.subclass;

import com.andersenlab.aadamovich.entity.single_table.PersonSingle;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Employee")
public class EmployeeSingle extends PersonSingle {

    private Integer salary;

    public EmployeeSingle(PersonSingle p, Integer salary) {
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
