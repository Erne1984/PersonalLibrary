package com.florianoDev.PersonalLibrary.Repository

import com.florianoDev.PersonalLibrary.Models.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : JpaRepository<Book, Long> {
}