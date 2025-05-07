package com.florianoDev.PersonalLibrary.Controllers

import com.florianoDev.PersonalLibrary.DTO.BookRequestDTO
import com.florianoDev.PersonalLibrary.Models.Book
import com.florianoDev.PersonalLibrary.Services.BookService
import jakarta.persistence.EntityNotFoundException
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/books")
class BookController(
    private val bookService: BookService
) {

    @PostMapping
    fun create(@RequestBody @Valid dto: BookRequestDTO): ResponseEntity<Book> {
        val created = bookService.criar(dto)
        return ResponseEntity.status(HttpStatus.CREATED).body(created)
    }

    @GetMapping
    fun getAll(): ResponseEntity<List<Book>> {
        val books = bookService.listarTodos()
        return ResponseEntity.ok(books)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Book> {
        return try {
            val book = bookService.buscarPorId(id)
            ResponseEntity.ok(book)
        } catch (e: EntityNotFoundException) {
            ResponseEntity.notFound().build()
        }
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody @Valid dto: BookRequestDTO): ResponseEntity<Book> {
        return try {
            val updated = bookService.atualizar(id, dto)
            ResponseEntity.ok(updated)
        } catch (e: EntityNotFoundException) {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        return try {
            bookService.deletar(id)
            ResponseEntity.noContent().build()
        } catch (e: EntityNotFoundException) {
            ResponseEntity.notFound().build()
        }
    }
}