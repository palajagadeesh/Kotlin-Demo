package com.project.training.assignment.employeedetails.Service

import com.project.training.assignment.employeedetails.model.employee.Employee
import com.project.training.assignment.employeedetails.repository.employee.EmployeeRepository
import com.project.training.assignment.employeedetails.service.EmployeeServiceImpl
import org.assertj.core.api.AssertionsForClassTypes
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import java.util.*
@SpringBootTest
class EmployeeServiceTest {
    @Mock
    private val employeeRepository: EmployeeRepository? = null

    @InjectMocks
    private val employeeService: EmployeeServiceImpl? = null
    @Test
    fun whenSaveEmployee() {
        val employee = Employee(1,"Ram","Ind")
        employee.empName = "Ram"
        Mockito.`when`(employeeRepository!!.save(ArgumentMatchers.any(Employee::class.java))).thenReturn(employee)
        val created = employeeService!!.createEmployee(employee)
        if (created != null) {
            AssertionsForClassTypes.assertThat(created.empName).isSameAs(employee.empName)
        }
        Mockito.verify(employeeRepository).save(employee)
    }

    @Test
    fun shouldReturnAllEmployees() {
        val employee: Any? = ArrayList<Any?>()
        Employee(1,"Ram","Ind");
        Mockito.`when`(employeeRepository!!.findAll()).thenReturn(employee as MutableList<Employee>?)
        val expected = employeeService!!.getAllEmployee()
        Assertions.assertEquals(expected, employee)
        Mockito.verify(employeeRepository).findAll()
    }

    @Test
    fun whenGivenId_shouldDeleteUser_ifFound() {
        val employee = Employee(1,"Ram","Ind")
        Mockito.`when`(employeeRepository!!.findById(employee.empId)).thenReturn(Optional.of(employee))
        employeeService!!.deleteEmployee(employee.empId)
        Mockito.verify(employeeRepository).deleteById(employee.empId)
    }

    @Test
    fun should_throw_exception_when_Employee_doesnt_exist() {
        val employee = Employee(1,"Ram","Ind")
        Mockito.`when`(employeeRepository!!.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.ofNullable(null))
        employeeService!!.deleteEmployee(employee.empId)
    }

    @Test
    fun whenGivenId_shouldUpdateEmployee() {
        val employee = Employee(1,"Ram","Ind")
        val employee2 = Employee(2,"Ravan","SL")
        Mockito.`when`<Any>(employeeRepository!!.save(ArgumentMatchers.any())).thenReturn(employee2)
        employeeService!!.updateEmployee(employee.empId, employee2)
        Mockito.verify(employeeRepository).save(employee2)
    }

    @Test
    fun whenGivenId_shouldReturnEmployee() {
        val employee = Employee(1,"Ram","Ind")
        Mockito.`when`(employeeRepository!!.findById(employee.empId)).thenReturn(Optional.of(employee))
        val expected = employeeService!!.getEmpById(employee.empId)
        AssertionsForClassTypes.assertThat(expected).isSameAs(employee)
        Mockito.verify(employeeRepository).findById(employee.empId)
    }
}