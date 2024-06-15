package com.employeeCRUD.employeeCRUDApplication.service;

import com.employeeCRUD.employeeCRUDApplication.entity.Employee;
import com.employeeCRUD.employeeCRUDApplication.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee postEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public void deleteEmployee(Long id) {
        if(!employeeRepository.existsById(id)) {
            throw new EntityNotFoundException("Employee not found with this id : {}" + id);
        }

        employeeRepository.deleteById(id);
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee updateEmployee(Long id, Employee employee) {
        Optional<Employee> existingEmployee = employeeRepository.findById(id);

        if(existingEmployee.isPresent()) {
            Employee newEmployee = existingEmployee.get();
            newEmployee.setName(employee.getName());
            newEmployee.setEmail(employee.getEmail());
            newEmployee.setPhone(employee.getPhone());
            newEmployee.setDepartment(employee.getDepartment());

            return employeeRepository.save(newEmployee);
        }

        return null;
    }
}
