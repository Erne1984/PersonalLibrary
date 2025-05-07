package com.florianoDev.PersonalLibrary.Services

import com.florianoDev.PersonalLibrary.DTO.UserRequestDTO
import com.florianoDev.PersonalLibrary.Models.User
import com.florianoDev.PersonalLibrary.Repository.UserRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun criar(dto: UserRequestDTO): User {
        val user = User(
            nome = dto.nome,
            email = dto.email,
            dataNascimento = LocalDate.parse(dto.dataNascimento),
            ativo = dto.ativo,
            horaRegistro = LocalDateTime.parse(dto.horaRegistro)
        )
        return userRepository.save(user)
    }

    fun listarTodos(): List<User> {
        return userRepository.findAll()
    }

    fun buscarPorId(id: Long): User {
        return userRepository.findById(id).orElseThrow {
            EntityNotFoundException("Usuário com id $id não encontrado.")
        }
    }

    fun atualizar(id: Long, dto: UserRequestDTO): User {
        val existente = userRepository.findById(id).orElseThrow {
            EntityNotFoundException("Usuário com id $id não encontrado para atualização.")
        }

        val atualizado = existente.copy(
            nome = dto.nome,
            email = dto.email,
            dataNascimento = LocalDate.parse(dto.dataNascimento),
            ativo = dto.ativo,
            horaRegistro = LocalDateTime.parse(dto.horaRegistro)
        )

        return userRepository.save(atualizado)
    }

    fun deletar(id: Long) {
        if (!userRepository.existsById(id)) {
            throw EntityNotFoundException("Usuário com id $id não encontrado para exclusão.")
        }
        userRepository.deleteById(id)
    }
}