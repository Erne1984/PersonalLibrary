package com.florianoDev.PersonalLibrary.Models

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.validation.constraints.NotBlank
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
data class Book(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    @field:NotBlank(message = "O título é obrigatório")
    val titulo: String,

    @field:NotBlank(message = "O autor é obrigatório")
    val autor: String,

    val dataPublicacao: LocalDate,

    val disponivel: Boolean,

    val horaCadastro: LocalDateTime,

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonBackReference
    val usuario: User? = null
)