package com.ammyt.domain.model

// Interface segregation

interface ReadAggregate<T> {
    fun count(): Int
    fun all(): List<T>
    fun add(element: T)

}

interface WriteAggregate<T> {
    fun delete(position: Int)
    fun delete(element: T)
    fun get(position: Int): T

}

interface Aggregate<T>: ReadAggregate<T>, WriteAggregate<T>