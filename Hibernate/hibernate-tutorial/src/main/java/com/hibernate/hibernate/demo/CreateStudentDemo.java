package com.hibernate.hibernate.demo;

import com.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.text.ParseException;
import java.util.Date;


public class CreateStudentDemo {
    public static void main(String[] args) throws ParseException {

        //create sessionFactory
        SessionFactory factory=new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        //create session
        Session session = factory.getCurrentSession();

        try{
            //create a student object
            System.out.println("Creating a new student object");

            String theDateOfBirthStr="31/12/1998";
            Date theDateOfBirth=DateUtils.parseDate(theDateOfBirthStr);
            Student tempStudent = new Student(theDateOfBirth,"Paul","Wall","paul@gmail.com");

            //start a transaction

            session.beginTransaction();
            //save the student object
            System.out.println("saving the student");
            session.save(tempStudent);
            //commit the transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }
        finally {
            factory.close();
        }
    }
}
