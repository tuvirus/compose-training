package com.training.compose.lessons.viewmodels.data

import com.training.compose.lessons.viewmodels.domain.entity.Todo
import kotlinx.coroutines.flow.Flow

interface TodosDataSource {
    fun getTodos(): Flow<List<Todo>>
}