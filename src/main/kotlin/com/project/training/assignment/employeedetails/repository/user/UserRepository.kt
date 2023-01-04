package com.project.training.assignment.employeedetails.repository.user

import com.project.training.assignment.employeedetails.model.user.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Int> {
    fun findByUsername(username: String?): User?
}
