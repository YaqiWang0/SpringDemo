package com.hibernate.hibernate.demo;

import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import com.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.text.ParseException;
import java.util.Date;


public class CreateDemo {
    public static void main(String[] args) throws ParseException {

        //create sessionFactory
        SessionFactory factory=new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();
        //create session
        Session session = factory.getCurrentSession();

        try{
           //create the objects
            Instructor instructor=new Instructor("Madhu","Patel","madhu@gmail.com");

            InstructorDetail instructorDetail=new InstructorDetail("http://www.madhu.com","guitar");
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
            factory.close();
        }
    }
}
