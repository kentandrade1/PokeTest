package com.example.pokemonapi.data

import com.example.pokemonapi.ApiService
import javax.inject.Inject

class AbilityRepository @Inject constructor(
    private val api: ApiService
) {

    suspend fun getAbilities(limit: Int, offset: Int) =
        api.getAbilities(limit, offset)

    suspend fun getAbilityDetail(id: Int) =
        api.getAbilityDetail(id)
}
