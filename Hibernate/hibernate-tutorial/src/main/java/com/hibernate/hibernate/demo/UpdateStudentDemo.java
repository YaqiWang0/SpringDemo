package com.hibernate.hibernate.demo;

import com.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class UpdateStudentDemo {
    public static void main(String[] args) {

        //create sessionFactory
        SessionFactory factory=new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        //create session
        Session session = factory.getCurrentSession();

        try{
           int studentId=1;

            //now get a new session and start transaction
            session=factory.getCurrentSession();
            session.beginTransaction();

            //retrieve student based on the id: primary key
            System.out.println("\nGetting student with id: "+studentId);

            Student myStudent=session.get(Student.class,studentId);

            System.out.println("Updating student...");
            myStudent.setFirstName("Scooby");

            //commit the transaction
            session.getTransaction().commit();

            System.out.println("Done!");

            //NEW CODE

            session=factory.getCurrentSession();
            session.beginTransaction();

            session.createQuery("update Student set email = 'Scooby@gmail.com'")
                    .executeUpdate();

            //commit the transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }
        finally {
            factory.close();
        }
    }
}
