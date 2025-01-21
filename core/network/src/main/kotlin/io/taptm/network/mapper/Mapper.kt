package io.taptm.network.mapper

interface Mapper<T: Any, R: Any> {
    fun toDomain(response: T): R
}
