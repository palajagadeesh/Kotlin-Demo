package com.project.training.assignment.employeedetails.service

import com.project.training.assignment.employeedetails.model.employee.Employee
import com.project.training.assignment.employeedetails.repository.employee.EmployeeRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class EmployeeServiceImpl:EmployeeService {
    @Autowired
    var employeeRepository: EmployeeRepository? = null
    var logger = LoggerFactory.getLogger(EmployeeServiceImpl::class.java)
    override fun getAllEmployee(): List<Employee> {
        logger.info("Employee details fetched")
        return employeeRepository!!.findAll()
    }

    override fun createEmployee(employee: Employee?): Employee? {
        if (employee != null) {
            return if (employee.empName.isEmpty() || employee.empName.isEmpty()) {
                throw com.project.training.assignment.employeedetails.exception.Error("Input is empty", HttpStatus.BAD_REQUEST)
            } else {
                logger.info("Employee is created")
                employeeRepository!!.save(employee)
            }
        }
        return employee?.let { employeeRepository?.save(it) }
    }

    override fun getEmpById(empId: Int): Employee? {
        //TODO("Not yet implemented")
        return employeeRepository!!.findById(empId).get()
    }

    override fun updateEmployee(empId: Int, employee: Employee?): Employee? {
        //TODO("Not yet implemented")
        if (employee != null) {
            employee.empId = empId
        }
        return employeeRepository!!.save(employee!!.copy())
    }

    override fun deleteEmployee(empId: Int) {
        //TODO("Not yet implemented")
        employeeRepository!!.deleteById(empId)
        logger.info("empId {} is deleted successfully @ {}", empId)
    }
}