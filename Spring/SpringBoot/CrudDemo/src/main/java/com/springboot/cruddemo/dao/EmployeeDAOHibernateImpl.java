package com.springboot.cruddemo.dao;

import com.springboot.cruddemo.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class EmployeeDAOHibernateImpl  implements EmployeeDAO{

    //define field for entityManager

    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOHibernateImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<Employee> findAll() {

        //get the current hibernate session
        Session session=entityManager.unwrap(Session.class);

        //create a query
        Query<Employee> query=session.createQuery("from Employee",Employee.class);

        //execute query and get result list
        List<Employee> employees=query.getResultList();

        //return the result
        return employees;
    }

    @Override
    public Employee findById(int id) {
        Session session=entityManager.unwrap(Session.class);

        Employee employee=session.get(Employee.class,id);

        return employee;
    }

    @Override
    public void save(Employee employee) {

        Session session=entityManager.unwrap(Session.class);

        session.saveOrUpdate(employee);

    }

    @Override
    public void delete(int id) {

        Session session=entityManager.unwrap(Session.class);

        Query query=session.createQuery("delete  from Employee where id=:employeeId");

        query.setParameter("employeeId",id);

        query.executeUpdate();

    }


}
