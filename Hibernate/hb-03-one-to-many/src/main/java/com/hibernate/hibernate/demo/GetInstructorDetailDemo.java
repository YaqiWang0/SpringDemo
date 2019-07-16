package com.hibernate.hibernate.demo;

import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.text.ParseException;


public class GetInstructorDetailDemo {
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
            //start a transaction
            session.beginTransaction();

            int theId=2;
            InstructorDetail instructorDetail=session.get(InstructorDetail.class,theId);

            System.out.println("instructorDetail: "+instructorDetail);

            System.out.println("the associated instructor: "+instructorDetail.getInstructor());
            System.out.println("Done!");
        }catch(Exception exc){
            exc.printStackTrace();
        }
        finally {
            //handle the leaking issue
            session.close();
            factory.close();
        }
    }
}
