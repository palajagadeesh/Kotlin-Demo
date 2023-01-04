package com.project.training.assignment.employeedetails.service

import com.project.training.assignment.employeedetails.model.user.User
import com.project.training.assignment.employeedetails.model.user.UserDto
import com.project.training.assignment.employeedetails.repository.user.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.HashSet
@Service
class JwtUserDetailsService: UserDetailsService {
    @Autowired
    val userRepository: UserRepository? = null

    @Autowired
    val bcryptEncoder: PasswordEncoder? = null
    var logger = LoggerFactory.getLogger(JwtUserDetailsService::class.java)

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String?): UserDetails {
        val user = userRepository!!.findByUsername(username)
        if (user == null) {
            throw UsernameNotFoundException("User not found with username: $username")
        } else {
            logger.info("user found")
        }
        return org.springframework.security.core.userdetails.User(
            user.username,
            user.password,
            getAuthority(user)
        )
    }
    fun save(user: UserDto): User {
        val newUser = User()
        newUser.username = user.username
        newUser.password = bcryptEncoder!!.encode(user.password)
        newUser.role = user.role
        return userRepository!!.save(newUser)
    }

    fun getAuthority(user: User?): MutableSet<SimpleGrantedAuthority> {
        val authorities: MutableSet<SimpleGrantedAuthority> = HashSet()
        if (user != null) {
            authorities.add(SimpleGrantedAuthority("ROLE_" + user.role))
        }
        return authorities
    }
}