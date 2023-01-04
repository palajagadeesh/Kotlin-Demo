package com.project.training.assignment.employeedetails.repository.employee

import com.project.training.assignment.employeedetails.model.employee.Employee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EmployeeRepository: JpaRepository<Employee, Int> {
}