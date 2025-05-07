package com.florianoDev.PersonalLibrary.DTO

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class UserRequestDTO(
    @field:NotBlank(message = "O nome é obrigatório")
    val nome: String,
    @field:Email(message = "E-mail inválido")
    @field:NotBlank(message = "O e-mail é obrigatório")
    val email: String,
    val dataNascimento: String,
    val ativo: Boolean,
    val horaRegistro: String
)
