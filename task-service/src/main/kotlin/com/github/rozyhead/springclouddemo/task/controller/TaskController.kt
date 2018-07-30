package com.github.rozyhead.springclouddemo.task.controller

import com.github.rozyhead.springclouddemo.task.model.Task
import com.github.rozyhead.springclouddemo.task.repository.TaskRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/tasks")
class TaskController(
    val taskRepository: TaskRepository
) {

  @GetMapping
  fun getTasks(): List<Task> {
    return taskRepository.findAll()
  }

}
