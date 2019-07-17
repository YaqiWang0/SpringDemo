package com.hibernate.hibernate.demo;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.text.ParseException;


public class CreateInstructorDemo {
    public static void main(String[] args) throws ParseException {

        //create sessionFactory
        SessionFactory factory=new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();
        //create session
        Session session = factory.getCurrentSession();

        try{
           //create the objects
            Instructor instructor=new Instructor("Susan","Public","susan.public@gmail.com");

            InstructorDetail instructorDetail=new InstructorDetail("http://www.susam.com","video games");
            //associate the objects
            instructor.setInstructorDetail(instructorDetail);
            //start a transaction
            session.beginTransaction();

            session.save(instructor);
            //commit the transaction
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
