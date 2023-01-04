package com.project.training.assignment.employeedetails.service

import com.project.training.assignment.employeedetails.model.employee.Employee
import org.springframework.stereotype.Component

@Component
interface EmployeeService {
    fun getAllEmployee():List<Employee>
    fun createEmployee(employee: Employee?): Employee?
    fun getEmpById(empId: Int): Employee?
    fun updateEmployee(empId: Int, employee: Employee?): Employee?
    fun deleteEmployee(empId: Int)
}