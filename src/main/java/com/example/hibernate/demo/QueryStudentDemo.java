package com.example.hibernate.demo;

import com.example.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {
    public static void main(String[] args) {

        // create session factory

        SessionFactory factory = new Configuration()
                .configure("/hibernate.cfg.xml").
                addAnnotatedClass(Student.class).
                buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // use the session object to save Java object
            // start a transaction
            session.beginTransaction();

            // NEW CODE HERE

            // Query students
            List<Student> theStudents = session.createQuery("from Student").list();

            // display students
            displayStudents(theStudents);
            // query students: lastName='Doe'
            theStudents = session.createQuery("from Student s where s.lastName='Doe'").list();

            // display the students
            System.out.println("Students with last name Doe");
            displayStudents(theStudents);
            // commit transaction
            session.getTransaction().commit();
        } finally {
            factory.close();
        }

    }

    private static void displayStudents(List<Student> theStudents) {
        for (Student tempStudent: theStudents) {
            System.out.println(tempStudent);
        }
    }
}
