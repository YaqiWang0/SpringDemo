package com.springsecurity.demo.dao;

import com.springsecurity.demo.entity.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Role findRoleByName(String theRoleName) {
        Session session=sessionFactory.getCurrentSession();

        Query<Role> query=session.createQuery("from Role where name=:roleName",Role.class);
        query.setParameter("roleName",theRoleName);

        Role role=null;
        try{
            role=query.getSingleResult();
        }catch (Exception e){
            role=null;
        }
        return role;
    }
}
