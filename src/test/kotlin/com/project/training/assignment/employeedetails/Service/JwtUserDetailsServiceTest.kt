//package com.project.training.assignment.employeedetails.Service
//
//import com.project.training.assignment.employeedetails.model.user.User
//import com.project.training.assignment.employeedetails.model.user.UserDto
//import com.project.training.assignment.employeedetails.repository.user.UserRepository
//import com.project.training.assignment.employeedetails.service.JwtUserDetailsService
//import org.assertj.core.api.AssertionsForClassTypes
//import org.junit.jupiter.api.Test
//import org.mockito.ArgumentMatchers
//import org.mockito.InjectMocks
//import org.mockito.Mock
//import org.mockito.Mockito
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.security.core.userdetails.UserDetails
//import org.springframework.security.crypto.password.PasswordEncoder
//@SpringBootTest
//class JwtUserDetailsServiceTest {
//    @Mock
//    private val userRepository: UserRepository? = null
//
//    @Mock
//    private val bcryptEncoder: PasswordEncoder? = null
//
//    @InjectMocks
//    private val jwtUserDetailsService: JwtUserDetailsService? = null
//    private val userDetails: UserDetails? = null
//    @Test
//    fun whenSaveUser() {
//        val user = User()
//
//        user.username = "CAT"
//        user.password = bcryptEncoder!!.encode("RAT")
//        user.role = "admin"
//
//        val user1 = UserDto()
//        user1.username = "CAT"
//        user1.password = "RAT"
//        user1.role = "admin"
//
//        Mockito.`when`(userRepository!!.save(ArgumentMatchers.any())).thenReturn(user)
//        val expected = jwtUserDetailsService!!.save(user1)
//        AssertionsForClassTypes.assertThat(expected.username).isSameAs(user.username)
//        Mockito.verify(userRepository).save(user)
//    }
//
//    @Test
//    fun whenLoadUserByUserName() {
//        val user = User()
//        user.id=1
//        user.username = "CAT"
//        user.password =bcryptEncoder!!.encode("RAT")
//        user.role = "admin"
//        Mockito.`when`(userRepository!!.findByUsername("CAT")).thenReturn(user)
//        val created = jwtUserDetailsService!!.loadUserByUsername(user.username)
//        Mockito.verify(userRepository).findByUsername("CAT")
//    }
//}