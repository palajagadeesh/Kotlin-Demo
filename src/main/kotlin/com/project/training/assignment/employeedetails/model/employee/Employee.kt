package com.project.training.assignment.employeedetails.model.employee

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table
data class Employee(@Id
                    var empId:Int,
                    var empName:String,
                    var empLoc:String)