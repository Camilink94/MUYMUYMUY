package com.camilink.rrhh.util

enum class ListOrder(
    val id: Int,
    val toString: String,
    val queryValue: String
) {
    NONE(-1, "Predeterminado", "ASC"),
    ASCENDING(0, "De menor a mayor", "ASC"),
    DESCENDING(1, "De mayor a menor", "DESC");

    override fun toString() = toString
}