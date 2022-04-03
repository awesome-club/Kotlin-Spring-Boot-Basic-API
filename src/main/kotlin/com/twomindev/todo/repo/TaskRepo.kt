package com.twomindev.todo.repo

import com.twomindev.todo.domain.Task
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface TaskRepo : JpaRepository<Task, Long> {
  @Query("from Task where id = :id")
  fun findOneById(id: Long): Task?
}
