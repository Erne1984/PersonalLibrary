package com.florianoDev.PersonalLibrary.Models

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    @field:NotBlank
    val nome: String,

    @field:Email
    val email: String,

    val dataNascimento: LocalDate,

    val ativo: Boolean,

    val horaRegistro: LocalDateTime,

    @OneToMany(mappedBy = "usuario", cascade = [CascadeType.ALL], orphanRemoval = true)
    val books: List<Book> = mutableListOf()
)
