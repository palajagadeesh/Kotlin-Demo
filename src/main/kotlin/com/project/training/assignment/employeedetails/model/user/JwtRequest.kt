package com.project.training.assignment.employeedetails.model.user

import java.io.Serializable

data class JwtRequest(var username: String? = null,
                      var password: String? = null,
                      val serialVersionUID: Long = -8091879091924046844L): Serializable