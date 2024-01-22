package com.training.compose.lessons.viewmodels.domain.usecase

import com.training.compose.lessons.viewmodels.domain.repository.TodosRepository

class GetTodosUseCase(private val todosRepository: TodosRepository) {
    operator fun invoke() = todosRepository.getTodos()
}