package com.github.rozyhead.springclouddemo.task

import com.github.rozyhead.springclouddemo.task.model.Task
import com.github.rozyhead.springclouddemo.task.repository.TaskRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class SampleDataInitializer(
    val taskRepository: TaskRepository
) : CommandLineRunner {

  @Transactional
  override fun run(vararg args: String?) {
    registerTask("Milk")
    registerTask("Notebook")
  }

  private fun registerTask(title: String): Task {
    val task = Task(title = title)
    taskRepository.save(task)
    return task
  }

}