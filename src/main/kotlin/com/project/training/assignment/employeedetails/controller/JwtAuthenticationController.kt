package com.project.training.assignment.employeedetails.controller

import com.project.training.assignment.employeedetails.config.JwtTokenUtil
import com.project.training.assignment.employeedetails.model.user.JwtRequest
import com.project.training.assignment.employeedetails.model.user.JwtResponse
import com.project.training.assignment.employeedetails.model.user.UserDto
import com.project.training.assignment.employeedetails.service.JwtUserDetailsService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.lang.Exception

@RestController
@CrossOrigin
class JwtAuthenticationController {
    @Autowired
    val authenticationManager: AuthenticationManager? = null

    @Autowired
    val jwtTokenUtil: JwtTokenUtil? = null

    @Autowired
    private val userDetailsService: JwtUserDetailsService? = null
    var logger = LoggerFactory.getLogger(JwtAuthenticationController::class.java)
    @PostMapping("/authenticate")
    @Throws(Exception::class)
    fun createAuthenticationToken(@RequestBody authenticationRequest: JwtRequest): ResponseEntity<*> {
        try {
            authenticationRequest.username?.let { authenticationRequest.password?.let { it1 -> authenticate(it, it1) } }
            logger.info("Succesfully Logged in", authenticationRequest.username)
        } catch (ex: Exception) {
            throw Exception("Invalid username/password")
        }
        val userDetails = userDetailsService!!.loadUserByUsername(authenticationRequest.username)
        val token = jwtTokenUtil!!.generateToken(userDetails)
        return ResponseEntity.ok(JwtResponse(token))
    }

    @PostMapping("/register")
    @Throws(Exception::class)
    fun saveUser(@RequestBody user: UserDto?): ResponseEntity<*> {
        return ResponseEntity.ok(
            userDetailsService!!.save(
                user!!
            )
        )
    }

    @Throws(Exception::class)
    private fun authenticate(username: String, password: String) {
        try {
            authenticationManager!!.authenticate(UsernamePasswordAuthenticationToken(username, password))
        } catch (e: DisabledException) {
            throw Exception("USER_DISABLED", e)
        } catch (e: BadCredentialsException) {
            throw Exception("INVALID_CREDENTIALS", e)
        }
    }
}