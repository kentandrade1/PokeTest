package com.example.pokemonapi.models

data class AbilityDetailResponse(
    val id: Int,
    val name: String,
    val effect_entries: List<EffectEntry>,
    val flavor_text_entries: List<FlavorEntry>,
    val pokemon: List<PokemonEntry>,
    val generation: Generation
)

data class EffectEntry(
    val effect: String,
    val short_effect: String?,   // 👈 ESTA ES LA CLAVE
    val language: Language
)

data class FlavorEntry(
    val flavor_text: String,
    val language: Language,
)

data class PokemonEntry(
    val is_hidden: Boolean,
    val pokemon: PokemonData
)

data class PokemonData(
    val name: String
)

data class Language(val name: String)

data class Generation(val name: String)
