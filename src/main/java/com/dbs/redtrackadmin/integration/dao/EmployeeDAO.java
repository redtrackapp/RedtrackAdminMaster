package com.dbs.redtrackadmin.integration.dao;

import java.util.List;

import com.dbs.redtrackadmin.base.dto.Employee;
public interface EmployeeDAO
{
    public void saveEmployee(Employee employee);
    public Employee getEmployeeById(int id);
    public void updateEmployee(Employee employee);
    public void deleteEmployee(int id);
    public List<Employee> getAllEmployees();
}
