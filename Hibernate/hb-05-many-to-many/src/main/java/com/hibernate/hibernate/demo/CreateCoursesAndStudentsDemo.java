package com.hibernate.hibernate.demo;

import com.hibernate.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.text.ParseException;


public class CreateCoursesAndStudentsDemo {
    public static void main(String[] args) throws ParseException {

        //create sessionFactory
        SessionFactory factory=new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        //create session
        Session session = factory.getCurrentSession();

        try{
            //start a transaction
            session.beginTransaction();

            //create a course
            Course course=new Course("Pacman-How to score one million point");


            //save th course .. and leverage the cascade all :-)
            session.save(course);
            //commit the transaction

            //create the students
            Student student1=new Student("John","Doe","John@gmail.com");
            Student student2=new Student("Mary","Public","Mary@gmail.com");
            //add students to the course
            course.addStudent(student1);
            course.addStudent(student2);

            //save the students
            session.save(student1);
            session.save(student2);

            //save the students
            session.getTransaction().commit();

            System.out.println("Done!");
        }
        finally {
            //add clean up code
            session.close();
            factory.close();
        }
    }
}
