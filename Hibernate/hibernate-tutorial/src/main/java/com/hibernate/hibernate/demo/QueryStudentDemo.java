package com.hibernate.hibernate.demo;

import com.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class QueryStudentDemo {
    public static void main(String[] args) {

        //create sessionFactory
        SessionFactory factory=new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        //create session
        Session session = factory.getCurrentSession();

        try{

            //start a transaction
            session.beginTransaction();

            //query students
            List<Student> students=session.createQuery("from Student").getResultList();
            //display the students
            displayTheStudents(students);

            //query students: lastName: 'Doe'
            students=session.createQuery("from Student s where s.lastName='Doe'").getResultList();
            System.out.println("\n\nStudents who have last name of Doe");
            displayTheStudents(students);

            //query students: lastName='Doe' OR firstName='Mary'
            students=session.createQuery("from Student s where s.lastName='Doe' OR s.firstName='Mary'").getResultList();
            System.out.println("\n\nStudents who have last name of Doe or first name of Mary");
            displayTheStudents(students);

            //query students: where email LIKE '%gmail.com'
            students=session.createQuery("from Student s where s.email like '%gmail.com'").getResultList();
            System.out.println("\n\nStudents who have email address end with gmail.com");
            displayTheStudents(students);

            //commit the transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }
        finally {
            factory.close();
        }
    }

    private static void displayTheStudents(List<Student> students) {
        for (Student tempStudnet : students) {
            System.out.println(tempStudnet);
        }
    }
}
