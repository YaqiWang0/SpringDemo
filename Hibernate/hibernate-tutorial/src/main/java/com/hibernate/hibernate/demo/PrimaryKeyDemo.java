package com.hibernate.hibernate.demo;

import com.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {

    public static void main(String[] args) {

        //create sessionFactory
        SessionFactory factory=new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        //create session
        Session session = factory.getCurrentSession();

        try{
            //create 3 student object
            System.out.println("Creating a new student object");
            Student tempStudent1 = new Student("John","Doe","John@gmail.com");
            Student tempStudent2 = new Student("Mary","Public","Mary@gmail.com");
            Student tempStudent3 = new Student("Bonita","Applebum","Bonita@gmail.com");

            //start a transaction

            session.beginTransaction();
            //save the student object
            System.out.println("saving the student");
            session.save(tempStudent1);
            session.save(tempStudent2);
            session.save(tempStudent3);
            //commit the transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }
        finally {
            factory.close();
        }

    }
}
