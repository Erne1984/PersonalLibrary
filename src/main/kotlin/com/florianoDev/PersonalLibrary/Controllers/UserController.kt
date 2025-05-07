package com.florianoDev.PersonalLibrary.Controllers

import com.florianoDev.PersonalLibrary.DTO.UserRequestDTO
import com.florianoDev.PersonalLibrary.Models.Book
import com.florianoDev.PersonalLibrary.Models.User
import com.florianoDev.PersonalLibrary.Services.UserService
import jakarta.persistence.EntityNotFoundException
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService
) {

    @PostMapping
    fun create(@RequestBody @Valid dto: UserRequestDTO): ResponseEntity<User> {
        val created = userService.criar(dto)
        return ResponseEntity.status(HttpStatus.CREATED).body(created)
    }

    @GetMapping
    fun getAll(): ResponseEntity<List<User>> {
        val users = userService.listarTodos()
        return ResponseEntity.ok(users)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<User> {
        return try {
            val user = userService.buscarPorId(id)
            ResponseEntity.ok(user)
        } catch (e: EntityNotFoundException) {
            ResponseEntity.notFound().build()
        }
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody @Valid dto: UserRequestDTO): ResponseEntity<User> {
        return try {
            val updated = userService.atualizar(id, dto)
            ResponseEntity.ok(updated)
        } catch (e: EntityNotFoundException) {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        return try {
            userService.deletar(id)
            ResponseEntity.noContent().build()
        } catch (e: EntityNotFoundException) {
            ResponseEntity.notFound().build()
        }
    }
}