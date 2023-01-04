package com.project.training.assignment.employeedetails.controller

import com.project.training.assignment.employeedetails.model.employee.Employee
import com.project.training.assignment.employeedetails.model.owner.Owner
import com.project.training.assignment.employeedetails.repository.employee.EmployeeRepository
import com.project.training.assignment.employeedetails.repository.owner.OwnerRepository
import com.project.training.assignment.employeedetails.service.EmployeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
class EmployeeController {
    @Autowired
    var employeeService: EmployeeService? = null

    @Autowired
    var employeeRepository: EmployeeRepository? = null
    @Autowired
    var ownerRepository: OwnerRepository? = null


    @GetMapping("/employeelist")
    fun getAllEmployee(): List<Employee> {
        return employeeService!!.getAllEmployee()}

    @GetMapping("/ownerlist")
    fun getAllOwners(): List<Owner> {
        return ownerRepository!!.findAll()
    }

    @PostMapping("/addemployee")
    fun createEmployee(@RequestBody employee: Employee?): Employee? {
        return employeeService!!.createEmployee(employee)
    }
    @PostMapping("/addowner")
    fun createOwner(@RequestBody owner: Owner): Owner {
        return ownerRepository!!.save(owner)
    }

    @PutMapping("/updateemployee/{empId}")
    fun updateEmployee(@PathVariable empId: Int, @RequestBody employee: Employee?): Employee? {
        return employeeService!!.updateEmployee(empId, employee)
    }

    @PutMapping("/updateowner/{ownId}")
    fun updateOwner(@PathVariable ownId: Int, @RequestBody owner: Owner): Owner {
        return ownerRepository!!.save(owner)
    }


    @DeleteMapping("/deleteemployee/{empId}")
    fun deleteEmployee(empId: Int) {
        employeeService!!.deleteEmployee(empId)
    }
    @DeleteMapping("/deleteowner/{ownId}")
    fun deleteOwner(@PathVariable ownId: Int) {
        ownerRepository!!.deleteById(ownId)
    }


    @GetMapping("/getbyid/{empId}")
    fun empById(@PathVariable empId: Int): Employee? {
        return employeeService!!.getEmpById(empId)
    }
}