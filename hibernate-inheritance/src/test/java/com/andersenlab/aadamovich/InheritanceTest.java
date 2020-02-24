package com.andersenlab.aadamovich;

import com.andersenlab.aadamovich.entity.mapped_superclass.PersonMapped;
import com.andersenlab.aadamovich.entity.mapped_superclass.subclass.StudentMapped;
import com.andersenlab.aadamovich.entity.single_table.PersonSingle;
import com.andersenlab.aadamovich.entity.single_table.subclass.EmployeeSingle;
import com.andersenlab.aadamovich.entity.single_table.subclass.StudentSingle;
import com.andersenlab.aadamovich.entity.table_per_subclass.PersonPerSubclass;
import com.andersenlab.aadamovich.entity.table_per_subclass.subclass.EmployeePerSubclass;
import com.andersenlab.aadamovich.entity.table_per_subclass.subclass.StudentPerSubclass;
import com.andersenlab.aadamovich.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

public class InheritanceTest {

    /*
    MAPPED SUPERCLASS

    Creates table student_mapped which contains PersonMapped and StudentMapped fields.
    The table for subclass itself is not created.
     */
    @Test
    public void mappedSuperclass() {
        final PersonMapped person = new PersonMapped(null, "Alexey", "Adamovich");
        final StudentMapped student = new StudentMapped(person, "AnderesnLab");
        try (final Session session = HibernateUtil.getSession()) {
        final Transaction transaction = session.beginTransaction();
        session.save(student);
        transaction.commit();
        }
    }

    /*
    TABLE PER SUBCLASS

    Creates three tables. Columns in "person_per_subclass" table match fields in root super class PersonPerSubclass. Any
    type of entity saved adds an entry to this table. Tables "student_per_subclass" and "employee_per_subclass" match
    subclass fields of StudentPerClass and EmployeePerClass entities respectively and in addition foreign key column with
    reference to Person table. Entries are made in those two tables only when Student or Employee entities are saved.
     */
    @Test
    public void tablePerSubclass() {
        final PersonPerSubclass person = new PersonPerSubclass(null, "Alexey", "Adamovich");
        final StudentPerSubclass student = new StudentPerSubclass(person, "AndersenLab");
        final EmployeePerSubclass employee = new EmployeePerSubclass(person, 10000);
        try(final Session session = HibernateUtil.getSession()) {
            final Transaction transaction = session.beginTransaction();
            session.save(person);
            session.save(student);
            session.save(employee);
            transaction.commit();
        }
    }

    /*
    SINGLE TABLE

    All entities saved add entries to one single table "single_table". Type of entities is distinguished through the
    value in "discriminator" column.
     */
    @Test
    public void singleTable() {
        final PersonSingle person = new PersonSingle(null, "Alexey", "Adamovich");
        final StudentSingle student = new StudentSingle(person, "AndersenLab");
        final EmployeeSingle employee = new EmployeeSingle(person, 10000);
        try (final Session session = HibernateUtil.getSession()) {
            final Transaction transaction = session.beginTransaction();
                        session.save(person);
            session.save(student);
            session.save(employee);
            transaction.commit();
        }
    }

    @AfterAll
    public static void closeFactory(){
        HibernateUtil.closeFactory();
    }
}
