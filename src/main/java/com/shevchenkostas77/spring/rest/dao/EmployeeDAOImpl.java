package com.shevchenkostas77.spring.rest.dao;

import com.shevchenkostas77.spring.rest.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
//    @Transactional // спринг автоматически управляет открытием и закрытием транзакций
    public List<Employee> getAllEmployees() {
        Session session = sessionFactory.getCurrentSession();

//        List<Employee> allEmployees = session.createQuery("from Employee"
//                , Employee.class).getResultList();

        Query<Employee> query = session.createQuery("from Employee"
                , Employee.class);
        List<Employee> allEmplyees = query.getResultList();

        return allEmplyees;
    }

    @Override
    public void saveEmployee(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(employee);
    }

    @Override
    public Employee getEmployee(int id) {
        Session session = sessionFactory.getCurrentSession();
        Employee employee = session.get(Employee.class, id);
        return employee;
    }

    @Override
    public void deleteEmployee(int id) {
        Session session = sessionFactory.getCurrentSession();

//        Employee employee = session.get(Employee.class, id);
//        session.delete(employee);

        Query<Employee> query = session.createQuery("delete from Employee " +
                "where id = :employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();
    }

}
