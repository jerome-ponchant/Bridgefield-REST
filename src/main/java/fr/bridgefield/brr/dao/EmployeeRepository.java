package fr.bridgefield.brr.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.bridgefield.brr.dao.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
