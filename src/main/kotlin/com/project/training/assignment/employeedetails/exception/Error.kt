package com.project.training.assignment.employeedetails.exception

import org.springframework.http.HttpStatus

data class Error(override val message: String? = null,
            val status: HttpStatus? = null):RuntimeException() {
}