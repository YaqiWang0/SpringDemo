package com.springsecurity.demo.dao;

import com.springsecurity.demo.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {



    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public User findByUserName(String userName) {
        Session session =sessionFactory.getCurrentSession();
        Query<User> query=session.createQuery("from User where userName=:name",User.class);
        query.setParameter("name",userName);
        User user=null;
        try{
            user=query.getSingleResult();
        }catch (Exception e){
            user=null;
        }
        return user;
    }

    @Override
    public void save(User user) {
        Session session=sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);

    }
}
