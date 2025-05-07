package com.florianoDev.PersonalLibrary.Repository

import com.florianoDev.PersonalLibrary.Models.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
}