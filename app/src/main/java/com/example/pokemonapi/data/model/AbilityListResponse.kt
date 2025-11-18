package com.example.pokemonapi.models

data class AbilityListResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<AbilityItem>
)

data class AbilityItem(
    val name: String,
    val url: String
) {
    val id: Int
        get() = url.trimEnd('/').split('/').last().toInt()
}
