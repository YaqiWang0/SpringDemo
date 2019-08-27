package com.springboot.thymeleafdemo.service;

import com.springboot.thymeleafdemo.dao.EmployeeRepository;
import com.springboot.thymeleafdemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    private EmployeeRepository employeeRepository;

    //private EmployeeDAO employeeDAO;
    //private EmployDAO employDAO;
//    @Autowired
//    public EmployeeServiceImpl(@Qualifier("employeeDAOJpaImpl") EmployeeDAO employeeDAO) {
//        this.employeeDAO = employeeDAO;
//    }

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }




    @Override
    //@Transactional
    public List<Employee> findAll() {
        //return employeeDAO.findAll();
        return employeeRepository.findAllByOrderByLastNameAsc();
    }

    @Override
    //@Transactional
    public Employee findById(int id) {
        //return employeeDAO.findById(id);
        Optional<Employee> result=employeeRepository.findById(id);
        Employee employee=null;
        if(result.isPresent())
            employee= result.get();
        else
            throw new RuntimeException("Did not found employee id -" +id);
        return employee;

    }

    @Override
    //@Transactional
    public void save(Employee employee) {
        //employeeDAO.save(employee);
        employeeRepository.save(employee);
    }

    @Override
    //@Transactional
    public void delete(int id) {
        //employeeDAO.delete(id);
        employeeRepository.deleteById(id);

    }
}
