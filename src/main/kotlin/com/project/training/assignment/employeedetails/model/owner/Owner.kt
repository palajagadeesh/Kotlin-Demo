package com.project.training.assignment.employeedetails.model.owner

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table
data class Owner( @Id
                  var id: Int = 0,
                  var name: String? = null,
                  var company: String? = null)
