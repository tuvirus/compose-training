package com.training.compose.lessons.viewmodels.data

import com.training.compose.lessons.viewmodels.domain.entity.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LocalTodosDataSource: TodosDataSource {
    override fun getTodos(): Flow<List<Todo>> {
        return flow {
            delay(3000)

            emit(listOf(
                Todo(
                    id = 1,
                    title = "Learn Compose",
                    isDone = false
                ),
                Todo(
                    id = 2,
                    title = "Learn Hilt",
                    isDone = true
                ),
                Todo(
                    id = 3,
                    title = "Learn Navigation",
                    isDone = false
                ),
            ))
        }.flowOn(Dispatchers.IO)
    }
}