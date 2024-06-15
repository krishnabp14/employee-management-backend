package com.employeeCRUD.employeeCRUDApplication.repository;

import com.employeeCRUD.employeeCRUDApplication.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
