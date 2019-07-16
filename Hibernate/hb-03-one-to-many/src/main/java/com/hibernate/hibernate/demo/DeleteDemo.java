package com.hibernate.hibernate.demo;

import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.ParseException;


public class DeleteDemo {
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
            int id=2;
            Instructor instructor=session.get(Instructor.class,id);

            if(instructor != null){
                session.delete(instructor);
            }
            //session.createQuery("delete from Instructor where last_name='Darby'").executeUpdate();

            //commit the transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }
        finally {
            factory.close();
        }
    }
}
