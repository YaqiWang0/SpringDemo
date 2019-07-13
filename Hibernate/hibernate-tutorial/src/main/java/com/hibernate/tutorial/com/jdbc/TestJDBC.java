package com.hibernate.tutorial.com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJDBC {
    public static void main(String[] args) {
        String jdbcUrl="jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false";
        String user="hbstudent";
        String password="Dfhydgn19950624!";
        try{

            System.out.println("Connenting to database: "+jdbcUrl);
            Connection connection= DriverManager.getConnection(jdbcUrl,user,password);

            System.out.println("Connection successful!!!");


        }catch (Exception exc){
            exc.printStackTrace();
        }

    }

}
