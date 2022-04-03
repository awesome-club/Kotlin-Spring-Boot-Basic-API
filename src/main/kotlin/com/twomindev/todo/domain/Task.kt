package com.twomindev.todo.domain

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Task(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long = 0,
  val name: String = "",
  val details: String = "",
  var status: TaskStatus = TaskStatus.ToDo,
  val createdDate: Date = Date()
)

class TaskDto(
  val id: Long = 0,
  val name: String = "",
  val status: TaskStatus = TaskStatus.ToDo
) {
  companion object {
    fun from(task: Task): TaskDto {
      return TaskDto(
        id = task.id,
        name = task.name,
        status = task.status
      )
    }
  }
}

enum class TaskStatus {
  ToDo, InProgress, Done
}
