package com.hibernate.hibernate.demo;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.text.ParseException;


public class FetchJoinDemo {
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
            //start a transaction
            session.beginTransaction();

           //get the instructor from db
            int id=1;

            Query<Instructor> query =session.createQuery("select i from Instructor i JOIN fetch  i.courses where i.id=:theInstructorId",Instructor.class);
            query.setParameter("theInstructorId",id);
            Instructor instructor=query.getSingleResult();
            System.out.println("Instructor: "+instructor);

            //get course for the instructor

            //commit the transaction
            session.getTransaction().commit();

            session.close();
            System.out.println("\n\n\n\n\nCourses: "+instructor.getCourses());
            System.out.println("Done!");
        }
        finally {
            //add clean up code

            factory.close();
        }
    }
}
