package com.florianoDev.PersonalLibrary.Repository

import com.florianoDev.PersonalLibrary.Models.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
}