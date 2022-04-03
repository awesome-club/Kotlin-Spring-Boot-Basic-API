package com.twomindev.todo.resource

import com.twomindev.todo.domain.Task
import com.twomindev.todo.domain.TaskDto
import com.twomindev.todo.repo.TaskRepo
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class TaskResource(
  private val taskRepo: TaskRepo
) {

  @GetMapping("/")
  fun hello(): String {
    return "hello! :)"
  }

  @GetMapping("/tasks")
  fun getTasks(): List<TaskDto> {
    return taskRepo.findAll().map { TaskDto.from(it) }
  }

  @PostMapping("/tasks")
  fun createTask(@RequestBody dto: TaskDto) {
    val task = Task(
      name = dto.name,
      status = dto.status
    )
    taskRepo.save(task)
  }

  @PutMapping("/tasks/{id}")
  fun updateTask(@PathVariable id: Long, @RequestBody dto: TaskDto) {
    val task = taskRepo.findOneById(id)
    task?.let {
      task.status = dto.status
      taskRepo.save(task)
    }
  }

  @DeleteMapping("/tasks/{id}")
  fun deleteTask(@PathVariable id: Long) {
    taskRepo.deleteById(id)
  }
}
