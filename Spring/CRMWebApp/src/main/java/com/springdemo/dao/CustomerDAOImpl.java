package com.springdemo.dao;

import com.springdemo.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {



    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> getCustomers() {

        //get the current hibernate session
        Session session=sessionFactory.getCurrentSession();

        //create a query
        Query<Customer>  query=session.createQuery("from Customer order by lastName",Customer.class);

        //execute query and get result list
        List<Customer> list=query.getResultList();

        //return the results
        return list;
    }

    @Override
    public void saveCustomer(Customer customer) {

        Session session=sessionFactory.getCurrentSession();

        session.saveOrUpdate(customer);
    }

    @Override
    public Customer getCustomer(int theId) {
        Session session=sessionFactory.getCurrentSession();

        Customer customer=session.get(Customer.class,theId);
        return customer;
    }

    @Override
    public void deleteCustomer(int id) {
        Session session=sessionFactory.getCurrentSession();

        Query query=session.createQuery("delete from Customer where id=:customerId");
        query.setParameter("customerId",id);

        query.executeUpdate();
    }

    @Override
    public List<Customer> searchCustomers(String name) {
        Session session=sessionFactory.getCurrentSession();

        Query query=null;

        if(name!=null && name.trim().length()>0){
            query=session.createQuery("from Customer where lower(firstName)like :name or lower(lastName) like :name",Customer.class);
            query.setParameter("name","%"+name.toLowerCase()+"%");
        }
        else {
            query=session.createQuery("from Customer ",Customer.class);
        }

        List<Customer> customers=query.getResultList();

        return customers;
    }
}
