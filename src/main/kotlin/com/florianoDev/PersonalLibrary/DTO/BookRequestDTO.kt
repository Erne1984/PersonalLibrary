package com.florianoDev.PersonalLibrary.DTO

import jakarta.validation.constraints.NotBlank

data class BookRequestDTO(
    @field:NotBlank(message = "O título é obrigatório")
    val titulo: String,
    @field:NotBlank(message = "O autor é obrigatório")
    val autor: String,
    val dataPublicacao: String,
    val disponivel: Boolean,
    val horaCadastro: String,
    val usuarioId: Long
)
