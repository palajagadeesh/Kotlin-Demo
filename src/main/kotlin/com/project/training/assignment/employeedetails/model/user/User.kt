package com.project.training.assignment.employeedetails.model.user

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "user")
data class User (@Id
                 @GeneratedValue(strategy = GenerationType.IDENTITY) var id:Int?=null,
                 @Column
                 var username: String? = null,
                 @Column
                 @JsonIgnore
                 var password: String? = null,
                 var role: String? = null)