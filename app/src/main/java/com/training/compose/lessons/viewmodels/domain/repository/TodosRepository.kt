package com.training.compose.lessons.viewmodels.domain.repository

import com.training.compose.lessons.viewmodels.domain.entity.Todo
import kotlinx.coroutines.flow.Flow

interface TodosRepository {
    fun getTodos(): Flow<List<Todo>>
}