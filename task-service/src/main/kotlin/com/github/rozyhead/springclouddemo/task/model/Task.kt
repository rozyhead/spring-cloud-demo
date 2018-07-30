package com.github.rozyhead.springclouddemo.task.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Task(
    @Id
    @GeneratedValue
    var id: Long = 0,

    @Column(nullable = false)
    var title: String = "",

    @Column(nullable = true)
    var note: String = "",

    @Column(nullable = false)
    var done: Boolean = false,

    @Column(nullable = false)
    var ownerId: Long = 0
)