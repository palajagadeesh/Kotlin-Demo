package com.project.training.assignment.employeedetails.exception

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.lang.NullPointerException
import java.util.NoSuchElementException

@ControllerAdvice
class ExceptionHandler: ResponseEntityExceptionHandler() {
    @ExceptionHandler(Error::class)
    fun emptyInput(error: Error?): ResponseEntity<String> {
        return ResponseEntity("Input username is Empty", HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(NoSuchElementException::class)
    fun employeeNotFound(noSuchElementException: NoSuchElementException?): ResponseEntity<String> {
        return ResponseEntity("No related data", HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(NullPointerException::class)
    fun nullPointer(nullPointerException: NullPointerException?): ResponseEntity<String> {
        return ResponseEntity("null value", HttpStatus.BAD_GATEWAY)
    }

    override fun handleHttpRequestMethodNotSupported(
        ex: HttpRequestMethodNotSupportedException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        return ResponseEntity("Input http request is wrong", HttpStatus.BAD_REQUEST)
    }
}