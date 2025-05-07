package com.florianoDev.PersonalLibrary.Services

import com.florianoDev.PersonalLibrary.DTO.BookRequestDTO
import com.florianoDev.PersonalLibrary.Models.Book
import com.florianoDev.PersonalLibrary.Repository.BookRepository
import com.florianoDev.PersonalLibrary.Repository.UserRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class BookService(
    private val bookRepository: BookRepository,
    private val userRepository: UserRepository
) {

    fun criar(dto: BookRequestDTO): Book {

        val user = userRepository.findById(dto.usuarioId).orElseThrow {
            EntityNotFoundException("Usuário com id ${dto.usuarioId} não encontrado.")
        }

        val book = Book(
            titulo = dto.titulo,
            autor = dto.autor,
            dataPublicacao = LocalDate.parse(dto.dataPublicacao),
            disponivel = dto.disponivel,
            horaCadastro = LocalDateTime.parse(dto.horaCadastro),
            usuario = user
        )

        return bookRepository.save(book)
    }

    fun listarTodos(): List<Book> {
        return bookRepository.findAll()
    }

    fun buscarPorId(id: Long): Book {
        return bookRepository.findById(id).orElseThrow {
            EntityNotFoundException("Livro com id $id não encontrado.")
        }
    }

    fun atualizar(id: Long, dto: BookRequestDTO): Book {
        val existente = bookRepository.findById(id).orElseThrow {
            EntityNotFoundException("Livro com id $id não encontrado para atualização.")
        }

        val user = userRepository.findById(dto.usuarioId).orElseThrow {
            EntityNotFoundException("Usuário com id ${dto.usuarioId} não encontrado.")
        }

        val atualizado = existente.copy(
            titulo = dto.titulo,
            autor = dto.autor,
            dataPublicacao = LocalDate.parse(dto.dataPublicacao),
            disponivel = dto.disponivel,
            horaCadastro = LocalDateTime.parse(dto.horaCadastro),
            usuario = user
        )

        return bookRepository.save(atualizado)
    }

    fun deletar(id: Long) {
        if (!bookRepository.existsById(id)) {
            throw EntityNotFoundException("Livro com id $id não encontrado para exclusão.")
        }
        bookRepository.deleteById(id)
    }
}