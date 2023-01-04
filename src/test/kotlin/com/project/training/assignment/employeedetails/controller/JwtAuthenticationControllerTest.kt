package com.project.training.assignment.employeedetails.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.project.training.assignment.employeedetails.model.user.JwtRequest
import com.project.training.assignment.employeedetails.model.user.User
import com.project.training.assignment.employeedetails.model.user.UserDto
import com.project.training.assignment.employeedetails.repository.user.UserRepository
import com.project.training.assignment.employeedetails.service.JwtUserDetailsService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import java.lang.Exception
@SpringBootTest
@AutoConfigureMockMvc
class JwtAuthenticationControllerTest {
    @InjectMocks
    private val jwtAuthenticationController: JwtAuthenticationController? = null

    @Mock
    private val userRepository: UserRepository? = null

    @Mock
    private val jwtUserDetailsService: JwtUserDetailsService? = null

    @Autowired
    private var mockMvc: MockMvc? = null

    @Autowired
    private val context: WebApplicationContext? = null
    private val mapper = ObjectMapper()
    private var user: UserDto? = null
    private val request: JwtRequest? = null
    @BeforeEach
    fun setUp() {
        mockMvc = context?.let {
            MockMvcBuilders.webAppContextSetup(it)
                .apply<DefaultMockMvcBuilder>(SecurityMockMvcConfigurers.springSecurity()).build()
        }
        user = UserDto()
        user!!.username = "Ram"
        user!!.password = "ped"
        user!!.role = "admin"
    }

    @Test
    @Throws(Exception::class)
    fun whenCreateEmployee() {
        user = UserDto()
        user!!.username = "Ram"
        user!!.password = "ped"
        user!!.role = "admin"
        val user1 = User(1, "Ram", "ped", "admin")
        Mockito.`when`(jwtUserDetailsService!!.save(user!!)).thenReturn(user1)
        mockMvc!!.perform(
            MockMvcRequestBuilders.post("/register")
                .content(mapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
    }
}