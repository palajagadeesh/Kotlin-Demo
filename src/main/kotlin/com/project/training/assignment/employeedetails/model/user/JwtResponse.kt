package com.project.training.assignment.employeedetails.model.user

import java.io.Serializable

data class JwtResponse(
    val token: String,
    val serialVersionUID:Long= -8091879091924046844L

): Serializable