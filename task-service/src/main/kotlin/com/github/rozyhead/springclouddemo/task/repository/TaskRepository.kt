package com.github.rozyhead.springclouddemo.task.repository

import com.github.rozyhead.springclouddemo.task.model.Task
import org.springframework.data.jpa.repository.JpaRepository

interface TaskRepository : JpaRepository<Task, Long>
